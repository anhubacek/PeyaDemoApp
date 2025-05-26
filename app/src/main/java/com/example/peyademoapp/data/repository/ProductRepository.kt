package com.example.peyademoapp.data.repository

data class Product(val id: Int, val name: String)

class ProductRepository {
    fun getProducts(): List<Product> {
        return listOf(
            Product(1, "Producto A"),
            Product(2, "Producto B"),
            Product(3, "Producto C")
        )
    }
}
