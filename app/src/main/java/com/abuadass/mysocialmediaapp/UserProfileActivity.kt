package com.abuadass.mysocialmediaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth

class UserProfileActivity : AppCompatActivity() {

    private lateinit var userID: String
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)
        auth = FirebaseAuth.getInstance()

        userID = intent.getStringExtra("id")!! // Here we are fetching the user ID coming from the HomePageActivity

        if(userID == auth.currentUser?.uid){
            // This is the same user (Open User's Own Profile)
        }else{
            // This is another user's profile page
        }
    }
}