package com.example.peyademoapp.data.repository.orders

import com.example.peyademoapp.model.Order
import com.example.peyademoapp.model.OrderRequest

interface OrdersDataSource {
    suspend fun createOrder(orderRequest: OrderRequest): List<Order>

    suspend fun getOrdersByUserEmail(email: String): List<Order>
}