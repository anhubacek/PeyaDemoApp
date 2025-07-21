package com.example.peyademoapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.peyademoapp.data.database.entity.CartItemEntity
import com.example.peyademoapp.data.database.entity.CartItemWithProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CartItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCartItem(cartItem: CartItemEntity)

    @Query("SELECT * FROM cart_items")
    fun getAllCartItems(): Flow<List<CartItemEntity>>

    @Transaction
    @Query("SELECT * FROM cart_items")
    fun getAllCartItemsWithProducts(): Flow<List<CartItemWithProductEntity>>

    @Query("DELETE FROM cart_items WHERE productId = :cartItemId")
    suspend fun deleteCartItem(cartItemId: String)

    @Update
    suspend fun updateCartItem(cartItem: CartItemEntity)

    @Query("DELETE FROM cart_items")
    suspend fun clearCart()

    @Query("SELECT * FROM cart_items WHERE productId = :productId")
    suspend fun getCartItemByproductId(productId: String): CartItemEntity?
}