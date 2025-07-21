package com.example.peyademoapp.view.ui.screens

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
import androidx.compose.runtime.LaunchedEffect
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
import com.example.peyademoapp.view.ui.components.BottomNavBar
import com.example.peyademoapp.view.ui.components.CartList
import com.example.peyademoapp.view.ui.components.CartResume
import com.example.peyademoapp.view.ui.components.Loader
import com.example.peyademoapp.view.viewmodel.CartViewModel

@Composable
fun ShoppingCartScreen(
    cartViewModel: CartViewModel,
    navController: NavController
) {
    val cartItems = cartViewModel.cartItems.collectAsState().value
    val orders = cartViewModel.orders.collectAsState().value
    val isLoadingOrders = cartViewModel.isLoadingOrders.collectAsState().value
    val isLoadingCartItems = cartViewModel.isLoadingCartItems.collectAsState().value
    var selectedTabIndex by rememberSaveable { mutableStateOf(0) }
    val tabs = listOf("Mi carrito", "Mis pedidos")

    LaunchedEffect(Unit) {
        cartViewModel.refreshCartItems()
        cartViewModel.loadOrders()
    }
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
                    val backgroundColor = if (isSelected) Color(0xFF51643F) else Color.LightGray
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
                    if (cartItems.isEmpty() && isLoadingCartItems.not()) {
                        Text(
                            text = "Tu carrito está vacío",
                            modifier = Modifier.padding(16.dp),
                            fontSize = 20.sp
                        )
                    } else if (cartItems.isNotEmpty()) {
                        Column {
                            CartList(cartItems, cartViewModel)
                            CartResume(cartItems, cartViewModel, handleChangeViewToOrders = {
                                selectedTabIndex = 1
                            })

                        }

                    } else {
                        Loader()
                    }

                1 -> {
                    if (isLoadingOrders) {
                        Loader()
                    } else {
                        if (orders.isNotEmpty()) {
                            OrdersHistoryScreen(
                                orders = orders,
                                modifier = Modifier.padding(16.dp)
                            )

                        } else {
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
    }
}
