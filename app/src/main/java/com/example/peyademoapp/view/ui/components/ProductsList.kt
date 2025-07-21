package com.example.peyademoapp.view.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.peyademoapp.model.dataclass.Product
import com.example.peyademoapp.view.viewmodel.CartViewModel
import com.example.peyademoapp.view.viewmodel.ProductsViewModel

@Composable
fun ProductsList(
    products: List<Product>,
    modifier: Modifier = Modifier,
    cartViewModel: CartViewModel,
    productsViewModel: ProductsViewModel
) {

    val loading = productsViewModel.loading.collectAsState().value

    if (loading) {
        Loader()
    } else if (products.isNotEmpty()) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = modifier
                .fillMaxSize()
                .padding(bottom = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            items(products.size) { index ->
                ProductItem(
                    product = products[index],
                    cartViewModel = cartViewModel
                )
            }


        }
    }


}