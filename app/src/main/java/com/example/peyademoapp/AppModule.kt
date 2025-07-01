package com.example.peyademoapp

import com.example.peyademoapp.data.ProductsDataSource
import com.example.peyademoapp.data.ProfileDataSource
import com.example.peyademoapp.data.implementations.ProductsDataSourceImplementation
import com.example.peyademoapp.data.implementations.ProfileDataSourceImplementation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideProfileDataSource(): ProfileDataSource {
        return ProfileDataSourceImplementation()
    }

    @Provides
    fun provideProductsDataSource(): ProductsDataSource {
        return ProductsDataSourceImplementation()
    }

}