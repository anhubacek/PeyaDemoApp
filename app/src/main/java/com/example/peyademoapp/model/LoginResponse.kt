package com.example.peyademoapp.model

data class LoginResponse(
    val message: String,
    val user: User,
    val error: String? = null
)