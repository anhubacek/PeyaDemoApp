package com.example.peyademoapp.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.core.model.Product

@Composable
@Preview(showBackground = true)
fun ProductsScreen() {
    Scaffold(
        bottomBar = {
            BottomNavBar()
        }

    ) { innerPadding ->
        Column(
            modifier = androidx.compose.ui.Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Text(text = "Home")
        }
    }
}


@Composable
fun ProductsList(products: List<Product>, modifier: Modifier = Modifier) {
    LazyColumn {
        items(products.size) { index ->
            Text(
                text = "${products[index].name} - \$${products[index].price}",

                )
        }
    }

}