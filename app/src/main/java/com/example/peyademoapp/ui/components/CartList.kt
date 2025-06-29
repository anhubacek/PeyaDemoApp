package com.example.peyademoapp.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.peyademoapp.model.CartItem

@Composable
fun CartList(
    cartItems: List<CartItem>
) {
    if (cartItems.isEmpty()) {
        Text(
            text = "Tu carrito está vacío",
            modifier = Modifier.padding(16.dp),
            fontSize = 20.sp
        )
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)

        ) {
            items(cartItems) { cartItem ->
                CartItem(cartItem)
            }
        }
    }
}