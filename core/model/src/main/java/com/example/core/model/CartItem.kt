package com.example.core.model

data class CartItem(
    val product: Product,
    var quantity: Int = 1
)
