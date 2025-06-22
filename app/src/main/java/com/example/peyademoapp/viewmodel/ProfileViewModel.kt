package com.example.peyademoapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.peyademoapp.SessionManager

class ProfileViewModel : ViewModel() {

    fun logout(): Boolean {
        try {
            SessionManager.loggedUser = null
            return true
        } catch (e: Exception) {
            return false
        }

    }
}