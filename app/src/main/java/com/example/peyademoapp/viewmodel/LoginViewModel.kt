package com.example.peyademoapp.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class LoginViewModel : ViewModel(
) {
    private val _error = MutableStateFlow("")
    val loginError: StateFlow<String> = _error

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    suspend fun login(email: String, password: String): Boolean {
        _loading.value = true
        try {
            if (email.trim() == "test@test.com" && password.trim() == "12345678") {
                delay(2000)
                return true
            } else {
                delay(1000)
                _error.value = "Correo electrónico o contraseña incorrectos"
                return false
            }
        } catch (e: Exception) {
            _error.value = "Error de conexión"
            _loading.value = false
            return false
        } finally {
            _loading.value = false
        }

    }
}