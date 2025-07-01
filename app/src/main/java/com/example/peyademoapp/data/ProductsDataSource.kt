package com.example.peyademoapp.data

import com.example.peyademoapp.model.Product

interface ProductsDataSource {
    suspend fun getProducts(): List<Product>
}


