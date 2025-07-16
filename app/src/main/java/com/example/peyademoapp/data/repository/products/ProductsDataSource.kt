package com.example.peyademoapp.data.repository.products

import com.example.peyademoapp.model.Product

interface ProductsDataSource {
    suspend fun getProducts(): List<Product>
}