package com.example.core.model

data class Product(
    val name: String,
    val price: Double,
    val description: String? = null,
    val image: String? = null,
)
