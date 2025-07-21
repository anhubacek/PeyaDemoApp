package com.example.peyademoapp.data.repository.users

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.peyademoapp.data.database.dataStore
import com.example.peyademoapp.data.remote.api.ApiService
import com.example.peyademoapp.model.dataclass.LoginRequest
import com.example.peyademoapp.model.dataclass.LoginResponse
import com.example.peyademoapp.model.dataclass.User
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import javax.inject.Inject


class UsersDataSourceImpl
@Inject constructor(
    @ApplicationContext private val context: Context,
    private val apiService: ApiService
) : UsersDataSource {

    private val EMAIL_KEY = stringPreferencesKey("email")

    override suspend fun createUser(user: User): User {
        return apiService.createUser(user)

    }

    override suspend fun getUserByEmail(email: String): User {
        return apiService.getUserByEmail(email)
    }

    override suspend fun loginUser(loginRequest: LoginRequest): LoginResponse {
        return apiService.loginUser(
            loginRequest
        )
    }

    override suspend fun storeUserEmail(email: String) {
        context.dataStore.edit { prefs ->
            prefs[EMAIL_KEY] = email
        }
    }

    override suspend fun getStoredEmail(): String {
        val prefs = context.dataStore.data.first()
        return prefs[EMAIL_KEY] ?: ""
    }

    override suspend fun removeStoredEmail() {
        context.dataStore.edit { prefs ->
            prefs.remove(EMAIL_KEY)
        }
    }

    override suspend fun updateUser(email: String, user: User): User {
        return apiService.updateUser(email, user)
    }


}