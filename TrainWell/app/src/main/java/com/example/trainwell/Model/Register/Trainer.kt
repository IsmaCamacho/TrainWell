package com.example.trainwell.Model.Register

data class Trainer(
    val userId:String = "",
    val biography: String,
    val price: Double,
    val average: Double? = null,
    var specializations: String
)
