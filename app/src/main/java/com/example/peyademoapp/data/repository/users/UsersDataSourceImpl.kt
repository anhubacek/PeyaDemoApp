package com.example.peyademoapp.data.repository.users

import com.example.peyademoapp.model.User
import javax.inject.Inject


class UsersDataSourceImpl
@Inject constructor(
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

}