package com.example.peyademoapp.data.remote.api

import com.example.peyademoapp.model.Product
import com.example.peyademoapp.model.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("foods")
    suspend fun getProducts(): List<Product>

    @POST("users/register")
    suspend fun createUser(@Body user: User): User

    @GET("users/{id}")
    suspend fun getUserById(@retrofit2.http.Path("id") id: Int): User
}