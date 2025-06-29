package com.example.peyademoapp.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.peyademoapp.SessionManager
import com.example.peyademoapp.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProfileViewModel : ViewModel() {
    val emptyUserState = User(
        name = "",
        lastName = "",
        email = "",
        nationality = "",
        password = ""
    )
    private val _error = MutableStateFlow("")
    private val _loading = MutableStateFlow(false)
    val error: StateFlow<String> = _error
    val loading: StateFlow<Boolean> = _loading
    private val _userProfile = mutableStateOf(SessionManager.loggedUser ?: emptyUserState)
    val userProfile = _userProfile

    fun logout(): Boolean {
        try {
            SessionManager.loggedUser = null
            _userProfile.value = emptyUserState
            return true
        } catch (e: Exception) {
            return false
        }
    }
}