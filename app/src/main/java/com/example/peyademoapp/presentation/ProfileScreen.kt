package com.example.peyademoapp.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.peyademoapp.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel, navController: NavController
) {

    fun handleLogout() {
        if (viewModel.logout()) {
            navController.navigate("login") {
                popUpTo("home") { inclusive = true }
            }
        }

    }
    Scaffold(
        bottomBar = {
             BottomNavBar(navController)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(top = 16.dp)
        ) {
            Text(
                text = "Mi Perfil",
                fontSize = MaterialTheme.typography.headlineLarge.fontSize,
            )
        }


    }
}


