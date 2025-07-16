package com.example.peyademoapp.data.remote.api

import retrofit2.http.GET

interface ApiService {
    @GET("foods")
    suspend fun getProducts(): List<ProductDto>

}