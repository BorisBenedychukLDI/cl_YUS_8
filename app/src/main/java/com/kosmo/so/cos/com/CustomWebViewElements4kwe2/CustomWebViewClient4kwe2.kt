package com.kosmo.so.cos.com.CustomWebViewElements4kwe2

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.graphics.Bitmap
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.kosmo.so.cos.com.Utils4kwe2.SharedPreferenceCommands4kwe2
import com.kosmo.so.cos.com.Utils4kwe2.SharedPreferenceCommands4kwe2.putLastPage4kwe2

class CustomWebViewClient4kwe2 : WebViewClient() {

    override fun shouldOverrideUrlLoading(view4kwe2: WebView?, request4kwe2: WebResourceRequest?): Boolean {
        val prohibitedLinks4kwe2 = listOf("facebook","twitter")
        val modifiedLinks4kwe2 = listOf ("mailto:","tel:")
        return when {
            modifiedLinks4kwe2.find { request4kwe2?.url.toString().startsWith(it) } != null -> {
                view4kwe2?.context?.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        request4kwe2?.url
                    )
                )
                true
            }
            prohibitedLinks4kwe2.find { request4kwe2?.url.toString().contains(it) } != null ->
                true
            else -> false
        }
    }

    override fun onPageFinished(view4kwe2: WebView?, url4kwe2: String?) {
        view4kwe2?.context?.getSharedPreferences(SharedPreferenceCommands4kwe2.spCreationTag4kwe2, MODE_PRIVATE)?.run {
            putLastPage4kwe2(url4kwe2 ?: return@run)
        }
        super.onPageFinished(view4kwe2, url4kwe2)
    }
}