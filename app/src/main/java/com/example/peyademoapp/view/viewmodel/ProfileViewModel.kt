package com.example.peyademoapp.view.viewmodel

import android.app.Application
import android.net.Uri
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.cloudinary.Cloudinary
import com.example.peyademoapp.BuildConfig
import com.example.peyademoapp.data.repository.users.UsersDataSource

import com.example.peyademoapp.model.dataclass.User
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

    suspend fun loadUserProfile() {
        _loading.value = true
        try {
            val userEmail = usersDataSource.getStoredEmail()
            val user = usersDataSource.getUserByEmail(
                userEmail
            )
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
            viewModelScope.launch {
                usersDataSource.removeStoredEmail()
            }
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

                val updatedUser = usersDataSource.updateUser(
                    _userProfile.value?.email ?: "",
                    User(
                        name = _userProfile.value?.name ?: "",
                        lastName = _userProfile.value?.lastName ?: "",
                        email = _userProfile.value?.email ?: "",
                        password = _userProfile.value?.password ?: "",
                        image = imageUrl,
                    )

                )
                if (updatedUser.image == imageUrl) {
                    _userProfile.value = updatedUser
                }
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


    suspend fun handleSaveChanges(data: User): Boolean {
        try {
            if (data.name.isBlank() || data.lastName.isBlank() || data.email.isBlank() || data.password.isBlank()) {
                _error.value = "Completa todos los campos para continuar."
                return false
            }
            val updatedUser = usersDataSource.updateUser(
                _userProfile.value?.email ?: "",
                User(
                    name = data.name.trim(),
                    lastName = data.lastName.trim(),
                    email = data.email.trim(),
                    password = data.password.trim(),
                    nationality = data.nationality?.trim(),
                    image = _userProfile.value?.image ?: "",
                )
            )
            _userProfile.value = updatedUser
            return true
        } catch (e: Exception) {
            _error.value = "Error al guardar los cambios: ${e.message}"
            return false
        }

    }


}