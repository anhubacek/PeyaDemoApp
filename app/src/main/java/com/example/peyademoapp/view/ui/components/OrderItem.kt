package com.example.peyademoapp.view.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.peyademoapp.model.dataclass.Order

@Composable
fun OrderItem(
    order: Order,
    number: Int,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 8.dp, end = 8.dp, bottom = 16.dp)
            .shadow(
                elevation = 4.dp,
                shape = MaterialTheme.shapes.medium
            )
    ) {

        Text(
            text = "Pedido #${number}",
            modifier = Modifier.padding(8.dp),
            fontSize = 18.sp
        )

        order.items.forEach { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "${item.product.name} x${item.quantity}",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = "%.2f €".format(item.product.price * item.quantity),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Total",
                style = MaterialTheme.typography.titleMedium
            )
            val total = order.items.sumOf { it.product.price * it.quantity }
            Text(
                text = "%.2f €".format(total),
                style = MaterialTheme.typography.titleMedium
            )
        }


    }


}