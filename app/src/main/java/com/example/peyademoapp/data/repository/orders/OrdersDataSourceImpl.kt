package com.example.peyademoapp.data.repository.orders

import com.example.peyademoapp.data.remote.api.ApiService
import com.example.peyademoapp.model.dataclass.Order
import com.example.peyademoapp.model.dataclass.OrderRequest
import javax.inject.Inject

class OrdersDataSourceImpl
@Inject constructor(
    private val apiService: ApiService,
) : OrdersDataSource {
    override suspend fun createOrder(orderRequest: OrderRequest): List<Order> =
        apiService.createOrder(orderRequest)

    override suspend fun getOrdersByUserEmail(email: String): List<Order> =
        apiService.getOrdersByUserEmail(email)
}