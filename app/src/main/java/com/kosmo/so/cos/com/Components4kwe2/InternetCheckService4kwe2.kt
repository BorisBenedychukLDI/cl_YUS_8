package com.kosmo.so.cos.com.Components4kwe2

import android.app.Service
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.IBinder
import kotlinx.coroutines.*

class InternetCheckService4kwe2 : Service() {


    override fun onStartCommand(intent4kwe2: Intent?, flags4kwe2: Int, startId4kwe2: Int): Int {
        CoroutineScope(Dispatchers.Main).launch {
            while (true) {
                val intent4kwe2 = Intent().also {
                    it.action = INTERNET_CHECK_4KWE2
                    it.putExtra(INTERNET_CHECK_4KWE2, isNetworkPresent4kwe2())
                }
                sendBroadcast(intent4kwe2)
                delay(500)
            }
        }
        return START_STICKY
    }

    override fun onBind(p0: Intent?): IBinder? = null

    private fun isNetworkPresent4kwe2(): Boolean {
        val connectivityManager4kwe2 = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val networkCapabilities4kwe2 = connectivityManager4kwe2.getNetworkCapabilities(connectivityManager4kwe2.activeNetwork) ?: return false
            return networkCapabilities4kwe2.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        } else {
            for (network4kwe2 in connectivityManager4kwe2.allNetworks) {
                connectivityManager4kwe2.getNetworkInfo(network4kwe2)?.let {
                    if (it.isConnected) return true
                }
            }
            return false
        }
    }
    companion object {
        const val INTERNET_CHECK_4KWE2: String = "INTERNET_CHECK"
    }
}