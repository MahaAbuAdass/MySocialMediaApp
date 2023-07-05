package com.abuadass.mysocialmediaapp

data class UserData(
    var userID: String,
    var name: String,
    var username: String,
    var email: String,
    var password: String,
    var profilePictureURL: String,
    var bio: String,
    var gender: String,
    var savedPosts: MutableList<String>,
    var likedPosts: MutableList<String>
)