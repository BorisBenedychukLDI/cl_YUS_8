package com.ker.win.joker.cosmoyus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ker.win.joker.cosmoyus.Utils4kwe2.UtilPackage4kwe2

class WebViewActivity4kwe2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 0) {
            if (UtilPackage4kwe2.filePathCallBack4kwe2 != null) {
                val uriResult4kwe2 =
                    if (data  == null || resultCode != RESULT_OK) null else data.data
                if (uriResult4kwe2 != null) {
                    UtilPackage4kwe2.filePathCallBack4kwe2?.onReceiveValue(arrayOf(uriResult4kwe2))
                } else {
                    UtilPackage4kwe2.filePathCallBack4kwe2?.onReceiveValue(arrayOf(UtilPackage4kwe2.uriView4kwe2))
                }
                UtilPackage4kwe2.filePathCallBack4kwe2 = null
            }
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}