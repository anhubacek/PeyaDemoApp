package com.example.peyademoapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.peyademoapp.model.CartItem
import kotlinx.coroutines.flow.MutableStateFlow

class CartViewModel : ViewModel() {
    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems = _cartItems

    fun addToCart(cartItem: CartItem) {
        val existingItem = _cartItems.value.find { it.product.id == cartItem.product.id }
        if (existingItem != null) {
            existingItem.quantity += cartItem.quantity
            _cartItems.value = _cartItems.value.map {
                if (it.product.id == cartItem.product.id) existingItem else it
            }
        } else {
            _cartItems.value = _cartItems.value + cartItem
        }
        println("cartitems: ${_cartItems.value}")
    }

}