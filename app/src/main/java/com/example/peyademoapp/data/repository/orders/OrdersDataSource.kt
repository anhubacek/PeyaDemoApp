package com.example.peyademoapp.data.repository.orders

import com.example.peyademoapp.model.dataclass.Order
import com.example.peyademoapp.model.dataclass.OrderRequest

interface OrdersDataSource {
    suspend fun createOrder(orderRequest: OrderRequest): List<Order>

    suspend fun getOrdersByUserEmail(email: String): List<Order>
}