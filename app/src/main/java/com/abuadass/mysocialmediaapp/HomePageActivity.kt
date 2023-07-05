package com.abuadass.mysocialmediaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HomePageActivity : AppCompatActivity(), OnClickListener {

    private lateinit var myProfileButton: ImageView
    private lateinit var logoutButton: ImageView
    private lateinit var postsRecyclerView: RecyclerView
    private lateinit var loader: LinearLayoutCompat

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        auth = FirebaseAuth.getInstance()

        myProfileButton = findViewById(R.id.iv_my_profile_button)
        logoutButton = findViewById(R.id.iv_log_out_button)
        postsRecyclerView = findViewById(R.id.rv_posts)
        loader = findViewById(R.id.lL_loader)

        myProfileButton.setOnClickListener(this)
        logoutButton.setOnClickListener(this)

        getAllPostsFromDatabase()
    }
    private fun getAllPostsFromDatabase() {
        loader.visibility = View.VISIBLE
        val databaseRef = FirebaseDatabase.getInstance().reference.child("posts")
        databaseRef.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    val posts = mutableListOf<PostData>() // We created an empty list of posts (To fill it later with the real posts coming from DB)
                    snapshot.children.forEach{post-> // We are looping here on each item in the snapshot.children list (Which are the posts on the DB)
                        val postData: PostData = post.getValue(PostData::class.java)!! // We are mapping the JSON coming from the DB to our data class (PostData)
                        posts.add(postData)
                    }
                    addPostsToRecyclerView(posts)
                }else{
                    showDialog("There is no posts yet", "No posts found")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                showDialog(error.message, "An error occurred")
            }
        })
    }
    private fun addPostsToRecyclerView(posts: MutableList<PostData>) {

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
    override fun onClick(v: View?) {
        if(v?.id == R.id.iv_my_profile_button){
            openProfilePage()
        }else if(v?.id == R.id.iv_log_out_button){
            logoutUser()
        }
    }
    private fun openProfilePage() {
        val userID = auth.currentUser?.uid
        val intent = Intent(this, UserProfileActivity::class.java)
        intent.putExtra("id", userID)
        startActivity(intent)
    }
    private fun logoutUser() {
        auth.signOut()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}