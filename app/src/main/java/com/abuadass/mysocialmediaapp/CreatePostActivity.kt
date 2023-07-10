package com.abuadass.mysocialmediaapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.AppCompatImageView
import de.hdodenhof.circleimageview.CircleImageView
class CreatePostActivity : AppCompatActivity(), OnClickListener {

    private lateinit var cancelButton: TextView
    private lateinit var addPostButton : TextView
    private lateinit var uploadpostimage : Button
    private lateinit var selectedImage: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)

        cancelButton = findViewById(R.id.tv_cancelpost)
        addPostButton = findViewById(R.id.tv_add_postbutton)
        uploadpostimage = findViewById(R.id.uploadpostimagebutton)
        selectedImage = findViewById(R.id.iv_userpost_image)

        cancelButton.setOnClickListener(this)
        addPostButton.setOnClickListener(this)
        uploadpostimage.setOnClickListener(this)
        selectedImage.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        if (p0?.id == R.id.tv_cancelpost) {
            openHomePageActivity()
        } else if (p0?.id == R.id.tv_add_postbutton) {
            submitPost()
        } else if (p0?.id == R.id.uploadpostimagebutton) {
            openUserGallery()
        }

    }

    private fun openUserGallery() {
        val pickImg = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        changeImage.launch(pickImg)
    }
    private fun submitPost(){
        // Upload the image to Firebase Storage.
        // Get the image URL from the Storage.
        // Save the image URL to DB.
        // Save the post on DB.
    }
    private fun openHomePageActivity() {
        startActivity(Intent(this, HomePageActivity::class.java))
    }
    private val changeImage =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { activityResponse->
            if (activityResponse.resultCode == Activity.RESULT_OK) {
                val data = activityResponse.data
                val imgUri = data?.data
                selectedImage.setImageURI(imgUri)
            }else{
                Toast.makeText(this@CreatePostActivity, "You did not pick an image", Toast.LENGTH_LONG).show()
            }
        }
}

