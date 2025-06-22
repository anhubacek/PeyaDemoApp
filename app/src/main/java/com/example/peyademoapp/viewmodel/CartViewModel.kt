package com.example.peyademoapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.peyademoapp.model.CartItem
import com.example.peyademoapp.model.Product
import kotlinx.coroutines.flow.MutableStateFlow

class CartViewModel : ViewModel() {
    //private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    private val _cartItems = MutableStateFlow<List<CartItem>>(
        listOf(
            CartItem(
                product = Product(
                    id = "3",
                    name = "Hamburguesa clásica",
                    price = 70.0,
                    imageUrl = "https://imag.bonviveur.com/hamburguesa-clasica.jpg",
                    description = "Pan artesanal, carne de res, lechuga, tomate y mayonesa.",
                    hasDrink = false,
                ),
                quantity = 2
            ),
            CartItem(
                product = Product(
                    id = "5",
                    name = "Hot dog especial",
                    price = 50.0,
                    imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQaKqDVCWtGQzA2-d1WvRmTfPi0krczx2pwzQ&s",
                    description = "Salchicha jumbo, tocino, cebolla caramelizada y mostaza.",
                    hasDrink = true,
                ),
                quantity = 1
            )
        )
    )
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

    fun decreaseQuantity(cartItem: CartItem) {
        val existingItem = _cartItems.value.find { it.product.id == cartItem.product.id }
        if (existingItem != null) {
            if (existingItem.quantity > 1) {
                existingItem.quantity -= 1
                _cartItems.value = _cartItems.value.map {
                    if (it.product.id == cartItem.product.id) existingItem else it
                }
            } else {
                _cartItems.value = _cartItems.value.filter { it.product.id != cartItem.product.id }
            }
        }
        println("cartitems: ${_cartItems.value}")
    }

    fun removeFromCart(cartItem: CartItem) {
        _cartItems.value = _cartItems.value.filter { it.product.id != cartItem.product.id }
    }

    fun clearCart() {
        _cartItems.value = emptyList()
    }

}