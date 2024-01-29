package com.skjo.skjo_android_template.manager

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.skjo.skjo_android_template.R

class ProgressBarManager(private val context: Context) {
    private val mProgressBar = ProgressBar(context)
    private var mLayout: ConstraintLayout? = null
    private val handler = Handler(Looper.getMainLooper())

    init {
       initView()
    }

    private fun initView() {
        if (context is AppCompatActivity) {
            context.runOnUiThread {
                mLayout = context.findViewById(PROGRESS_BAR_ID)

                if (mLayout == null) {
                    val progressLayoutParams = ConstraintLayout.LayoutParams(
                        ConstraintLayout.LayoutParams.WRAP_CONTENT,
                        ConstraintLayout.LayoutParams.WRAP_CONTENT
                    )

                    val backgroundView = context.findViewById<View>(android.R.id.content)
                        .rootView as ViewGroup

                    progressLayoutParams.topToTop = ConstraintLayout.LayoutParams.PARENT_ID
                    progressLayoutParams.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
                    progressLayoutParams.startToStart = ConstraintLayout.LayoutParams.PARENT_ID
                    progressLayoutParams.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID

                    val backgroundLayout = ConstraintLayout.LayoutParams(
                        ConstraintLayout.LayoutParams.MATCH_PARENT,
                        ConstraintLayout.LayoutParams.MATCH_PARENT
                    )

                    mProgressBar.isIndeterminate = true
                    mProgressBar.indeterminateTintList = ContextCompat.getColorStateList(context, R.color.white)
                    mProgressBar.progressBackgroundTintList = ContextCompat.getColorStateList(context, R.color.transparent)

                    mLayout?.addView(mProgressBar, progressLayoutParams)
                    mLayout?.id = PROGRESS_BAR_ID
                    backgroundView.addView(mLayout, backgroundLayout)
                }
            }
        }
    }

    fun setProgressTintColor(colorRes: Int) {
        mProgressBar.indeterminateTintList = ContextCompat.getColorStateList(context, colorRes)
    }

    fun setProgressBackground(colorRes: Int) {
        mProgressBar.progressBackgroundTintList = ContextCompat.getColorStateList(context, colorRes)
    }

    fun show() {
        if (!isShown()) {
            handler.post {
                mLayout?.visibility = View.VISIBLE
            }
        }
    }

    fun hide() {
        if (isShown()) {
            handler.post {
                mLayout?.visibility = View.GONE
            }
        }
    }

    private fun isShown() = mLayout?.visibility == View.VISIBLE

    companion object {
        const val TAG = "ProgressBarManager"
        private const val PROGRESS_BAR_ID = 9876
    }
}