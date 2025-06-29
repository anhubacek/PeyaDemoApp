package com.example.peyademoapp.data.repository

import com.example.peyademoapp.model.User

class UserRepository {

    companion object {
        fun getUser(): User {
            return User(
                name = "John",
                lastName = "Doe",
                email = "johndoe@test.com",
                nationality = "Argentina",
                password = "password123"
            )
        }
    }
}

