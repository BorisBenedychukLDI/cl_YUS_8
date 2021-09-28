package com.ker.win.joker.cosmoyus.CustomWebViewElements4kwe2

import android.content.Context.MODE_PRIVATE
import android.view.View
import android.webkit.CookieManager
import android.webkit.WebSettings
import android.webkit.WebView
import com.ker.win.joker.cosmoyus.Utils4kwe2.SharedPreferenceCommands4kwe2
import com.ker.win.joker.cosmoyus.Utils4kwe2.SharedPreferenceCommands4kwe2.getLastPage
import com.ker.win.joker.cosmoyus.WebViewActivity4kwe2

class CustomWebView4kwe2(private val activity: WebViewActivity4kwe2) : WebView(activity) {

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
        webChromeClient = CustomWebChromeClient4kwe2(activity)
        webViewClient = CustomWebViewClient4kwe2()
        loadUrl4kwe2()
    }

    private fun loadUrl4kwe2() {
        activity.getSharedPreferences(SharedPreferenceCommands4kwe2.spCreationTag, MODE_PRIVATE).let {
            if (it.getLastPage() == null) {
                this.loadUrl(activity.intent.getStringExtra("webViewURL") ?: return)
            } else {
                this.loadUrl(it.getLastPage()!!)
            }
        }
    }
}