package com.abuadass.mysocialmediaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var emailEt: EditText
    private lateinit var passwordEt: EditText
    private lateinit var loginBtn: Button
    private lateinit var goToSignUpPageTv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        emailEt = findViewById(R.id.emailEt)
        passwordEt = findViewById(R.id.passwordEt)
        loginBtn = findViewById(R.id.loginBtn)
        goToSignUpPageTv = findViewById(R.id.openSignUpPageTv)

        loginBtn.setOnClickListener(this)
        goToSignUpPageTv.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if(v?.id == R.id.loginBtn){
            /// The user has clicked on Login Button
            loginUser()
        }else if(v?.id == R.id.openSignUpPageTv){
            /// The user has clicked on Click To Sign up
            openSignUpPage()
        }
    }

    private fun openSignUpPage() {
        //Toast.makeText(this, "Sign up Button Clicked", Toast.LENGTH_LONG).show()
        startActivity(Intent(this, SignUpActivity::class.java))
    }

    private fun loginUser() {
        Toast.makeText(this, "Login Button Clicked", Toast.LENGTH_LONG).show()
    }
}