package com.example.peyademoapp.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.peyademoapp.viewmodel.CartViewModel

@Composable
fun ShoppingCartScreen(
    viewModel: CartViewModel,
    navController: NavController
) {
    val coroutineScope = rememberCoroutineScope()
    val cartItems = viewModel.cartItems.collectAsState().value
    Scaffold(
        bottomBar = {
            BottomNavBar(navController)
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Text(
                text = "Mi Carrito"
            )
            Box() {
                if (cartItems.isEmpty()) {
                    Text(
                        text = "Tu carrito está vacío",
                        modifier = Modifier.padding(16.dp)
                    )
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF51643F),
                            contentColor = Color.White
                        ),
                        onClick = {
                            navController.navigate("home") {
                                popUpTo("cart") { inclusive = true }

                            }
                        }
                    ) {
                        Text(
                            "Comprar",
                            fontSize = MaterialTheme.typography.bodyLarge.fontSize
                        )
                    }
                } else {
                    Text(
                        text = "Tienes ${cartItems.size} productos en tu carrito",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }

        }


    }
}