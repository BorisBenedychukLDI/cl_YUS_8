package com.ker.win.joker.cosmoyus

import android.content.Context
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings

class SplashPresenter4kwe2(val context: Context, val methods: SplashMethods4kwe2) {

    companion object {
        const val firebaseDefaultKey4kwe2 = ""
        const val firebaseBlackKey4kwe2 = ""
        const val firebaseWhiteKey4kwe2 = ""

        const val firebaseWhiteVal4kwe2 = ""
        const val firebaseBlackVal4kwe2 = ""
        const val firebaseDefaultVal4kwe2 = ""

    }

    private fun setupFirebaseConfigs4kwe2()  {
        Firebase.remoteConfig.run {
            setConfigSettingsAsync(
                remoteConfigSettings {
                    minimumFetchIntervalInSeconds = 1000
                }
            )
            setDefaultsAsync(
                mapOf(
            ))
        }
    }
}