package com.ker.win.joker.cosmoyus.CustomWebViewElements4kwe2

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.ker.win.joker.cosmoyus.Utils4kwe2.SharedPreferenceCommands4kwe2
import com.ker.win.joker.cosmoyus.Utils4kwe2.SharedPreferenceCommands4kwe2.putLastPage

class CustomWebViewClient4kwe2 : WebViewClient() {

    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        val prohibitedLinks4kwe2 = listOf("facebook","twitter","instagram","vk.com","youtube")
        val modifiedLinks4kwe2 = listOf ("mailto:","tel:")
        return when {
            modifiedLinks4kwe2.find { request?.url.toString().startsWith(it) } != null -> {
                view?.context?.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        request?.url
                    )
                )
                true
            }
            prohibitedLinks4kwe2.find { request?.url.toString().contains(it) } != null ->
                true
            else -> false
        }
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        view?.context?.getSharedPreferences(SharedPreferenceCommands4kwe2.spCreationTag, MODE_PRIVATE)?.run {
            putLastPage(url ?: return@run)
        }
        super.onPageFinished(view, url)
    }
}