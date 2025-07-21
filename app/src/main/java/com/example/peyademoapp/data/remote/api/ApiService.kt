package com.example.peyademoapp.data.remote.api

import com.example.peyademoapp.model.dataclass.LoginRequest
import com.example.peyademoapp.model.dataclass.LoginResponse
import com.example.peyademoapp.model.dataclass.Order
import com.example.peyademoapp.model.dataclass.OrderRequest
import com.example.peyademoapp.model.dataclass.Product
import com.example.peyademoapp.model.dataclass.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface ApiService {
    @GET("foods")
    suspend fun getProducts(): List<Product>

    @POST("users/register")
    suspend fun createUser(@Body user: User): User

    @GET("users/{email}")
    suspend fun getUserByEmail(@retrofit2.http.Path("email") email: String): User

    @POST("users/login")
    suspend fun loginUser(
        @Body loginRequest: LoginRequest
    ): LoginResponse

    @PUT("users/update/{email}")
    suspend fun updateUser(
        @retrofit2.http.Path("email") email: String,
        @Body user: User
    ): User

    @POST("orders")
    suspend fun createOrder(
        @Body orderRequest: OrderRequest
    ): List<Order>

    @GET("orders/user/{email}")
    suspend fun getOrdersByUserEmail(
        @retrofit2.http.Path("email") email: String
    ): List<Order>
}