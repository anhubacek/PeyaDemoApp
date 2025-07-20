package com.example.peyademoapp.data.remote.api.di

import com.example.peyademoapp.data.repository.orders.OrdersDataSource
import com.example.peyademoapp.data.repository.orders.OrdersDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class OrdersModule {

    @Binds
    @Singleton
    abstract fun bindOrdersDataSource(
        impl: OrdersDataSourceImpl
    ): OrdersDataSource

}