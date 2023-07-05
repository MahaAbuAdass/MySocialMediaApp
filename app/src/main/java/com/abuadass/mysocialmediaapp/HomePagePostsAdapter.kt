package com.abuadass.mysocialmediaapp

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
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
        val currentPost = postsList[position]
        holder.usernameText.text = currentPost.username

        Glide.with(holder.itemView).load(currentPost.postImageURL).into(holder.postImage)
        Glide.with(holder.itemView).load(currentPost.userProfilePictureURL).into(holder.userImage)
    }

    class PostsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var userImage: CircleImageView = itemView.findViewById(R.id.iv_user_post_image)
        var usernameText: TextView = itemView.findViewById(R.id.tv_user_post_name)
        var postImage: ImageView = itemView.findViewById(R.id.imageView)
        var likeButton: ImageView = itemView.findViewById(R.id.iv_like_post_button)
        var commentButton: ImageView = itemView.findViewById(R.id.iv_comment_post_button)
        var shareButton : ImageView = itemView.findViewById(R.id.iv_share_post_button)
        var saveButton : ImageView = itemView.findViewById(R.id.iv_save_post_button)
        var viewCommentButton : TextView = itemView.findViewById(R.id.tv_view_post_comments_button)
    }
}