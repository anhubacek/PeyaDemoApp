package com.example.peyademoapp.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.peyademoapp.data.repository.orders.OrdersDataSource
import com.example.peyademoapp.data.repository.users.UsersDataSource
import com.example.peyademoapp.model.CartItem
import com.example.peyademoapp.model.Order
import com.example.peyademoapp.model.OrderRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val ordersDataSource: OrdersDataSource,
    private val usersDataSource: UsersDataSource
) : ViewModel() {
    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    private val _orders = MutableStateFlow<List<Order>>(emptyList())
    private val _loadingOrders = MutableStateFlow(false)
    val cartItems = _cartItems
    val orders = _orders
    val isLoadingOrders = _loadingOrders
    private val _message = MutableStateFlow("")
    val message = _message

    val exceptionHndler = CoroutineExceptionHandler { _, exception ->
        _message.value = "Error: ${exception.message}"
        println("Unexpected error: ${exception.message}")
    }

    fun loadOrders() {
        viewModelScope.launch(Dispatchers.IO + exceptionHndler) {
            _loadingOrders.value = true
            try {
                val userEmail = usersDataSource.getStoredEmail()
                val ordersList: List<Order> = ordersDataSource.getOrdersByUserEmail(userEmail)
                _orders.value = ordersList

            } catch (e: Exception) {
                _message.value = "Error al cargar las órdenes"
                println("Error al cargar las órdenes ${e.message}")
            } finally {
                _loadingOrders.value = false
            }

        }
    }

    fun addToCart(cartItem: CartItem) {
        val existingItem = _cartItems.value.find { it.product._id == cartItem.product._id }
        _cartItems.value = if (existingItem != null) {
            _cartItems.value.map {
                if (it.product._id == cartItem.product._id) {
                    it.copy(quantity = it.quantity + cartItem.quantity)
                } else {
                    it
                }
            }
        } else {
            _cartItems.value + cartItem
        }
    }


    fun decreaseQuantity(cartItem: CartItem) {
        _cartItems.value = _cartItems.value.mapNotNull { item ->
            if (item.product._id == cartItem.product._id) {
                if (item.quantity > 1) {
                    item.copy(quantity = item.quantity - 1)
                } else {
                    null
                }
            } else {
                item
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
        _cartItems.value = _cartItems.value.mapNotNull { item ->
            if (item.product._id == cartItem.product._id) {
                if (newQuantity > 0) {
                    item.copy(quantity = newQuantity)
                } else {
                    null
                }
            } else {
                item
            }
        }
    }

    suspend fun confirmOrder(): Boolean {
        _loadingOrders.value = true
        try {
            val userEmail = usersDataSource.getStoredEmail()
            if (userEmail.isEmpty()) {
                throw IllegalStateException("User email not found")
            }
            val userOrders = ordersDataSource.createOrder(
                OrderRequest(
                    email = userEmail,
                    order = Order(
                        items = _cartItems.value,
                        total = _cartItems.value.sumOf { it.product.price * it.quantity }
                    )
                )

            )
            if (userOrders.isNotEmpty()) {
                _orders.value = userOrders
                return true
            } else {
                return false
            }

        } catch (e: Exception) {
            println("Error al crear la orden: ${e.message}")
            return false
        } finally {
            _loadingOrders.value = false

        }
    }

}