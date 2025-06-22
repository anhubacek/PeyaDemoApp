package com.example.peyademoapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.peyademoapp.model.CartItem
import kotlinx.coroutines.flow.MutableStateFlow

class CartViewModel : ViewModel() {
    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems = _cartItems

    fun addItemToCart(item: CartItem) {
        val existingItem = _cartItems.value.find { it.product.id == item.product.id }
        if (existingItem != null) {
            existingItem.quantity += item.quantity
            _cartItems.value = _cartItems.value.map {
                if (it.product.id == item.product.id) existingItem else it
            }
        } else {
            _cartItems.value + item
        }


    }
}