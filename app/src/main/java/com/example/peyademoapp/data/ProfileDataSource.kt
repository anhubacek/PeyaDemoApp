package com.example.peyademoapp.data

import com.example.core.model.User

interface ProfileDataSource {
    fun getProfileData(): User
}