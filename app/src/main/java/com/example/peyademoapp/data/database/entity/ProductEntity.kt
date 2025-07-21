package com.example.peyademoapp.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.peyademoapp.model.Product

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey val _id: String,
    val name: String,
    val description: String = "Product description not available",
    val price: Double,
    val imageUrl: String,
    val hasDrink: Boolean
) {
    fun toDomain(): Product {
        return Product(
            _id = _id,
            name = name,
            description = description,
            price = price,
            imageUrl = imageUrl,
            hasDrink = hasDrink
        )
    }


}