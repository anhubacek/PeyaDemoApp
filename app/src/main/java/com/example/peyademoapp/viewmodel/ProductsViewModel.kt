package com.example.peyademoapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.peyademoapp.data.repository.ProductRepository
import com.example.peyademoapp.model.Product
import kotlinx.coroutines.flow.MutableStateFlow

class ProductsViewModel : ViewModel() {
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    private val _filteredProducts: MutableStateFlow<List<Product>> = _products
    val filteredProducts: MutableStateFlow<List<Product>> = _filteredProducts

    init {
        val productList: List<Product> = ProductRepository.getProducts()
        _products.value = productList
    }

    fun filterProducts(query: String) {
        _filteredProducts.value = _products.value.filter { product ->
            product.name.contains(query, ignoreCase = true)
        }
    }
}