package com.kosmo.so.cos.com

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.lifecycle.lifecycleScope
import com.kosmo.so.cos.com.SplashScreen4kwe2.SplashMethods4kwe2
import com.kosmo.so.cos.com.SplashScreen4kwe2.SplashPresenter4kwe2
import com.kosmo.so.cos.com.Utils4kwe2.SharedPreferenceCommands4kwe2
import com.kosmo.so.cos.com.Utils4kwe2.SharedPreferenceCommands4kwe2.getLastPage4kwe24kwe2
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashActivity4kwe2 : AppCompatActivity(), SplashMethods4kwe2 {

    private lateinit var presenter4kwe2: SplashPresenter4kwe2
    private lateinit var buttonStart4kwe2: Button
    private lateinit var imageView4kwe2: ImageView
    private lateinit var progressBar4kwe2: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_4kwe2)
        loadSharedPreferences4kwe2().getLastPage4kwe24kwe2()?.run {
            startActivity4kwe2()
        }
        presenter4kwe2 = SplashPresenter4kwe2(this)
        buttonStart4kwe2 = findViewById(R.id.button_4kwe2)
        imageView4kwe2 = findViewById(R.id.iv_rocket_4kwe2)
        progressBar4kwe2 = findViewById(R.id.pb_loading_progress_4kwe2)
        progressBar4kwe2.progress = 0
        presenter4kwe2.setupFirebaseConfigs4kwe2()
        buttonStart4kwe2.setOnClickListener {
            animateSetup4kwe2()
        }
    }


    private fun animateSetup4kwe2() {
        lifecycleScope.launch {
            buttonStart4kwe2.isClickable = false
            launch {
                for (i in 3 downTo 1) {
                    buttonStart4kwe2.text = "$i"
                    delay(1000)
                }
                buttonStart4kwe2.text = resources.getString(R.string.lets_go_4kwe2)
                ObjectAnimator.ofFloat(imageView4kwe2, View.TRANSLATION_Y, 0f, -1500f).start()
            }
            progressBar4kwe2.run {
                max = 100
                while (progress < max) {
                    progress++
                    delay(50)
                }
            }
            presenter4kwe2.setupUrlLink4kwe2()
        }
    }

    override fun startActivity4kwe2() {
        startActivity(Intent(this, WebViewActivity4Kwe24kwe2::class.java))
        finish()
    }

    override fun loadPackageName4kwe2(): String = packageName

    override fun loadSharedPreferences4kwe2(): SharedPreferences = getSharedPreferences(SharedPreferenceCommands4kwe2.spCreationTag4kwe2, MODE_PRIVATE)
}