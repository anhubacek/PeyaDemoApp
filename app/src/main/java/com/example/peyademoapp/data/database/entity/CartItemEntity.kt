package com.example.peyademoapp.data.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.peyademoapp.model.dataclass.CartItem


@Entity(
    tableName = "cart_items", foreignKeys = [ForeignKey
        (
        entity = ProductEntity::class,
        parentColumns = ["_id"],
        childColumns = ["productId"],
        onDelete = ForeignKey.NO_ACTION
    )]
)
data class CartItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val productId: String,
    val quantity: Int = 1,
) {
    fun toDomain(): CartItem {
        return CartItem(
            product = ProductEntity(
                _id = productId,
                name = "",
                price = 0.0,
                imageUrl = "",
                hasDrink = false
            ).toDomain(),
            quantity = quantity
        )
    }

    fun toEntity(): CartItemEntity {
        return CartItemEntity(
            id = id,
            productId = productId,
            quantity = quantity
        )
    }
}