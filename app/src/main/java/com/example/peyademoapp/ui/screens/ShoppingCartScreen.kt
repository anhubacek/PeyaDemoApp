package com.example.peyademoapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.peyademoapp.ui.components.BottomNavBar
import com.example.peyademoapp.ui.components.CartList
import com.example.peyademoapp.viewmodel.CartViewModel

@Composable
fun ShoppingCartScreen(
    cartViewModel: CartViewModel,
    navController: NavController
) {
    val cartItems = cartViewModel.cartItems.collectAsState().value
    var selectedTabIndex by rememberSaveable { mutableStateOf(0) }
    val tabs = listOf("Mi carrito", "Mis pedidos")

    Scaffold(
        bottomBar = {
            BottomNavBar(navController)
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                tabs.forEachIndexed { index, title ->
                    val isSelected = selectedTabIndex == index
                    val backgroundColor = if (isSelected) Color(0xFF4CAF50) else Color.LightGray
                    val textColor = if (isSelected) Color.White else Color.Black

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 4.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .background(backgroundColor)
                            .clickable { selectedTabIndex = index }
                            .padding(vertical = 12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = title,
                            color = textColor,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }

            when (selectedTabIndex) {
                0 ->
                    if (cartItems.isEmpty()) {
                        Text(
                            text = "Tu carrito está vacío",
                            modifier = Modifier.padding(16.dp),
                            fontSize = 20.sp
                        )
                    } else {
                        CartList(cartItems, cartViewModel)
                    }

                1 -> {

                    Text(
                        text = "No tienes pedidos aún",
                        modifier = Modifier.padding(16.dp),
                        fontSize = 20.sp
                    )
                }
            }
        }


    }
}