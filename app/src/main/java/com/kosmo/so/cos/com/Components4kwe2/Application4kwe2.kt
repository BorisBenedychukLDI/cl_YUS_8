package com.kosmo.so.cos.com.Components4kwe2

import android.app.Application
import android.util.Log
import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLib
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.kosmo.so.cos.com.BuildConfig
import com.kosmo.so.cos.com.Utils4kwe2.URLParams4kwe2.APPSFLYER_UID_4kwe2
import com.kosmo.so.cos.com.Utils4kwe2.URLParams4kwe2.GAID4kwe2
import com.kosmo.so.cos.com.Utils4kwe2.URLParams4kwe2.adGroup4kwe2
import com.kosmo.so.cos.com.Utils4kwe2.URLParams4kwe2.adSet4kwe2
import com.kosmo.so.cos.com.Utils4kwe2.URLParams4kwe2.afChannel4kwe2
import com.kosmo.so.cos.com.Utils4kwe2.URLParams4kwe2.campParam24kwe2
import com.kosmo.so.cos.com.Utils4kwe2.URLParams4kwe2.campParam34kwe2
import com.kosmo.so.cos.com.Utils4kwe2.URLParams4kwe2.key4kwe2
import com.kosmo.so.cos.com.Utils4kwe2.URLParams4kwe2.mediaSource4kwe2
import com.kosmo.so.cos.com.Utils4kwe2.URLParams4kwe2.status4kwe2
import com.kosmo.so.cos.com.Utils4kwe2.UtilPackage4kwe2.decodeBase64ToString4kwe2
import com.onesignal.OneSignal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Application4kwe2 : Application(){

    override fun onCreate() {
        super.onCreate()
        appsFlyerSetup4kwe2()
        mobileAdsSetup4kwe2()
        oneSignalSetup4kwe2()
    }

    private fun appsFlyerSetup4kwe2 () {
        AppsFlyerLib.getInstance().run {
            val appsFlyerConversionListener4kwe2 = object : AppsFlyerConversionListener {
                override fun onConversionDataSuccess(dataMap4kwe2: MutableMap<String, Any>?) {
                    dataMap4kwe2?.run {
                        map {
                            Log.d("Map_Data_AF", "Attribute ${it.key}: ${it.value}")
                        }

                        status4kwe2 = getValue(APPSFLYER_STATUS_TAG_4kwe2).toString()

                        getValue(APPSFLYER_CAMPAIGN_TAG_4kwe2).toString()
                            .split("||")
                            .drop(1)
                            .map {
                                it.split(":")[1]
                            }.also {
                                key4kwe2 = if (it.size >= 1) it[0] else EMPTY_TAG_4kwe2
                                campParam24kwe2 = if (it.size >= 2) it[1] else EMPTY_TAG_4kwe2
                                campParam34kwe2 = if (it.size >= 3) it[2] else EMPTY_TAG_4kwe2
                            }

                        adGroup4kwe2 = getValue(APPSFLYER_ADGROUP_TAG_4kwe2).toString()
                        adSet4kwe2 = getValue(APPSFLYER_ADSET_TAG_4kwe2).toString()
                        mediaSource4kwe2 = getValue(APPSFLYER_MEDIA_SOURCE_TAG_4kwe2).toString()
                        afChannel4kwe2 = getValue(APPSFLYER_AF_CHANNEL_TAG_4kwe2).toString()



                    }
                }

                override fun onConversionDataFail(p0: String?) {
                }

                override fun onAppOpenAttribution(p0: MutableMap<String, String>?) {
                }

                override fun onAttributionFailure(p0: String?) {
                }
            }
            APPSFLYER_UID_4kwe2 = getAppsFlyerUID(applicationContext)
            init(
                BuildConfig.APPSFLYER_KEY.decodeBase64ToString4kwe2(),
                appsFlyerConversionListener4kwe2,
                applicationContext
            )
            start(applicationContext)
        }

    }

    private fun oneSignalSetup4kwe2 () {
        OneSignal.initWithContext(applicationContext)
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)
        OneSignal.setAppId(BuildConfig.ONE_SIGNAL_KEY.decodeBase64ToString4kwe2())
    }

    private fun mobileAdsSetup4kwe2 () {
        MobileAds.initialize(applicationContext)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                GAID4kwe2 = AdvertisingIdClient.getAdvertisingIdInfo(this@Application4kwe2)?.id
                Log.d("GAID_EXTRACT", GAID4kwe2.toString())
            } catch (e: Exception) {

            }
        }
    }

    companion object {

        const val EMPTY_TAG_4kwe2:String = "empty"

        private const val APPSFLYER_CAMPAIGN_TAG_4kwe2 = "campaign"
        private const val APPSFLYER_STATUS_TAG_4kwe2 = "af_status"
        private const val APPSFLYER_ADGROUP_TAG_4kwe2 = "adgroup"
        private const val APPSFLYER_ADSET_TAG_4kwe2 = "adset"
        private const val APPSFLYER_AF_CHANNEL_TAG_4kwe2 = "af_channel"
        private const val APPSFLYER_MEDIA_SOURCE_TAG_4kwe2 = "media_source"

    }
}