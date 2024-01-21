package com.skjo.skjo_android_template.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.core.content.FileProvider
import java.io.File

class ImagePicker(val activity: ComponentActivity, val context: Context) {
    private var callback: ((ArrayList<Uri>) -> Unit)? = null
    private var file: File? = null

    fun setOnCallback(callback: (ArrayList<Uri>) -> Unit) {
        this.callback = callback
    }

    fun showPickImageIntent(maxImageCount: Int) {
        if (maxImageCount < 1) {
            callback?.let { it(arrayListOf()) }
            return
        }

        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        val tempDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        file = File.createTempFile("temp_image", ".jpg", tempDir)

        val cameraUri = FileProvider.getUriForFile(
            context,
            context.applicationContext.packageName + ".provider",
            this.file!!
        )

        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, cameraUri)

        val fileType = "image/*"
        val photoPickerIntent = Intent()
        if (maxImageCount == 1) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                photoPickerIntent.apply {
                    action = MediaStore.ACTION_PICK_IMAGES
                    type = fileType
                }
            }
        } else {

        }


    }
}