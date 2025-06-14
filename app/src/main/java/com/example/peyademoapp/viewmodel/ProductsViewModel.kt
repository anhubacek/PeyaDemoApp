package com.example.peyademoapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.peyademoapp.data.repository.ProductRepository
import com.example.peyademoapp.model.Product
import kotlinx.coroutines.flow.MutableStateFlow

class ProductsViewModel : ViewModel() {
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    private val _filteredProducts: MutableStateFlow<List<Product>> = MutableStateFlow(emptyList())
    val filteredProducts: MutableStateFlow<List<Product>> = _filteredProducts
    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery
    private val _message = MutableStateFlow("")
    val message = _message

    init {
        val productList: List<Product> = ProductRepository.getProducts()
        _products.value = productList
        _filteredProducts.value = productList
    }

    fun filterProducts(query: String) {
        println("Filtering products with query: ${query.length}")
        if (query.isNotEmpty()) {
            val filtered = _products.value.filter { product ->
                product.name.contains(query, ignoreCase = true)
            }
            if (filtered.isEmpty()) {
                _message.value = "No se encontraron productos"
            } else {
                _message.value = ""
            }
            _filteredProducts.value = filtered

        } else {
            _filteredProducts.value = _products.value
            _message.value = ""
            return
        }

    }
}