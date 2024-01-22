package com.skjo.skjo_android_template.utils

import android.content.Intent
import android.os.Build
import android.os.Parcelable
import java.io.Serializable

/**
 * Intent 간 Parcelable or Serializable Data 전달 Util
 */
object ParcelableUtil {
    @Suppress("DEPRECATION")
    fun <T : Parcelable> getParcelableExtra(intent: Intent, name: String, parcelable: Class<T>): T? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(name, parcelable)
        } else {
            intent.getParcelableExtra(name) as T?
        }
    }

    @Suppress("DEPRECATION")
    fun <T : Parcelable> getParcelableArrayListExtra(intent: Intent, name: String, parcelable: Class<T>): ArrayList<T>? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableArrayListExtra(name, parcelable)
        } else {
            intent.getParcelableArrayListExtra(name)
        }
    }

    @Suppress("DEPRECATION")
    inline fun <reified T : Serializable?> getSerializableExtra(intent: Intent, name: String, serializable: Class<T>): T? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra(name, serializable)
        } else {
            if (intent.getSerializableExtra(name) is T) {
                intent.getSerializableExtra(name) as T
            } else {
                null
            }
        }
    }
}