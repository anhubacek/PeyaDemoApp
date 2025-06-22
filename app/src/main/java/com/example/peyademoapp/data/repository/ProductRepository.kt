package com.example.peyademoapp.data.repository

import com.example.peyademoapp.model.Product

class ProductRepository(
) {
    private val _products = listOf(
        Product(
            id = "1",
            name = "Tacos al pastor",
            price = 45.0,
            imageUrl = "https://comedera.com/wp-content/uploads/sites/9/2017/08/tacos-al-pastor-receta.jpg",
            description = "Tortillas con carne de cerdo marinada, piña y cebolla.",
            hasDrink = true,
        ),
        Product(
            id = "2",
            name = "Burrito de res",
            price = 60.0,
            imageUrl = "https://www.recetasnestle.com.mx/sites/default/files/srh_recipes/cff9881a271d21ae3d098ba68d5ecd18.jpg",
            description = "Con arroz, frijoles, carne de res y queso.",
            hasDrink = false,
        ),
        Product(
            id = "3",
            name = "Hamburguesa clásica",
            price = 70.0,
            imageUrl = "https://imag.bonviveur.com/hamburguesa-clasica.jpg",
            description = "Pan artesanal, carne de res, lechuga, tomate y mayonesa.",
            hasDrink = false,
        ),
        Product(
            id = "4",
            name = "Pizza margarita",
            price = 120.0,
            imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTDx46gfGPL3XKmoiXU_pQzvINxjjOFsXLoAA&s",
            description = "Salsa de tomate, mozzarella y albahaca fresca.",
            hasDrink = false,
        )
    )

    fun getProducts(): List<Product> {
        return _products
    }
}




