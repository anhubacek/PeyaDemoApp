package com.example.peyademoapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao // Data Access Object for Product
interface ProductDao {

    @Insert
    suspend fun insertProduct(product: com.example.peyademoapp.data.local.entity.ProductEntity)

    @Query("SELECT * FROM products")
    fun getAllProducts(): Flow<List<com.example.peyademoapp.data.local.entity.ProductEntity>>

    @Delete
    suspend fun deleteProduct(product: com.example.peyademoapp.data.local.entity.ProductEntity)

    @Update
    suspend fun updateProduct(product: com.example.peyademoapp.data.local.entity.ProductEntity)
}