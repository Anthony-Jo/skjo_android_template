package com.skjo.skjo_android_template.manager

import android.content.Context
import android.content.res.Resources
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.widget.Toast

class ToastManager(private val context: Context) {
    private val mToast: Toast = Toast.makeText(context, "", Toast.LENGTH_LONG)
    private val handler: Handler = Handler(Looper.getMainLooper())

    init {
        initView()
    }

    private fun initView() {
        mToast.setGravity(Gravity.BOTTOM, 0 , 120.toPx())
    }

    fun show(message: String?) {
        if (message.isNullOrEmpty()) {
            return
        }

        mToast.cancel()
        mToast.duration = Toast.LENGTH_LONG
        mToast.setText(message)

        handler.postDelayed({ mToast.show() }, 500)
    }

    /**
     * when shown keyboard adjust toast position
     */
    fun onShowKeypad() {
        mToast.setGravity(Gravity.CENTER, 0, (-20).toPx())
    }

    /**
     * when hide keyboard adjust toast position
     */
    fun onHideKeyboard() {
        mToast.setGravity(Gravity.BOTTOM, 0, 120.toPx())
    }

    private fun Int.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()
}