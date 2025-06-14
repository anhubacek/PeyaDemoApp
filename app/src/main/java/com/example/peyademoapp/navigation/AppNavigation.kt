package com.example.peyademoapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.peyademoapp.presentation.LoginScreen
import com.example.peyademoapp.presentation.ProductsScreen
import com.example.peyademoapp.presentation.ProfileScreen
import com.example.peyademoapp.presentation.RegisterScreen
import com.example.peyademoapp.viewmodel.LoginViewModel
import com.example.peyademoapp.viewmodel.ProductsViewModel

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    val loginViewModel: LoginViewModel = viewModel()
    val productsViewModel: ProductsViewModel = viewModel()
    NavHost(
        navController = navController,
        startDestination = "home", // Change to "login" later
        modifier = modifier
    ) {
        composable("login") {
            LoginScreen(
                loginViewModel,
                onLoginSuccess = {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                navController

            )
        }

        composable("signup") {
            RegisterScreen(
                onSignUpSuccess = {
                    navController.navigate("home") {
                        popUpTo("signup") { inclusive = true }
                    }
                }

            )
        }
        composable("home") {
            ProductsScreen(
                productsViewModel,
                navController
            )
        }
        composable("profile") {
            ProfileScreen(
                onLogout = {
                    navController.navigate("login") {
                        popUpTo("home") { inclusive = true }
                    }
                },
                navController
            )
        }

    }
}
