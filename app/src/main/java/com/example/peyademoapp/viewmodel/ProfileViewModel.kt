package com.example.peyademoapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.core.model.User
import com.example.peyademoapp.data.ProfileDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileDataSource: ProfileDataSource
) : ViewModel() {

    private val _error = MutableStateFlow("")
    private val _loading = MutableStateFlow(false)
    private val _userProfile = MutableStateFlow<User?>(null)
    val error: StateFlow<String> = _error
    val loading: StateFlow<Boolean> = _loading
    val userProfile = _userProfile

    init {
        loadUserProfile()
    }

    fun loadUserProfile() {
        _loading.value = true
        try {
            val user = profileDataSource.getProfileData()
            _userProfile.value = user
            _error.value = ""
        } catch (e: Exception) {
            _error.value = "Error loading profile: ${e.message}"
        } finally {
            _loading.value = false
        }
    }

    fun logout(): Boolean {
        try {
            _userProfile.value = null
            return true
        } catch (e: Exception) {
            return false
        }
    }


    fun handleSaveChanges(data: User): Boolean {
        try {
            if (data.name.isBlank() || data.lastName.isBlank() || data.email.isBlank() || data.password.isBlank() || data.nationality.isBlank()) {
                _error.value = "Completa todos los campos para continuar."
                return false
            }
            val updatedUser = User(
                name = data.name,
                lastName = data.lastName,
                email = data.email,
                password = data.password,
                nationality = data.nationality
            )
            _userProfile.value = updatedUser
            return true
        } catch (e: Exception) {
            _error.value = "Error al guardar los cambios: ${e.message}"
            return false
        }

    }


}