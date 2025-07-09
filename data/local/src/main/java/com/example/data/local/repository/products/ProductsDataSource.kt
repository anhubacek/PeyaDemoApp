package com.example.data.local.repository.products

import com.example.core.model.Product

interface ProductsDataSource {
    suspend fun getProducts(): List<Product>
}