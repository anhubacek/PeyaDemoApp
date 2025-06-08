package com.example.peyademoapp.view.viewmodel

import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay

class LoginViewModel {
    private var _error: String = ""
    val loginError: String = _error

  suspend  fun login(email: String, password: String): Boolean {
       if (email == "test@test.com" && password == "12345678"){
            // Simulate a network call
            delay(2000)
           _error = ""
            return true
        } else {
            // Simulate a failed login attempt
            delay(1000)
           _error = "Correo electrónico o contraseña incorrectos"
            return false
       }
    }
}