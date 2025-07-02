package com.example.peyademoapp.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.core.model.CartItem
import com.example.core.model.Product
import com.example.peyademoapp.R
import com.example.peyademoapp.viewmodel.CartViewModel

@SuppressLint("DefaultLocale")
@Composable
fun ProductItem(product: Product, cartViewModel: CartViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent),
        shape = RoundedCornerShape(12.dp),
        //elevation = CardDefaults.cardElevation(8.dp)
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {
        Column(
            modifier = Modifier.padding(bottom = 12.dp)
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            ) {

                Image(
                    painter = rememberAsyncImagePainter(
                        model = product.imageUrl,
                        error = painterResource(R.drawable.image_error),
                        placeholder = painterResource(R.drawable.gray_placeholder)

                    ),
                    contentDescription = "Imagen del producto",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(12.dp))
                )


                IconButton(
                    onClick = {
                        cartViewModel.addToCart(
                            CartItem(
                                product = product,
                                quantity = 1
                            )
                        )


                    },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .shadow(8.dp, shape = CircleShape, clip = false)
                        .background(Color(0xFF4CAF50), shape = CircleShape)
                        .size(30.dp)
                        .clip(CircleShape)

                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Agregar al carrito",
                        tint = Color.White,
                    )

                }
            }

            Spacer(modifier = Modifier.height(6.dp))


            Text(
                text = String.format("$%.2f", product.price),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(horizontal = 6.dp, vertical = 4.dp)
            )
            Text(
                text = product.name,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                modifier = Modifier.padding(horizontal = 6.dp)
            )

        }
    }
}