package com.example.peyademoapp.data.implementations

import com.example.peyademoapp.data.ProductsDataSource
import com.example.peyademoapp.model.Product
import javax.inject.Inject

class ProductsDataSourceImplementation
@Inject constructor(
) : ProductsDataSource {
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
        ),
        Product(
            id = "5",
            name = "Hot dog especial",
            price = 50.0,
            imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQaKqDVCWtGQzA2-d1WvRmTfPi0krczx2pwzQ&s",
            description = "Salchicha jumbo, tocino, cebolla caramelizada y mostaza.",
            hasDrink = true,
        ),
        Product(
            id = "6",
            name = "Ensalada César",
            price = 55.0,
            imageUrl = "https://cdn5.recetasdeescandalo.com/wp-content/uploads/2016/06/Ensalada-cesar-con-pollo-y-su-salsa-casera.-Receta-e-ingredientes.jpg",
            description = "Lechuga romana, crutones, parmesano y aderezo César.",
            hasDrink = false,
        ),
        Product(
            id = "7",
            name = "Sushi roll california",
            price = 85.0,
            imageUrl = "https://cocinista.b-cdn.net/download/bancorecursos/recetas/receta-california-maki-rollito.jpg",
            description = "Arroz, cangrejo, pepino y aguacate.",
            hasDrink = false,
        ),
        Product(
            id = "8",
            name = "Pollo a la plancha",
            price = 75.0,
            imageUrl = "https://lh4.googleusercontent.com/proxy/qc_EMK-WnHlq9YIMKmFV7RR94zRD-SqMAVPosDxhZwMykJ4wM78Qta0QeDXzq5SGGXD05XwxLdH8EArK4Jc5ndKnR1PWxFSAdO3VesMQJMJ1hFLp4rDUeQz_wAq1lScESN3RUxOTGHVsSZgBwfs",
            description = "Filete de pollo con vegetales al vapor.",
            hasDrink = false,
        )
    )

    override suspend fun getProducts(): List<Product> {
        return _products

    }
}