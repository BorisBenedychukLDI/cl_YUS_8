package com.kosmo.so.cos.com.Utils4kwe2

import android.content.SharedPreferences

object SharedPreferenceCommands4kwe2 {

    const val spCreationTag4kwe2 = "SP_4kwe2"

    fun SharedPreferences.putLastPage4kwe2 (str4kwe2: String)  {
        edit().putString("Last_Page", str4kwe2).apply()
    }

    fun SharedPreferences.getLastPage4kwe24kwe2 () = getString("Last_Page", null)

    fun SharedPreferences.putWebViewURL4kwe2 (str4kwe2: String) {
        edit().putString("WebViewURL", str4kwe2).apply()
    }

    fun SharedPreferences.getWebViewURL4kwe2 () = getString("WebViewURL", null)

}