package com.example.peyademoapp

import com.example.data.local.repository.products.ProductsDataSource
import com.example.data.local.repository.products.ProductsDataSourceImplementation
import com.example.data.local.repository.profile.ProfileDataSource
import com.example.data.local.repository.profile.ProfileDataSourceImplementation
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