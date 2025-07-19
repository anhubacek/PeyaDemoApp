package com.example.peyademoapp.data.repository.users


import com.example.peyademoapp.model.LoginRequest
import com.example.peyademoapp.model.LoginResponse
import com.example.peyademoapp.model.User


interface UsersDataSource {
    fun getProfileData(): User

    suspend fun createUser(user: User): User

    suspend fun getUserByEmail(email: String): User

    suspend fun loginUser(loginRequest: LoginRequest): LoginResponse
}