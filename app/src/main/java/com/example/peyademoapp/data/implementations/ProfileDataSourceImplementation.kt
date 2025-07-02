package com.example.peyademoapp.data.implementations

import com.example.core.model.User
import com.example.peyademoapp.data.ProfileDataSource
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