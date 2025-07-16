package com.example.peyademoapp.data.remote.api.di

import com.example.peyademoapp.data.repository.products.ProductsDataSource
import com.example.peyademoapp.data.repository.products.ProductsDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ProductsModule {

    @Binds
    @Singleton
    abstract fun bindProductsDataSource(
        impl: ProductsDataSourceImpl
    ): ProductsDataSource
}
