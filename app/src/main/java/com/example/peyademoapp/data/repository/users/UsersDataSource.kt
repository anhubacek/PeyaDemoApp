package com.example.peyademoapp.data.repository.users


import com.example.peyademoapp.model.dataclass.LoginRequest
import com.example.peyademoapp.model.dataclass.LoginResponse
import com.example.peyademoapp.model.dataclass.User


interface UsersDataSource {

    suspend fun createUser(user: User): User

    suspend fun getUserByEmail(email: String): User

    suspend fun loginUser(loginRequest: LoginRequest): LoginResponse

    suspend fun storeUserEmail(email: String)

    suspend fun getStoredEmail(): String

    suspend fun removeStoredEmail()

    suspend fun updateUser(email: String, user: User): User
}