package com.example.peyademoapp.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.peyademoapp.data.database.entity.OrderEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderHistoryDao {
    @Insert
    suspend fun insertOrder(order: OrderEntity)

    @Query("SELECT * FROM orders")
    fun getAllOrders(): Flow<List<OrderEntity>>

    @Delete
    suspend fun deleteOrder(order: OrderEntity)

    @Update
    suspend fun updateOrder(order: OrderEntity)
}