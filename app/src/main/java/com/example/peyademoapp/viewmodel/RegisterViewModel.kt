package com.example.peyademoapp.viewmodel

class RegisterViewModel {
    private var _error: String = ""
    val signUpError: String = _error

    fun signUp(): Boolean {
        return true
    }
}