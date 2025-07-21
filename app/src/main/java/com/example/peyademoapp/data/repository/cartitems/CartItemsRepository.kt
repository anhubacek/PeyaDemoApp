package com.example.peyademoapp.data.repository.cartitems

import com.example.peyademoapp.data.database.dao.CartItemDao
import com.example.peyademoapp.data.database.entity.CartItemEntity
import com.example.peyademoapp.model.CartItem
import javax.inject.Inject

class CartItemsRepository
@Inject constructor(
    private val cartItemDao: CartItemDao
) {

    fun getAllCartItems() = cartItemDao.getAllCartItems()

    suspend fun getAllCartItemsWithProducts() = cartItemDao.getAllCartItemsWithProducts()

    suspend fun insertCartItem(cartItem: CartItem) = cartItemDao.insertCartItem(
        CartItemEntity(
            productId = cartItem.product._id,
            quantity = cartItem.quantity,
        )
    )

    suspend fun updateCartItem(cartItem: CartItemEntity) = cartItemDao.updateCartItem(cartItem)

    suspend fun deleteCartItem(cartItemId: String) = cartItemDao.deleteCartItem(cartItemId)

    suspend fun clearCart() = cartItemDao.clearCart()

    suspend fun getCartItemByProductId(cartItemId: String) =
        cartItemDao.getCartItemByproductId(cartItemId)

}