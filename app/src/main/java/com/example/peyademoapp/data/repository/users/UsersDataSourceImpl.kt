package com.example.peyademoapp.data.repository.users

import com.example.peyademoapp.data.remote.api.ApiService
import com.example.peyademoapp.model.User
import javax.inject.Inject


class UsersDataSourceImpl
@Inject constructor(
    private val apiService: ApiService
) : UsersDataSource {
    private val _userProfile = User(
        name = "Andrea",
        lastName = "Hubacek",
        email = "test@test.com",
        nationality = "Argentina",
        password = "12345678"
    )

    override fun getProfileData(): User {
        return _userProfile
    }

    override suspend fun createUser(user: User): User {
        val newUser = apiService.createUser(user)
        return newUser
    }

}