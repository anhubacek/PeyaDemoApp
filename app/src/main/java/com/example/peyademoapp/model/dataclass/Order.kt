package com.example.peyademoapp.model.dataclass

data class Order(
    val items: List<CartItem>,
    val total: Double,
)