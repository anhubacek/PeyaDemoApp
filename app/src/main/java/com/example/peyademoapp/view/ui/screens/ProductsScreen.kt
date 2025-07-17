package com.example.peyademoapp.view.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.peyademoapp.view.ui.components.BottomNavBar
import com.example.peyademoapp.view.ui.components.FloatingCart
import com.example.peyademoapp.view.ui.components.ProductsList
import com.example.peyademoapp.view.viewmodel.CartViewModel
import com.example.peyademoapp.view.viewmodel.ProductsViewModel

@Composable
fun ProductsScreen(
    productsViewModel: ProductsViewModel,
    cartViewModel: CartViewModel,
    navController: NavController
) {
    val coroutineScope = rememberCoroutineScope()
    val products = productsViewModel.filteredProducts.collectAsState().value
    val cartItems = cartViewModel.cartItems.collectAsState().value
    var searchQuery by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(Unit) {
        productsViewModel.init()
    }

    Scaffold(
        bottomBar = {
            BottomNavBar(navController)
        },
        floatingActionButton = {
            if (cartItems.size > 0) {
                FloatingCart(cartViewModel, navController)
            }
        },
        floatingActionButtonPosition = FabPosition.Center

    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            TextField(
                value = searchQuery,
                onValueChange = {
                    searchQuery = it
                    productsViewModel.filterProducts(searchQuery)
                },
                placeholder = {
                    Text(text = "Buscar productos", fontSize = 20.sp)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent
                ),
            )
            ProductsList(
                products = products,
                modifier = Modifier.padding(16.dp),
                cartViewModel = cartViewModel
            )

        }
    }
}




