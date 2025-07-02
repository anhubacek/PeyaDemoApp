package com.example.peyademoapp.data

import com.example.core.model.Product

interface ProductsDataSource {
    suspend fun getProducts(): List<Product>
}


