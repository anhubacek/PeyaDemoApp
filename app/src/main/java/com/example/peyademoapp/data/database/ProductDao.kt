package com.example.peyademoapp.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao // Data Access Object for Product
interface ProductDao {

    @Insert
    suspend fun insertProduct(product: ProductEntity)

    @Query("SELECT * FROM products")
    fun getAllProducts(): Flow<List<ProductEntity>>

    @Delete
    suspend fun deleteProduct(product: ProductEntity)

    @Update
    suspend fun updateProduct(product: ProductEntity)
}