package com.example.peyademoapp.model

data class Product(
    val _id: String,
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: String,
    val hasDrink: Boolean,
)