package com.skjo.skjo_android_template.view

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import com.skjo.skjo_android_template.databinding.ActivitySplashBinding
import com.skjo.skjo_android_template.view.base.BaseActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity<ActivitySplashBinding>(ActivitySplashBinding::inflate) {
    override fun onCreateActivityView() {
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed(
            {

            }, 2000
        )
    }


    override fun onBackPressedCallback() = Unit

    override val viewTag: String
        get() = TAG

    companion object {
        const val TAG = "SplashActivity"
    }
}