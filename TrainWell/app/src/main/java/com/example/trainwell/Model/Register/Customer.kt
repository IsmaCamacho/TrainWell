package com.example.trainwell.Model.Register

data class Customer(
    val userId:String = "",
    val trainerId:String? = null,
    val goal:String,
    val weight: Double,
    val height: Int
)
