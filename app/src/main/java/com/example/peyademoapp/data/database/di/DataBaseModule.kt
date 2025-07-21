package com.example.peyademoapp.data.database.di

import android.content.Context
import androidx.room.Room
import com.example.peyademoapp.data.database.AppDatabase
import com.example.peyademoapp.data.database.dao.CartItemDao
import com.example.peyademoapp.data.database.dao.OrderHistoryDao
import com.example.peyademoapp.data.database.dao.ProductDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "peyademoapp_database"
        ).fallbackToDestructiveMigration(true).build()
    }

    @Provides
    fun provideProducts(database: AppDatabase): ProductDao {
        return database.productDao()
    }

    @Provides
    fun provideCartItems(database: AppDatabase): CartItemDao {
        return database.cartItemDao()
    }

    @Provides
    fun provideOrderHistory(database: AppDatabase): OrderHistoryDao {
        return database.orderHistoryDao()
    }


}