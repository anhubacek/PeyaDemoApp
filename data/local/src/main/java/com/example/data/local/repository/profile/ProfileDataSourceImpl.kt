package com.example.data.local.repository.profile

import com.example.core.model.User
import javax.inject.Inject

class ProfileDataSourceImplementation
@Inject constructor(
) : ProfileDataSource {
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