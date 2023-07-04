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
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.LinearLayoutCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class SignUpActivity : AppCompatActivity(), OnClickListener {

    private lateinit var nameEt: EditText
    private lateinit var usernameEt: EditText
    private lateinit var emailEt: EditText
    private lateinit var passwordEt: EditText
    private lateinit var confirmPassEt: EditText
    private lateinit var signupBtn: Button
    private lateinit var goTologinpageTv: TextView
    private lateinit var loader: LinearLayoutCompat

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        auth = FirebaseAuth.getInstance()

        nameEt = findViewById(R.id.nameEt)
        emailEt = findViewById(R.id.emailEt)
        passwordEt = findViewById(R.id.passwordEt)
        confirmPassEt = findViewById(R.id.confirmPasswordEt)
        signupBtn = findViewById(R.id.signupBtn)
        goTologinpageTv = findViewById(R.id.openLoginPageTv)
        usernameEt = findViewById(R.id.et_username)
        loader = findViewById(R.id.lL_loader)

        signupBtn.setOnClickListener(this)
        goTologinpageTv.setOnClickListener(this)
    }
    override fun onClick(p0: View?) {
        if (p0?.id == R.id.signupBtn) {
            signupUser()
        } else if (p0?.id == R.id.openLoginPageTv) {
            openLoginPage()
        }
    }
    private fun openLoginPage() {
        startActivity(Intent(this, MainActivity::class.java))
    }
    private fun signupUser() {
        loader.visibility = View.VISIBLE

        val name = nameEt.text.toString()
        val email = emailEt.text.toString()
        val password = passwordEt.text.toString()
        val confirmPassword = confirmPassEt.text.toString()
        val username = usernameEt.text.toString()

        if(name.isEmpty()){
            showDialog("Please enter your name", "Empty name")
            return
        }
        if(username.isEmpty()){
            showDialog("Please enter your username", "Empty username")
            return
        }
        if(email.isEmpty()){
            showDialog("Please enter your email", "Empty email address")
            return
        }
        if(password.isEmpty()){
            showDialog("Please enter your password", "Empty password")
            return
        }
        if(confirmPassword != password){
            showDialog("Password and confirm password are not matched", "Passwords not matched")
            return
        }

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { request->
                if(request.isSuccessful){
                    // The user is successfully registered
                    saveUserDataToDatabase(name, email, password, username)
                }else{
                    // The user is not successfully registered
                    showDialog(request.exception?.message.toString() /*Built in messages from Firebase*/, "Signing up failed")
                }
            }
    }

    private fun saveUserDataToDatabase(
        name: String,
        email: String,
        password: String,
        username: String
    ) {
        val userID = auth.currentUser?.uid!!
        val userData = UserData(
            name = name,
            email = email,
            userID = userID,
            password = password,
            profilePictureURL = "",
            bio = "",
            gender = "",
            posts = mutableListOf(),
            likedPosts = mutableListOf(),
            savedPosts = mutableListOf(),
            username = username
        )

        val databaseRef = FirebaseDatabase.getInstance().reference.child("users").child(userID)

        databaseRef.setValue(userData)
            .addOnCompleteListener { request->
                if(request.isSuccessful){
                    // The user data has been successfully saved to the Database
                    loader.visibility = View.GONE
                    finish()
                    startActivity(Intent(this@SignUpActivity, MainActivity::class.java))
                }else{
                    // The user data has not been successfully saved to the Database
                    showDialog(request.exception?.message.toString(), "Failed to save user data")
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





