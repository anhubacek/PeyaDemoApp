package com.example.peyademoapp.model.dataclass

data class Product(
    val _id: String,
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: String,
    val hasDrink: Boolean,
)