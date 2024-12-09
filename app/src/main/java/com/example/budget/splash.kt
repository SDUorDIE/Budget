package com.example.budget

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)

        class SplashActivity : AppCompatActivity() {
            // Splash screen duration in milliseconds
            private val SPLASH_DISPLAY_LENGTH = 2000L

            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_splash)


                Handler(Looper.getMainLooper()).postDelayed({

                    val mainIntent = Intent(this, MainActivity::class.java)
                    startActivity(mainIntent)
                    finish()
                }, SPLASH_DISPLAY_LENGTH)
            }
        }
    }
}