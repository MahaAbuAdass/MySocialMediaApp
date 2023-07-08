package com.abuadass.mysocialmediaapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.AppCompatImageView
import de.hdodenhof.circleimageview.CircleImageView

private lateinit var cancelButton: TextView
private lateinit var addPostButton : TextView
private lateinit var uploadpostimage : Button

private lateinit var selectedImage: CircleImageView


class CreatePostActivity : AppCompatActivity()  , OnClickListener {
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
            openHomePageActivity()
        } else if (p0?.id == R.id.uploadpostimagebutton) {
            openUserGallery()
        }

    }

    private fun openUserGallery() {
        val pickImg = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        changeImage.launch(pickImg)
    }

    private fun openHomePageActivity() {
        startActivity(Intent(this, HomePageActivity::class.java))

    }
    private val changeImage =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                val data = it.data
                val imgUri = data?.data
                selectedImage.setImageURI(imgUri)
            }
        }
}

