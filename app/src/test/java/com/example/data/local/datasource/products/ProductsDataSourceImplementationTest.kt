package com.example.data.local.datasource.products

import com.example.data.local.repository.products.ProductsDataSourceImplementation
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class ProductsDataSourceImplementationTest {
    @Test
    fun `Cuando llamo getProducts devuelve una lista de 8 productos`() {

        val productsDataSource = ProductsDataSourceImplementation()

        val result = runBlocking {
            productsDataSource.getProducts()
        }
        assertEquals(8, result.size)


    }

}