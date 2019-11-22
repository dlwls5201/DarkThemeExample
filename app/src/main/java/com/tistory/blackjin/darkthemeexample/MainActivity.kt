package com.tistory.blackjin.darkthemeexample

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import kotlinx.android.synthetic.main.activity_main.*

/**
 * https://github.com/material-components/material-components-android/releases
 * https://material.io/design/color/dark-theme.html#anatomy
 * https://material.io/develop/android/theming/dark/
 * https://blog.prototypr.io/implementing-dark-theme-in-android-dfe63e62145d
 *
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("BlackJin","getDefaultNightMode : ${AppCompatDelegate.getDefaultNightMode()}")

        initView()
        initButton()

    }

    private fun initView() {
        val uiMode = resources.configuration.uiMode
        val nightMask = Configuration.UI_MODE_NIGHT_MASK
        val currentNightMode = uiMode and nightMask
        Log.d("BlackJin","uiMode : $uiMode , nightMask : $nightMask , currentNightMode : $currentNightMode")
        if (currentNightMode == Configuration.UI_MODE_NIGHT_NO) {
            tvTheme.text = "NIGHT_MODE_OFF"
        } else if (currentNightMode == Configuration.UI_MODE_NIGHT_YES) {
            tvTheme.text = "NIGHT_MODE_ON"
        }
    }

    private fun initButton() {

        tvTheme.setOnClickListener {
            val currentNightMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK

            if (currentNightMode == Configuration.UI_MODE_NIGHT_NO) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else if (currentNightMode == Configuration.UI_MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        tvToast.setOnClickListener {
            Toast.makeText(baseContext, "토스트 입니다.", Toast.LENGTH_LONG).show()
        }

        tvDialog.setOnClickListener {
            val alertDialogBuilder = AlertDialog.Builder(this)
            alertDialogBuilder.setTitle("확인해주세요")
            alertDialogBuilder.setMessage("팝업이 생성되었습니다").setCancelable(false)
                .setPositiveButton("확인") { _, _ ->
                    // 확인시 수행할 함수
                }
                .setNegativeButton("취소") { _, _ ->
                    // 취소시 수행할 함수
                }
            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
        }
    }
}
