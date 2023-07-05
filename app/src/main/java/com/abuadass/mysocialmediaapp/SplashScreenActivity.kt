package com.abuadass.mysocialmediaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.google.firebase.auth.FirebaseAuth

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var auth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        auth = FirebaseAuth.getInstance()
        startDelayBeforeProceeding()
    }

    private fun startDelayBeforeProceeding() {
        val timer = object : CountDownTimer(3000, 500) {
            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                if(auth.currentUser != null){
                    startActivity(Intent(this@SplashScreenActivity, HomePageActivity::class.java))
                }else{
                    startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
                }
                finish()
            }
        }
        timer.start()
    }
}