package com.ker.win.joker.cosmoyus.Utils4kwe2

import android.content.SharedPreferences

object SharedPreferenceCommands4kwe2 {

    const val spCreationTag = "SP_4kwe2"

    fun SharedPreferences.putLastPage (str: String)  {
        edit().putString("Last_Page", str).apply()
    }

    fun SharedPreferences.getLastPage () = getString("Last_Page", null)

    fun SharedPreferences.putWebViewURL (str: String) {
        edit().putString("WebViewURL", str).apply()
    }

    fun SharedPreferences.getWebViewURL () = getString("WebViewURL", null)

}