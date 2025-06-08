package com.example.peyademoapp.view.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.peyademoapp.view.viewmodel.LoginViewModel

@Composable
@Preview(showBackground = true)
fun HomeScreen() {


   Scaffold(
       bottomBar = {
           BottomAppBar(
               containerColor = Color.White,
                modifier = androidx.compose.ui.Modifier.height(70.dp)
                    .shadow(
                        elevation = 16.dp,
                    )


           ) {
               Row(
                     modifier = androidx.compose.ui.Modifier
                          .fillMaxWidth(),
                     horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceAround,
                     verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
               ){
                   BottomBarIcon(
                       icon = androidx.compose.material.icons.Icons.Default.Home,
                       label = "Inicio",
                       link = "home",
                       modifier = Modifier.width(86.dp)
                   )
                     BottomBarIcon(
                          icon = androidx.compose.material.icons.Icons.Default.ShoppingCart,
                          label = "Mis pedidos",
                          link = "orders",
                          modifier = Modifier.width(86.dp)
                     )
                     BottomBarIcon(
                          icon = androidx.compose.material.icons.Icons.Default.AccountCircle,
                          label = "Perfil",
                          link = "profile",
                          modifier = Modifier.width(86.dp)
                        )

               }


           }
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
fun BottomBarIcon(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    link: String = "home",
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    Column(

        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
        modifier = modifier
            .width(80.dp)
            .clickable {
                navController.navigate(link) {
                    popUpTo("home") { inclusive = true }
                }
            }
    ) {
        Icon(imageVector = icon, contentDescription = label, tint = Color.Black)
        Text(text = label, color = Color.Black)
    }
}