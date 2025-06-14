package com.example.peyademoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.core.model.Product
import com.example.peyademoapp.navigation.AppNavigation
import com.example.peyademoapp.ui.theme.PeyaDemoAppTheme

class MainActivity : ComponentActivity() {


    private val productList = listOf(
        Product("Zapatos", 2500.0),
        Product("Camisa", 1900.0),
        Product("Pantalón", 3200.0),
        Product("Gorra", 1200.0),
        Product("Remera", 1500.0),
        Product("Bufanda", 1100.0)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PeyaDemoAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavigation(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PeyaDemoAppTheme {
        Greeting("Android")
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