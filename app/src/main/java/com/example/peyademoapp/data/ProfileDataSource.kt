package com.example.peyademoapp.data

import com.example.peyademoapp.model.User

interface ProfileDataSource {
    fun getProfileData(): User
}