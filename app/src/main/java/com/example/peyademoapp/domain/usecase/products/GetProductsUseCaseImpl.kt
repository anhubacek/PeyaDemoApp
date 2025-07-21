package com.example.peyademoapp.domain.usecase.products

import com.example.peyademoapp.data.database.dao.ProductDao
import com.example.peyademoapp.data.database.entity.ProductEntity
import com.example.peyademoapp.data.repository.products.ProductsDataSource
import com.example.peyademoapp.model.dataclass.Product
import javax.inject.Inject


class GetProductsUseCaseImpl
@Inject constructor(
    private val repository: ProductsDataSource,
    private val productDao: ProductDao
) : GetProductsUseCase {
    override suspend operator fun invoke(): List<Product> {
        val products = repository.getProducts()
        val productEntities = products.map {
            ProductEntity(
                _id = it._id,
                name = it.name,
                description = it.description,
                price = it.price,
                imageUrl = it.imageUrl,
                hasDrink = it.hasDrink
            )
        }
        productDao.insertProducts(productEntities)
        return products
    }
}
