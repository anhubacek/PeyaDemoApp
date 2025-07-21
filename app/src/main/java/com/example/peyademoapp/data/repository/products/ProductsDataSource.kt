package com.example.peyademoapp.data.repository.products

import com.example.peyademoapp.model.dataclass.Product

interface ProductsDataSource {
    suspend fun insertProductsInDB(products: List<Product>)
    suspend fun getProducts(): List<Product>
}