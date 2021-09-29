package com.kosmo.so.cos.com.CustomWebViewElements4kwe2

import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.core.content.FileProvider
import com.kosmo.so.cos.com.Utils4kwe2.UtilPackage4kwe2
import com.kosmo.so.cos.com.Utils4kwe2.UtilPackage4kwe2.checkPermissions4kwe2
import java.io.File
import java.util.*

class CustomWebChromeClient4kwe2(private val methods4kwe2: CustomWebChromeMethods4kwe2) : WebChromeClient() {


    override fun onShowFileChooser(
        webView4kwe2: WebView?,
        filePathCallback4kwe2: ValueCallback<Array<Uri>>?,
        fileChooserParams4kwe2: FileChooserParams?
    ): Boolean {
        methods4kwe2.getContext4kwe2().checkPermissions4kwe2()
        UtilPackage4kwe2.filePathCallBack4kwe2 = filePathCallback4kwe2
        val captureIntent4kwe2 = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (captureIntent4kwe2.resolveActivity(methods4kwe2.getContext4kwe2().packageManager) != null) {
            val providedFile4kwe2 = createTempFile4kwe2()
            UtilPackage4kwe2.uriView4kwe2 = FileProvider.getUriForFile(
                methods4kwe2.getContext4kwe2(),
                "${methods4kwe2.getContext4kwe2().applicationContext.packageName}.provider",
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
            methods4kwe2.startForResult4kwe2(intentChooser4kwe2, 0)
            return true

        }
        return super.onShowFileChooser(webView4kwe2, filePathCallback4kwe2, fileChooserParams4kwe2)
    }

    private fun createTempFile4kwe2(): File {
        val date4kwe2 = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val fileDir4kwe2 = methods4kwe2.getContext4kwe2().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("TMP${date4kwe2}_4kwe2", ".jpg", fileDir4kwe2)
    }
}