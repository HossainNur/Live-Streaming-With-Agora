package com.example.livestreamingagora.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.livestreamingagora.ACCESS_TOKEN
import com.example.livestreamingagora.databinding.ActivitySplashSreenBinding
import com.example.livestreamingagora.getSharedPref

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySplashSreenBinding
    private val SPLASH_TIME_OUT: Long = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashSreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val token = getSharedPref(applicationContext, ACCESS_TOKEN)

        Handler().postDelayed({
            if (token != null){
                startActivity(Intent(applicationContext, LiveActivity::class.java))
                finish()
            }else{
                startActivity(Intent(applicationContext, LoginActivity::class.java))
                finish()
            }

        }, SPLASH_TIME_OUT)

    }
}