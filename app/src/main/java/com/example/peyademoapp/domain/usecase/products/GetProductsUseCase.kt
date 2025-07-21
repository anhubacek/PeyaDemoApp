package com.example.peyademoapp.domain.usecase.products

import com.example.peyademoapp.model.dataclass.Product

interface GetProductsUseCase {
    suspend operator fun invoke(): List<Product>
}
