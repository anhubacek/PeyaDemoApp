package com.example.peyademoapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.peyademoapp.view.ui.screens.LoginScreen
import com.example.peyademoapp.view.ui.screens.ProductsScreen
import com.example.peyademoapp.view.ui.screens.ProfileScreen
import com.example.peyademoapp.view.ui.screens.RegisterScreen
import com.example.peyademoapp.view.ui.screens.ShoppingCartScreen
import com.example.peyademoapp.view.viewmodel.CartViewModel
import com.example.peyademoapp.view.viewmodel.LoginViewModel
import com.example.peyademoapp.view.viewmodel.ProductsViewModel
import com.example.peyademoapp.view.viewmodel.ProfileViewModel
import com.example.peyademoapp.view.viewmodel.RegisterViewModel

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    val loginViewModel: LoginViewModel = viewModel()
    val registerViewModel: RegisterViewModel = viewModel()
    val productsViewModel: ProductsViewModel = viewModel()
    val cartViewModel: CartViewModel = viewModel()
    val profileViewModel: ProfileViewModel = viewModel()
    NavHost(
        navController = navController,
        startDestination = "login",
        modifier = modifier
    ) {
        composable("login") {
            LoginScreen(
                loginViewModel,
                navController

            )
        }

        composable("signup") {
            RegisterScreen(
                registerViewModel,
                navController

            )
        }
        composable("home") {
            ProductsScreen(
                productsViewModel,
                cartViewModel,
                navController
            )
        }
        composable("profile") {
            ProfileScreen(
                profileViewModel,
                navController,
            )
        }
        composable("cart") {
            ShoppingCartScreen(
                cartViewModel,
                navController
            )
        }

    }
}
