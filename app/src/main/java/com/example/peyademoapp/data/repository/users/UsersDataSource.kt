package com.example.peyademoapp.data.repository.users


import com.example.peyademoapp.model.User


interface UsersDataSource {
    fun getProfileData(): User
}