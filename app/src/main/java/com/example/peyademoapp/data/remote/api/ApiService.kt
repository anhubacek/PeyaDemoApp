package com.example.peyademoapp.data.remote.api

import com.example.peyademoapp.model.Product
import retrofit2.http.GET

interface ApiService {
    @GET("foods")
    suspend fun getProducts(): List<Product>

}