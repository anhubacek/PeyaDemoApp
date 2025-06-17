package com.example.peyademoapp.data

import com.example.peyademoapp.model.Product
import retrofit2.http.GET

interface ApiService {
    @GET("products")
    suspend fun getProducts(): List<Product>


}