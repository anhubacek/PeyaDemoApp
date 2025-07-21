package com.example.peyademoapp.model.dataclass

data class User(
    val name: String,
    val lastName: String,
    val email: String,
    val nationality: String? = null,
    val password: String,
    val image: String? = null
)
