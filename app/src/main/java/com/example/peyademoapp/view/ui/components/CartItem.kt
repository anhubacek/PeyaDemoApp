package com.example.peyademoapp.view.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import coil.compose.rememberAsyncImagePainter
import com.example.peyademoapp.R
import com.example.peyademoapp.model.dataclass.CartItem
import com.example.peyademoapp.view.viewmodel.CartViewModel
import kotlinx.coroutines.launch

@SuppressLint("DefaultLocale")
@Composable
fun CartItem(
    cartItem: CartItem,
    cartViewModel: CartViewModel
) {
    var quantity by rememberSaveable { mutableStateOf(cartItem.quantity) }
    val viewModelScope = cartViewModel.viewModelScope


    fun onDecreaseQuantity() {
        viewModelScope.launch {
            if (quantity > 1) {
                quantity--
                cartViewModel.updateQuantity(cartItem, quantity)
            }
        }
    }

    fun onIncreaseQuantity() {
        quantity++
        viewModelScope.launch {
            cartViewModel.updateQuantity(cartItem, quantity)
        }

    }

    fun handleRemoveFromCart(cartItem: CartItem) {
        viewModelScope.launch {
            cartViewModel.removeFromCart(cartItem)
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .height(110.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {
        Row(
            modifier = Modifier
                .padding(bottom = 12.dp)
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = cartItem.product.imageUrl,
                    error = painterResource(R.drawable.image_error),
                    placeholder = painterResource(R.drawable.gray_placeholder)

                ),
                contentDescription = "Imagen del producto",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(110.dp)
                    .height(110.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
            Column(
                modifier = Modifier
                    .width(200.dp)
                    .height(110.dp)
                    .padding(start = 6.dp, end = 6.dp, top = 4.dp, bottom = 4.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = cartItem.product.name,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(horizontal = 6.dp),
                )
                Text(
                    text = String.format("$%.2f", cartItem.product.price),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 4.dp),
                )

            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(end = 4.dp, top = 4.dp, bottom = 4.dp)
            ) {
                IconButton(
                    onClick = { onIncreaseQuantity() },
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .size(30.dp)
                        .background(Color(0xFFF0F0F0))
                        .border(
                            width = 1.dp,
                            color = Color(0xFFBDBDBD),
                            shape = RoundedCornerShape(4.dp)
                        ),
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = "Agregar",
                        tint = Color.Black,

                        )
                }


                Box(
                    modifier = Modifier
                        .size(30.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = quantity.toString(),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                    )
                }

                IconButton(
                    onClick = { onDecreaseQuantity() },
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .size(30.dp)
                        .background(Color(0xFFF0F0F0))
                        .border(
                            width = 1.dp,
                            color = Color(0xFFBDBDBD),
                            shape = RoundedCornerShape(4.dp)
                        ),
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "Disminuir",
                        tint = Color.Black
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .align(Alignment.CenterVertically)
                    .padding(start = 4.dp),
                contentAlignment = Alignment.Center
            ) {
                IconButton(
                    onClick = {
                        handleRemoveFromCart(cartItem)

                    },
                    modifier = Modifier
                        .size(30.dp)

                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Agregar",
                    )
                }
            }


        }
    }

}

