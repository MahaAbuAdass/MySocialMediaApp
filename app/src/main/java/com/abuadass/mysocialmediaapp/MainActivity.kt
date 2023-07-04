package com.abuadass.mysocialmediaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.LinearLayoutCompat
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var emailEt: EditText
    private lateinit var passwordEt: EditText
    private lateinit var loginBtn: Button
    private lateinit var goToSignUpPageTv: TextView
    private lateinit var loader: LinearLayoutCompat

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()

        emailEt = findViewById(R.id.emailEt)
        passwordEt = findViewById(R.id.passwordEt)
        loginBtn = findViewById(R.id.loginBtn)
        goToSignUpPageTv = findViewById(R.id.openSignUpPageTv)
        loader = findViewById(R.id.lL_loader)

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
        loader.visibility = View.VISIBLE

        val email = emailEt.text.toString()
        val password = passwordEt.text.toString()

        if(email.isEmpty()){
            showDialog("Please enter your email address", "Empty email address")
            return
        }
        if(password.isEmpty()){
            showDialog("Please enter your password", "Empty password")
            return
        }

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener{request->
            if(request.isSuccessful){
                // The user is successfully signed in
                loader.visibility = View.GONE
                finish()
                startActivity(Intent(this, HomePageActivity::class.java))

            }else{
                // The user is not successfully signed in
                showDialog(request.exception?.message.toString(), "Log in failed")
            }
        }
    }

    private fun showDialog(message: String, title: String){
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle(title)
        dialogBuilder.setMessage(message)
        dialogBuilder.setPositiveButton("OK"){dialog, which->
            // Do something when the "OK" button is clicked
            dialog.dismiss()
        }
        val dialog = dialogBuilder.create()
        dialog.show()

        loader.visibility = View.GONE
    }
}