package com.example.peyademoapp.view.viewmodel

import androidx.lifecycle.ViewModel
import com.example.peyademoapp.data.repository.users.UsersDataSource
import com.example.peyademoapp.model.dataclass.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val usersDataSource: UsersDataSource
) : ViewModel() {
    private val _error = MutableStateFlow("")
    private val _loading = MutableStateFlow(false)
    val signUpError: StateFlow<String> = _error
    val loading: StateFlow<Boolean> = _loading

    suspend fun signUp(
        email: String,
        name: String,
        lastName: String,
        password: String,
        confirmPassword: String
    ): Boolean {
        _loading.value = true
        try {
            if (email.isBlank() || name.isBlank() || lastName.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
                _error.value = "Completa todos los campos para continuar"
                return false
            }
            if (password != confirmPassword) {
                _error.value = "Las contraseñas no coinciden"
                return false
            }
            if (password.length < 8) {
                _error.value = "La contraseña debe tener al menos 8 caracteres"
                return false
            }
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches()) {
                _error.value = "Correo electrónico inválido"
                return false
            }
            usersDataSource.createUser(
                User(
                    name = name.trim(),
                    lastName = lastName.trim(),
                    email = email.trim(),
                    password = password.trim(),
                )
            )

            val user = usersDataSource.getUserByEmail(email.trim())
            return true

        } catch (e: Exception) {
            _error.value = "Error al crear el usuario: ${e.message}"
            _loading.value = false
            return false
        } finally {
            _loading.value = false
        }

    }
}