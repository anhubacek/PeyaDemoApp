package com.example.data.local.datasource.profile

import com.example.core.model.User
import com.example.data.local.repository.profile.ProfileDataSourceImplementation
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class ProfileDataSourceImplementationTest {

    @Test
    fun `Cuando llamo a getProfileData me devuelve un usuario`() {
        val profileDataSource = ProfileDataSourceImplementation()
        val result = profileDataSource.getProfileData()
        assertNotNull(result)
    }

    @Test
    fun `Cuando llamo a getProfileData me devuelve el usuario con todos los datos`() {
        val profileDataSource = ProfileDataSourceImplementation()


        val mockedUser = User(
            name = "Andrea",
            lastName = "Hubacek",
            email = "test@test.com",
            nationality = "Argentina",
            password = "12345678"
        )

        val result = profileDataSource.getProfileData()
        assertEquals(mockedUser.name, result.name)
        assertEquals(mockedUser.lastName, result.lastName)
        assertEquals(mockedUser.email, result.email)
        assertEquals(mockedUser.nationality, result.nationality)
        assertEquals(mockedUser.password, result.password)

    }
}