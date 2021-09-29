package com.kosmo.so.cos.com

import android.animation.ObjectAnimator
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.cardview.widget.CardView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.kosmo.so.cos.com.Components4kwe2.InternetCheckService4kwe2
import com.kosmo.so.cos.com.CustomWebViewElements4kwe2.CustomWebChromeClient4kwe2
import com.kosmo.so.cos.com.CustomWebViewElements4kwe2.CustomWebView4kwe2
import com.kosmo.so.cos.com.CustomWebViewElements4kwe2.CustomWebChromeMethods4kwe2
import com.kosmo.so.cos.com.Utils4kwe2.UtilPackage4kwe2

class WebViewActivity4Kwe24kwe2 : AppCompatActivity(), CustomWebChromeMethods4kwe2 {

    private lateinit var customWebView4kwe2: CustomWebView4kwe2

    private lateinit var cardView4kwe2: CardView
    private lateinit var swipeRefreshLayout4kwe2: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        customWebView4kwe2 = findViewById(R.id.custom_wv_4kwe2)
        cardView4kwe2 = findViewById(R.id.cv_internet_dialog_4kwe2)
        swipeRefreshLayout4kwe2 = findViewById(R.id.srl_wv_4kwe2)
        swipeRefreshLayout4kwe2.setOnRefreshListener {
            customWebView4kwe2.loadUrl(customWebView4kwe2.url ?: return@setOnRefreshListener)
            swipeRefreshLayout4kwe2.isRefreshing = false
        }
        customWebView4kwe2.setWebChromeClientWithCustom(CustomWebChromeClient4kwe2(this))
        startService(Intent(this, InternetCheckService4kwe2::class.java))
    }


    override fun onActivityResult(requestCode4kwe2: Int, resultCode4kwe2: Int, data4kwe2: Intent?) {
        if (requestCode4kwe2 == 0) {
            if (UtilPackage4kwe2.filePathCallBack4kwe2 != null) {
                val uriResult4kwe2 =
                    if (data4kwe2  == null || resultCode4kwe2 != RESULT_OK) null else data4kwe2.data
                if (uriResult4kwe2 != null) {
                    UtilPackage4kwe2.filePathCallBack4kwe2?.onReceiveValue(arrayOf(uriResult4kwe2))
                } else {
                    UtilPackage4kwe2.filePathCallBack4kwe2?.onReceiveValue(arrayOf(UtilPackage4kwe2.uriView4kwe2))
                }
                UtilPackage4kwe2.filePathCallBack4kwe2 = null
            }
            super.onActivityResult(requestCode4kwe2, resultCode4kwe2, data4kwe2)
        }
    }

    override fun onResume() {
        registerReceiver(broadcastReceiver4kwe2, intentFilter4kwe2)
        super.onResume()
    }

    override fun onPause() {
        registerReceiver(broadcastReceiver4kwe2, intentFilter4kwe2)
        super.onPause()
    }

    override fun onDestroy() {
        stopService(Intent(this, InternetCheckService4kwe2::class.java))
        super.onDestroy()
    }

    private var broadcastReceiver4kwe2 = object : BroadcastReceiver() {
        override fun onReceive(p04kwe2: Context?, p14kwe2: Intent?) {
            p14kwe2?.let {
                if (it.action == InternetCheckService4kwe2.INTERNET_CHECK_4KWE2) {
                    if (!it.getBooleanExtra(InternetCheckService4kwe2.INTERNET_CHECK_4KWE2, true)) {
                        showInternetProblems4kwe2()
                    } else {
                        hideInternetProblems4kwe2()
                    }
                }
            }
        }
    }

    private var intentFilter4kwe2 = IntentFilter().apply {
        addAction(InternetCheckService4kwe2.INTERNET_CHECK_4KWE2)
    }

    private fun showInternetProblems4kwe2() {
        if (UtilPackage4kwe2.networkPresence4kwe2) {
            ObjectAnimator.ofFloat(cardView4kwe2, View.TRANSLATION_Y, 0f, 150f).start()
            UtilPackage4kwe2.networkPresence4kwe2 = false
        }
    }

    private fun hideInternetProblems4kwe2() {
        if (!UtilPackage4kwe2.networkPresence4kwe2) {
            ObjectAnimator.ofFloat(cardView4kwe2, View.TRANSLATION_Y, 150f, 0f).start()
            UtilPackage4kwe2.networkPresence4kwe2 = true
        }
    }

    override fun onBackPressed() {
        if (customWebView4kwe2.canGoBack() && customWebView4kwe2.isFocused) {
            customWebView4kwe2.goBack()
        } else {
            super.onBackPressed()
        }
    }

    override fun getContext4kwe2(): Context = this

    override fun startForResult4kwe2(intent: Intent, resultCode: Int) {
        startActivityForResult(intent, resultCode)
    }
}