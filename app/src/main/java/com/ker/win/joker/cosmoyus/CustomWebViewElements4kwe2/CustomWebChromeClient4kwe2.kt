package com.ker.win.joker.cosmoyus.CustomWebViewElements4kwe2

import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.core.content.FileProvider
import com.ker.win.joker.cosmoyus.Utils4kwe2.UtilPackage4kwe2
import com.ker.win.joker.cosmoyus.WebViewActivity4kwe2
import java.io.File
import java.util.*

class CustomWebChromeClient4kwe2(private val activity: WebViewActivity4kwe2) : WebChromeClient() {



    override fun onShowFileChooser(
        webView: WebView?,
        filePathCallback: ValueCallback<Array<Uri>>?,
        fileChooserParams: FileChooserParams?
    ): Boolean {
        UtilPackage4kwe2.filePathCallBack4kwe2 = filePathCallback
        val captureIntent4kwe2 = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (captureIntent4kwe2.resolveActivity(activity.packageManager) != null) {
            val providedFile4kwe2 = createTempFile4kwe2()
            UtilPackage4kwe2.uriView4kwe2 = FileProvider.getUriForFile(
                activity,
                "${activity.applicationContext.packageName}.provider",
                providedFile4kwe2
            )
            captureIntent4kwe2.run {
                putExtra(MediaStore.EXTRA_OUTPUT, UtilPackage4kwe2.uriView4kwe2)
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }
            val actionIntent4kwe2 = Intent(Intent.ACTION_GET_CONTENT).apply {
                addCategory(Intent.CATEGORY_OPENABLE)
                type = "image/*"
            }
            val intentChooser4kwe2 = Intent(Intent.ACTION_CHOOSER).apply {
                putExtra(Intent.EXTRA_INTENT, captureIntent4kwe2)
                putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(actionIntent4kwe2) )
            }
            activity.startActivityForResult(intentChooser4kwe2, 0)
            return true

        }
        return super.onShowFileChooser(webView, filePathCallback, fileChooserParams)
    }

    private fun createTempFile4kwe2(): File {
        val date4kwe2 = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val fileDir4kwe2 = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("TMP${date4kwe2}_4kwe2", ".jpg", fileDir4kwe2)
    }
}