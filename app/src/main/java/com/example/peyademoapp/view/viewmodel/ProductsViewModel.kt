package com.example.peyademoapp.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.peyademoapp.data.repository.products.ProductsDataSource
import com.example.peyademoapp.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val productsDataSource: ProductsDataSource
) : ViewModel() {
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    private val _filteredProducts: MutableStateFlow<List<Product>> =
        MutableStateFlow(emptyList())
    val filteredProducts: MutableStateFlow<List<Product>> = _filteredProducts
    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery
    private val _message = MutableStateFlow("")
    val message = _message


    val exceptionHndler = CoroutineExceptionHandler { _, exception ->
        _message.value = "Error: ${exception.message}"
        println("Unexpected error: ${exception.message}")
    }


    fun init() {
        viewModelScope.launch(Dispatchers.IO + exceptionHndler) {
            try {
                val productList: List<Product> = productsDataSource.getProducts()
                _products.value = productList
                _filteredProducts.value = productList

            } catch (e: Exception) {
                _message.value = "Error al cargar los productos"
                println("Error in getProducts ${e.message}")
            }

        }

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