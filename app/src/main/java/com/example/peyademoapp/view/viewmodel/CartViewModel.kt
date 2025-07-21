package com.example.peyademoapp.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.peyademoapp.data.repository.cartitems.CartItemsRepository
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
    private val usersDataSource: UsersDataSource,
    private val cartItemsRepository: CartItemsRepository
) : ViewModel() {
    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    private val _orders = MutableStateFlow<List<Order>>(emptyList())
    private val _loadingOrders = MutableStateFlow(false)
    val cartItems = _cartItems
    val orders = _orders
    val isLoadingOrders = _loadingOrders
    private val _message = MutableStateFlow("")
    val message = _message

    val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        _message.value = "Error: ${exception.message}"
        println("Unexpected error: ${exception.message}")
    }

    fun loadOrders() {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
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

    fun refreshCartItems() {
        viewModelScope.launch {
            val cartItems =
                cartItemsRepository.getAllCartItemsWithProducts().collect() { cartItemsList ->
                    val cartItems = cartItemsList.map { cartItemEntity ->
                        cartItemEntity.toDomain()
                    }
                    _cartItems.value = cartItems
                }

        }
    }

    fun addToCart(cartItem: CartItem) {
        viewModelScope.launch {
            val existentItem = cartItemsRepository.getCartItemByProductId(cartItem.product._id)
            if (existentItem != null) {
                val updatedItem =
                    existentItem.copy(quantity = existentItem.quantity + cartItem.quantity)
                cartItemsRepository.updateCartItem(updatedItem)
            } else {
                cartItemsRepository.insertCartItem(cartItem)
            }
            refreshCartItems()
        }
    }


    suspend fun removeFromCart(cartItem: CartItem) {
        cartItemsRepository.deleteCartItem(cartItem.product._id)
        refreshCartItems()
    }

    suspend fun clearCart() {
        cartItemsRepository.clearCart()
        refreshCartItems()
    }


    suspend fun updateQuantity(cartItem: CartItem, newQuantity: Int) {
        val existingItem = cartItemsRepository.getCartItemByProductId(cartItem.product._id)
        if (existingItem != null) {
            if (newQuantity > 0) {
                val updatedItem = existingItem.copy(quantity = newQuantity)
                cartItemsRepository.updateCartItem(updatedItem)
            } else {
                cartItemsRepository.deleteCartItem(cartItem.product._id)
            }
        }
        refreshCartItems()
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