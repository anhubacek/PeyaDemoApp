package com.example.peyademoapp.data.database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation
import com.example.peyademoapp.model.CartItem

@Entity
data class CartItemWithProductEntity(
    @Embedded val cartItem: CartItemEntity,
    @Relation(
        parentColumn = "productId",
        entityColumn = "_id"
    )
    val product: ProductEntity
) {
    fun toDomain(): CartItem {
        return CartItem(
            product = product.toDomain(),
            quantity = cartItem.quantity
        )
    }
}