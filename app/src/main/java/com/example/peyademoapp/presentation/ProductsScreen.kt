package com.example.peyademoapp.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.peyademoapp.model.Product
import com.example.peyademoapp.viewmodel.ProductsViewModel


@Composable
fun ProductsScreen(
    viewModel: ProductsViewModel,
    navController: NavController
) {
    val products = viewModel.filteredProducts.collectAsState().value
    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        bottomBar = {
            BottomNavBar(navController)
        }

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
                    viewModel.filterProducts(searchQuery)
                },
                placeholder = { Text("Buscar productos") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            )
            ProductsList(
                products = products,
                modifier = Modifier.padding(16.dp)
            )

        }
    }
}


@Composable
fun ProductsList(products: List<Product>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(products.size) { index ->
            ProductItem(
                product = products[index],
            )
        }
    }

}

@Composable
fun ProductItem(product: Product) {
    Card {
        Column {
            Text(
                text = product.name,
                modifier = Modifier.padding(8.dp)
            )
            Text(
                text = "\$${product.price}",
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}