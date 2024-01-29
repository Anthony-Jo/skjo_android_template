package com.skjo.skjo_android_template.view.base

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding>(private val inflate: InflateActivity<VB>) : AppCompatActivity() {
    protected val mBinding: VB by lazy { inflate.invoke(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)

        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
        onCreateActivityView()
    }

    /**
     * LifeCycle - onCreate() : activity view init
     */
    protected abstract fun onCreateActivityView()

    /**
     * SDK 33 대응 onBackPressedDispatcher 공통화
     */
    protected abstract fun onBackPressedCallback()

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            onBackPressedCallback()
        }
    }

    abstract val viewTag: String
}