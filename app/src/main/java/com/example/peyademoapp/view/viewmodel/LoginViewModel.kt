package com.example.peyademoapp.view.viewmodel

import androidx.lifecycle.ViewModel
import com.example.peyademoapp.data.repository.users.UsersDataSource
import com.example.peyademoapp.model.LoginRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val usersDataSource: UsersDataSource
) : ViewModel(
) {
    private val _error = MutableStateFlow("")
    private val _loading = MutableStateFlow(false)
    val loginError: StateFlow<String> = _error
    val loading: StateFlow<Boolean> = _loading

    suspend fun login(email: String, password: String): Boolean {
        _loading.value = true
        try {
            if (email.isBlank() || password.isBlank()) {
                _error.value = "Completa todos los campos para iniciar sesión"
                return false
            }
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches()) {
                _error.value = "Correo electrónico inválido"
                return false
            }
            val response = usersDataSource.loginUser(
                LoginRequest(
                    email = email.trim(),
                    password = password.trim()
                )
            )
            return response.user.email.isNotBlank()
        } catch (e: Exception) {
            _error.value = e.message ?: "Error de conexión"
            _loading.value = false
            return false
        } finally {
            _loading.value = false
            _error.value = ""
        }

    }
}