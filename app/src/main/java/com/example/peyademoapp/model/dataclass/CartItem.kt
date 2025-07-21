package com.example.peyademoapp.model.dataclass

data class CartItem(
    val product: Product,
    var quantity: Int = 1
)
