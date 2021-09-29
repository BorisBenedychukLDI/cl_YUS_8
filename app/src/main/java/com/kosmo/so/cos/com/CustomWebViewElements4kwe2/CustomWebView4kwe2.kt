package com.kosmo.so.cos.com.CustomWebViewElements4kwe2

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.webkit.CookieManager
import android.webkit.WebSettings
import android.webkit.WebView
import com.kosmo.so.cos.com.Utils4kwe2.SharedPreferenceCommands4kwe2
import com.kosmo.so.cos.com.Utils4kwe2.SharedPreferenceCommands4kwe2.getLastPage4kwe24kwe2
import com.kosmo.so.cos.com.Utils4kwe2.SharedPreferenceCommands4kwe2.getWebViewURL4kwe2

@SuppressLint("ViewConstructor")
class CustomWebView4kwe2(context: Context, attrs: AttributeSet?) : WebView(context, attrs) {

    init {
        val cookiesManager4kwe2 = CookieManager.getInstance()
        CookieManager.setAcceptFileSchemeCookies(true)
        cookiesManager4kwe2.setAcceptThirdPartyCookies(this, true)

        scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
        settings.run {
            builtInZoomControls = true
            domStorageEnabled = true
            mediaPlaybackRequiresUserGesture = false
            javaScriptEnabled = true
            cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
            displayZoomControls = false
            useWideViewPort = true
            mediaPlaybackRequiresUserGesture = false
        }
        webViewClient = CustomWebViewClient4kwe2()
        loadUrl4kwe2()
    }

    private fun loadUrl4kwe2() {
        context.getSharedPreferences(SharedPreferenceCommands4kwe2.spCreationTag4kwe2, MODE_PRIVATE).run {
            if (getLastPage4kwe24kwe2() == null) {
//                loadUrl(getWebViewURL4kwe2()?: return@run)
                loadUrl("https://dropmefiles.com/")
                Log.d("WebViewURL_FINAL_TEST", getWebViewURL4kwe2() ?: return@run)
            } else {
                loadUrl(getLastPage4kwe24kwe2() ?: return@run)
            }
        }
    }

    fun setWebChromeClientWithCustom (webChromeClient: CustomWebChromeClient4kwe2) {
        this.webChromeClient = webChromeClient
    }
}