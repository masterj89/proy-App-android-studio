package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

class splashActivity: AppCompatActivity() {

    private val SPLASH_TIME_OUT: Long = 3000

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.splash)

        Handler(Looper.getMainLooper()).postDelayed({
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }, SPLASH_TIME_OUT)
}
}