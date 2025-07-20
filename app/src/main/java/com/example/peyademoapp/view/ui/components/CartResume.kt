package com.example.peyademoapp.view.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import com.example.peyademoapp.model.CartItem
import com.example.peyademoapp.view.viewmodel.CartViewModel
import kotlinx.coroutines.launch

@Composable
fun CartResume(
    items: List<CartItem>,
    cartViewModel: CartViewModel,
    handleChangeViewToOrders: () -> Unit
) {
    val total = items.sumOf { it.product.price * it.quantity }
    val viewModelScope = cartViewModel.viewModelScope

    fun handleConfirmPurchase() {
        viewModelScope.launch {
            if (cartViewModel.confirmOrder()) {
                handleChangeViewToOrders()
                cartViewModel.clearCart()
            }
        }

    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        items.forEach { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "${item.product.name} x${item.quantity}")
                Text(text = "%.2f €".format(item.product.price * item.quantity))
            }
        }
        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Total", style = MaterialTheme.typography.titleMedium)
            Text(text = "%.2f €".format(total), style = MaterialTheme.typography.titleMedium)
        }

        Button(
            onClick = { handleConfirmPurchase() },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF51643F),
                contentColor = Color.White
            ),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Confirmar compra")
        }
    }
}
