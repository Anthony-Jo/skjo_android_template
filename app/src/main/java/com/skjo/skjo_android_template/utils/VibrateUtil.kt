package com.skjo.skjo_android_template.utils

import android.content.Context
import android.content.Context.VIBRATOR_SERVICE
import android.os.Build
import android.os.CombinedVibration
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager

/**
 * 단순 진동 발생 이벤트, SDK version 별 분기
 */
class VibrateUtil(private val context: Context) {
    private lateinit var mVibrate: Vibrator
    private lateinit var mVibrateManager: VibratorManager

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            mVibrateManager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
        } else {
            @Suppress("DEPRECATION")
            mVibrate = context.getSystemService(VIBRATOR_SERVICE) as Vibrator
        }
    }

    /**
     * @param time 진동 시간 ms 단위
     */
    fun onVibration(time: Long) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val effect = VibrationEffect.createOneShot(time, VibrationEffect.DEFAULT_AMPLITUDE)
            val combined = CombinedVibration.createParallel(effect)
            mVibrateManager.vibrate(combined)
        } else {
            val effect = VibrationEffect.createOneShot(time, VibrationEffect.DEFAULT_AMPLITUDE)
            mVibrate.vibrate(effect)
        }
    }
}