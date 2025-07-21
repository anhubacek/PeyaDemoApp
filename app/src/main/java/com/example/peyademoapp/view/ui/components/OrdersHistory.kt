package com.example.peyademoapp.view.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.peyademoapp.model.dataclass.Order

@Composable
fun OrdersHistory(
    orders: List<Order>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(orders.size) { index ->
            OrderItem(
                order = orders[index],
                number = orders.size - index,
            )
        }
    }
}