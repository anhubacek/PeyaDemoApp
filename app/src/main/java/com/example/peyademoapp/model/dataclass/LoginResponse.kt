package com.example.peyademoapp.model.dataclass

data class LoginResponse(
    val message: String,
    val user: User,
    val error: String? = null
)