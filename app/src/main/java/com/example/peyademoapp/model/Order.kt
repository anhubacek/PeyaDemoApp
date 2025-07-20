package com.example.peyademoapp.model

data class Order(
    val items: List<CartItem>,
    val total: Double,
)