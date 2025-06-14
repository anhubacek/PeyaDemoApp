package com.example.peyademoapp.data.repository

import com.example.peyademoapp.model.Product


class ProductRepository {
    val products = listOf(
        Product(
            "Zapatos",
            2500.0,
            "https://dcdn-us.mitiendanube.com/stores/001/719/894/products/remera_lisa_fucsia11-78c8df7ce5d737624416252554306232-640-0.png"
        ),
        com.example.peyademoapp.model.Product(
            "Camisa", 1900.0,
            "https://dcdn-us.mitiendanube.com/stores/001/719/894/products/remera_lisa_fucsia11-78c8df7ce5d737624416252554306232-640-0.png"
        ),
        Product(
            "Pantalón", 3200.0,
            "https://dcdn-us.mitiendanube.com/stores/001/719/894/products/remera_lisa_fucsia11-78c8df7ce5d737624416252554306232-640-0.png"
        ),
        Product(
            "Gorra", 1200.0,
            "https://dcdn-us.mitiendanube.com/stores/001/719/894/products/remera_lisa_fucsia11-78c8df7ce5d737624416252554306232-640-0.png"
        ),
        Product(
            "Remera", 1500.0,
            "https://dcdn-us.mitiendanube.com/stores/001/719/894/products/remera_lisa_fucsia11-78c8df7ce5d737624416252554306232-640-0.png"
        ),
        Product(
            "Bufanda", 1100.0,
            "https://dcdn-us.mitiendanube.com/stores/001/719/894/products/remera_lisa_fucsia11-78c8df7ce5d737624416252554306232-640-0.png"
        )
    )

    companion object {
        fun getProducts(): List<Product> {
            return ProductRepository().products
        }
    }
}




