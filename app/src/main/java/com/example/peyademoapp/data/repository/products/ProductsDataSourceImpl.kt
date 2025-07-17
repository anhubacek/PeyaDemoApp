package com.example.peyademoapp.data.repository.products

import com.example.peyademoapp.data.remote.api.ApiService
import com.example.peyademoapp.model.Product
import javax.inject.Inject

class ProductsDataSourceImpl
@Inject constructor(
    private val apiService: ApiService
) : ProductsDataSource {
    override suspend fun getProducts(): List<Product> = apiService.getProducts()

}