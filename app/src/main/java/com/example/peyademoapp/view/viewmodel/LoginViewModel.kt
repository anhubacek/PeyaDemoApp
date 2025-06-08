package com.example.peyademoapp.view.viewmodel

class LoginViewModel {

    fun login(username: String, password: String): Boolean {
        // Lógica de autenticación simulada
        return username == "user" && password == "password"
    }
}