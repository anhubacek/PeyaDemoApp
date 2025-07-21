package com.example.peyademoapp.data.repository.products

import com.example.peyademoapp.data.database.dao.ProductDao
import com.example.peyademoapp.data.database.entity.ProductEntity
import com.example.peyademoapp.data.remote.api.ApiService
import com.example.peyademoapp.model.Product
import javax.inject.Inject

class ProductsDataSourceImpl
@Inject constructor(
    private val apiService: ApiService,
    private val productDao: ProductDao
) : ProductsDataSource {

    private fun toEntity(product: Product): ProductEntity {
        return ProductEntity(
            _id = product._id,
            name = product.name,
            description = product.description,
            price = product.price,
            imageUrl = product.imageUrl,
            hasDrink = product.hasDrink
        )
    }

    override suspend fun insertProductsInDB(products: List<Product>) {
        val productEntities = products.map { toEntity(it) }
        productDao.insertProducts(productEntities)
    }

    override suspend fun getProducts(): List<Product> {
        val productsFromApi: List<Product> = apiService.getProducts()
        if (productsFromApi.isNotEmpty()) {
            insertProductsInDB(productsFromApi)
        }
        return productsFromApi
    }


}