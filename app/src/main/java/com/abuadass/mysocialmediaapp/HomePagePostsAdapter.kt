package com.abuadass.mysocialmediaapp

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView

class HomePagePostsAdapter(private val postsList: List<PostData>):
    RecyclerView.Adapter<HomePagePostsAdapter.PostsViewHolder>() {

    override fun getItemCount(): Int {
        // Returns the number of the items in the list (How many item should the Recycler View show?)
        return postsList.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        // Inflates (Shows) the actual record (The design, The template) that is used to show the data
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostsViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    class PostsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var userImage: CircleImageView = itemView.findViewById(R.id.iv_user_post_image)
        var usernameText: TextView = itemView.findViewById(R.id.tv_user_post_name)
        var postImage: ImageView = itemView.findViewById(R.id.imageView)

    }
}