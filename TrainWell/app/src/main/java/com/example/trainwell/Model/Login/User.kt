package com.example.trainwell.Model.Login

data class User(
    val userId:String,
    val passwd:String,
    val name:String,
    val email:String,
    val role:String,
    val dateRegister: String
)
