package com.example.data.local.repository.profile


import com.example.core.model.User

interface ProfileDataSource {
    fun getProfileData(): User
}