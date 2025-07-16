package com.example.peyademoapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey()
    val _id: String,
    val name: String,
    val description: String = "Product description not available",
    val price: Double,
    val imageUrl: String,
    val hasDrike: Boolean
)