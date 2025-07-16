package com.example.peyademoapp.view.viewmodel

import android.app.Application
import android.net.Uri
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.cloudinary.Cloudinary
import com.example.peyademoapp.BuildConfig
import com.example.peyademoapp.data.repository.users.UsersDataSource

import com.example.peyademoapp.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val usersDataSource: UsersDataSource,
    val myApplication: Application, application: Application
) : AndroidViewModel(application) {

    private val _error = MutableStateFlow("")
    private val _loading = MutableStateFlow(false)
    private val _userProfile = MutableStateFlow<User?>(null)
    private val _isImageUploading = MutableStateFlow(false)
    val error: StateFlow<String> = _error
    val loading: StateFlow<Boolean> = _loading
    val userProfile = _userProfile
    val isImageUploading: StateFlow<Boolean> = _isImageUploading


    private val cloudinary = Cloudinary(
        mapOf(
            "cloud_name" to BuildConfig.CLOUDINARY_CLOUD_NAME,
            "api_key" to BuildConfig.CLOUDINARY_API_KEY,
            "api_secret" to BuildConfig.CLOUDINARY_API_SECRET
        )
    )

    init {
        loadUserProfile()
    }

    private fun loadUserProfile() {
        _loading.value = true
        try {
            val user = usersDataSource.getProfileData()
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


    private fun uploadProfileImage(uri: Uri) {
        viewModelScope.launch(Dispatchers.IO) {
            _isImageUploading.value = true
            try {
                val inputStream = getApplication<Application>().contentResolver.openInputStream(uri)
                val result = cloudinary.uploader()
                    .upload(
                        inputStream,
                        mapOf("upload_preset" to BuildConfig.CLOUDINARY_PRESET_NAME)
                    )
                val imageUrl = result["secure_url"] as String

                val updatedUser = _userProfile.value?.copy(image = imageUrl)
                _userProfile.value = updatedUser

                _error.value = ""
            } catch (e: Exception) {
                _error.value = "Error uploading image: ${e.message}"
                Log.e("Cloudinary", "Error uploading image", e)
            } finally {
                _isImageUploading.value = false
            }
        }
    }

    fun saveProfileImage(imageUri: Uri) {
        if (imageUri != Uri.EMPTY) {
            uploadProfileImage(imageUri)
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