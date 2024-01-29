package com.skjo.skjo_android_template.view.base

import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.viewbinding.ViewBinding
import com.skjo.skjo_android_template.manager.ToastManager
import com.skjo.skjo_android_template.utils.KeyboardVisibilityUtils

abstract class GUIActivity<VB: ViewBinding>(inflate: InflateActivity<VB>) : BaseActivity<VB>(inflate) {
    private var mToastManager: ToastManager? = null
    private var isShowKeyboard = false

    val isKeyboardShown: Boolean
        get() = isShowKeyboard

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addKeyboardVisibilityListener()
        mToastManager = ToastManager(this)
    }

    /**
     * Key board hide
     */
    fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(mBinding.root.windowToken, 0)
    }

    /**
     * Key board hide
     */
    fun hideKeyboard(view: View) {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    /**
     * Key board show/hide listener
     */
    private fun addKeyboardVisibilityListener() {
        KeyboardVisibilityUtils(
            mBinding.root,
            onShowKeyboard = { _, _ ->
                isShowKeyboard = true
                mToastManager?.onShowKeypad()
            },
            onHideKeyboard = {
                isShowKeyboard = false
                mToastManager?.onHideKeyboard()
            }
        )
    }

    fun showToastMessage(message: String?) {
        mToastManager?.show(message)
    }
}