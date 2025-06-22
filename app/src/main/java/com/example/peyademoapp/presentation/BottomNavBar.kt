package com.example.peyademoapp.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun BottomNavBar(
    navController: NavController,
    containerColor: Color = Color.White,
    modifier: Any = Modifier
        .height(80.dp)
        .shadow(
            elevation = 16.dp,
        )
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BottomBarIcon(
            icon = Icons.Default.Home,
            label = "Inicio",
            link = "home",
            navController
        )
        BottomBarIcon(
            icon = Icons.Default.ShoppingCart,
            label = "Mis pedidos",
            link = "cart",
            navController
        )
        BottomBarIcon(
            icon = Icons.Default.AccountCircle,
            label = "Perfil",
            link = "profile",
            navController
        )
    }
}

@Composable
fun BottomBarIcon(
    icon: ImageVector,
    label: String,
    link: String = "home",
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .width(100.dp)
            .height(60.dp)
            .clickable {
                navController.navigate(link) {
                    popUpTo("home") { inclusive = true }
                }
            }
    ) {
        Icon(imageVector = icon, contentDescription = label, tint = Color.Black)
        Text(
            text = label, color = Color.Black
        )
    }
}