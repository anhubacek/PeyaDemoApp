package com.example.peyademoapp.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    //dependencies
) : ViewModel(
) {
    private val _error = MutableStateFlow("")
    private val _loading = MutableStateFlow(false)
    val loginError: StateFlow<String> = _error
    val loading: StateFlow<Boolean> = _loading

    suspend fun login(email: String, password: String): Boolean {
        _loading.value = true
        try {
            if (email.trim() == "test@test.com" && password.trim() == "12345678") {
                delay(2000)
                return true
            } else {
                delay(500)
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