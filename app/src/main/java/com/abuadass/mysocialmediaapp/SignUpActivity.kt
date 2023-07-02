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

class SignUpActivity : AppCompatActivity() , OnClickListener {

    private lateinit var nameEt: EditText
    private lateinit var emailEt: EditText
    private lateinit var passwordEt: EditText
    private lateinit var confirmPassEt:EditText
    private lateinit var signupBtn: Button
    private lateinit var goTologinpageTv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        nameEt = findViewById(R.id.nameEt)
        emailEt = findViewById(R.id.emailEt)
        passwordEt=findViewById(R.id.passwordEt)
        confirmPassEt =findViewById(R.id.confirmPasswordEt)
        signupBtn = findViewById(R.id.signupBtn)
        goTologinpageTv = findViewById(R.id.openLoginPageTv)

        signupBtn.setOnClickListener(this)
        goTologinpageTv.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        if (p0?.id == R.id.signupBtn ) {
            signupUser()
        }else if (p0?.id == R.id.openLoginPageTv) {
        openLoginPage()
        }
        }

    private fun openLoginPage() {
       startActivity(Intent(this, MainActivity::class.java))
    }

    private fun signupUser() {
        Toast.makeText(this, "ٍSignup Button Clicked", Toast.LENGTH_LONG).show()
    }
}





