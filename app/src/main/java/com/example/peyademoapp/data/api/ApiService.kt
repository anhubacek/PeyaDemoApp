package com.example.peyademoapp.data.api

import com.example.peyademoapp.data.database.ProductEntity
import retrofit2.http.GET

interface ApiService {
    @GET("foods")
    suspend fun getProducts(): List<ProductEntity>

}