package com.skjo.skjo_android_template.utils

import android.app.Activity
import android.os.Build

object AnimateUtil {
    /**
     * Activity Open Animate
     * Android 14 (SDK 34) 대응
     */
    fun openOverrideTransition(
        activity: Activity,
        enterAnim: Int,
        exitAnim: Int
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            activity.overrideActivityTransition(
                Activity.OVERRIDE_TRANSITION_OPEN,
                enterAnim,
                exitAnim
            )
        } else {
            @Suppress("DEPRECATION")
            activity.overridePendingTransition(enterAnim, exitAnim)
        }
    }

    /**
     * Activity Close Animate
     * Android 14 (SDK 34) 대응
     */
    fun closeOverrideTransition(
        activity: Activity,
        enterAnim: Int,
        exitAnim: Int
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            activity.overrideActivityTransition(
                Activity.OVERRIDE_TRANSITION_CLOSE,
                enterAnim,
                exitAnim
            )
        } else {
            @Suppress("DEPRECATION")
            activity.overridePendingTransition(enterAnim, exitAnim)
        }
    }
}