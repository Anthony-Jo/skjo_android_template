package com.skjo.skjo_android_template.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.core.net.toUri
import com.skjo.skjo_android_template.R
import java.io.File

class ImagePicker(private val activity: ComponentActivity, private val context: Context) {
    private var callback: ((ArrayList<Uri>) -> Unit)? = null
    private var file: File? = null

    fun setOnCallback(callback: (ArrayList<Uri>) -> Unit) {
        this.callback = callback
    }

    /**
     * Image 촬영, 선택 Intent
     * OS 기본 카메라, OS 기본 갤러리, 파일 선택기
     */
    @SuppressLint("IntentReset")
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
        val mimeTypes = arrayListOf("image/*")
        /**
         * Device 내부 이미지 선택 intent
         */
        val photoPickerIntent = Intent()

        /**
         * OS 기본 갤러리 Intent
         */
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        galleryIntent.type = fileType

        /**
         * 1개 이미지 선택
         */
        if (maxImageCount == 1) {
            /**
             * Android 13 (SDK 33) 이상 Photo Picker 사용
             */
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                photoPickerIntent.apply {
                    action = MediaStore.ACTION_PICK_IMAGES
                    type = fileType
                }
            } else {
                /**
                 * Android 12 (SDK 31) 이하 ACTION_GET_CONTENT 사용
                 */
                photoPickerIntent.action = Intent.ACTION_GET_CONTENT
                photoPickerIntent.type = fileType
                photoPickerIntent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
            }
        } else {
            /**
             * 2개 이상 이미지 선택
             */
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                /**
                 * Android 13 (SDK 33) 이상 Photo Picker 사용
                 */
                photoPickerIntent.apply {
                    putExtra(MediaStore.EXTRA_PICK_IMAGES_MAX, maxImageCount)
                    type = fileType
                }
            } else {
                /**
                 * Android 12 (SDK 31) 이하 ACTION_GET_CONTENT 사용
                 */
                photoPickerIntent.action = Intent.ACTION_GET_CONTENT
                photoPickerIntent.type = fileType
                photoPickerIntent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
                photoPickerIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            }
            galleryIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        }

        val chooserIntent = Intent.createChooser(galleryIntent, context.getString(R.string.image_picker_title))
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(cameraIntent, photoPickerIntent))
        chooserIntentResult.launch(chooserIntent)
    }


    private val chooserIntentResult =
        activity.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val singleUri = result.data?.data
                val clipData = result.data?.clipData
                val size = clipData?.itemCount
                if (clipData != null && size != null) {
                    /**
                     * Multi Image Pick
                     */
                    setIntentCallback(clipData, size)
                } else if (singleUri != null) {
                    /**
                     * Single Image Pick
                     */
                    val uriList = arrayListOf(singleUri)
                    callback?.let {
                        it(uriList)
                    }
                } else {
                    /**
                     * Camera Capture
                     */
                    val uriList: ArrayList<Uri> = arrayListOf()
                    val uri = file?.toUri()
                    if (uri != null) {
                        uriList.add(uri)
                    }
                    callback?.let {
                        it(uriList)
                    }
                }
            } else {
                callback?.let {
                    it(arrayListOf())
                }
            }
        }

    /**
     * Multi Image Pick 처리
     */
    private fun setIntentCallback(data: ClipData, size: Int) {
        val uriList: ArrayList<Uri> = arrayListOf()
        for (i in 0 until size) {
            uriList.add(data.getItemAt(i).uri)
        }

        callback?.let { it(uriList) }
    }
}