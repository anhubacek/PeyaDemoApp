package com.example.peyademoapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.peyademoapp.data.database.dao.CartItemDao
import com.example.peyademoapp.data.database.dao.OrderHistoryDao
import com.example.peyademoapp.data.database.dao.ProductDao
import com.example.peyademoapp.data.database.entity.CartItemEntity
import com.example.peyademoapp.data.database.entity.OrderEntity
import com.example.peyademoapp.data.database.entity.ProductEntity

@Database(
    entities = [
        ProductEntity::class,
        CartItemEntity::class,
        OrderEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun cartItemDao(): CartItemDao
    abstract fun orderHistoryDao(): OrderHistoryDao
}