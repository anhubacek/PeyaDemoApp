package com.example.core.model

data class Product(
    val id: String,
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: String,
    val hasDrink: Boolean,
)