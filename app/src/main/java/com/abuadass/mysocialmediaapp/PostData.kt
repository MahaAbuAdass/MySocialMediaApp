package com.abuadass.mysocialmediaapp

data class PostData(
    var postID: String,
    var userProfilePictureURL: String,
    var username: String,
    var postImageURL: String,
    var comments: MutableList<CommentData>
)
