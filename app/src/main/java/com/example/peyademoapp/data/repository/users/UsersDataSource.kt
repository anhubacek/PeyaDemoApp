package com.example.peyademoapp.data.repository.users


import com.example.peyademoapp.model.User


interface UsersDataSource {
    fun getProfileData(): User

    suspend fun createUser(user: User): User
}