package com.example.peyademoapp.view.viewmodel

import androidx.lifecycle.ViewModel
import com.example.peyademoapp.model.CartItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    //
) : ViewModel() {
    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems = _cartItems

    fun addToCart(cartItem: CartItem) {
        val existingItem = _cartItems.value.find { it.product._id == cartItem.product._id }
        if (existingItem != null) {
            existingItem.quantity += cartItem.quantity
            _cartItems.value = _cartItems.value.map {
                if (it.product._id == cartItem.product._id) existingItem else it
            }
        } else {
            _cartItems.value = _cartItems.value + cartItem
        }
        println("cartitems: ${_cartItems.value}")
    }

    fun decreaseQuantity(cartItem: CartItem) {
        val existingItem = _cartItems.value.find { it.product._id == cartItem.product._id }
        if (existingItem != null) {
            if (existingItem.quantity > 1) {
                existingItem.quantity -= 1
                _cartItems.value = _cartItems.value.map {
                    if (it.product._id == cartItem.product._id) existingItem else it
                }
            } else {
                _cartItems.value =
                    _cartItems.value.filter { it.product._id != cartItem.product._id }
            }
        }
    }

    fun removeFromCart(cartItem: CartItem) {
        _cartItems.value = _cartItems.value.filter { it.product._id != cartItem.product._id }
    }

    fun clearCart() {
        _cartItems.value = emptyList()
    }

    fun updateQuantity(cartItem: CartItem, newQuantity: Int) {
        val existingItem = _cartItems.value.find { it.product._id == cartItem.product._id }
        if (existingItem != null) {
            if (newQuantity > 0) {
                existingItem.quantity = newQuantity
                _cartItems.value = _cartItems.value.map {
                    if (it.product._id == cartItem.product._id) existingItem else it
                }
            }
        }
    }


}