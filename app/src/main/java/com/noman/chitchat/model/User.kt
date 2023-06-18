package com.noman.chitchat.model

data class User(
    val userList: List<Users>
){
    data class Users(
        val id: Int,
        val name: String,
        val email: String,
        val phone: String,
        val image: Int
    )
}
