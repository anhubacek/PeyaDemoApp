package com.example.peyademoapp.domain.di

import com.example.peyademoapp.domain.usecase.products.GetProductsUseCase
import com.example.peyademoapp.domain.usecase.products.GetProductsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ProductsModule {

    @Binds
    abstract fun bindGetProductsUseCase(
        impl: GetProductsUseCaseImpl
    ): GetProductsUseCase
}