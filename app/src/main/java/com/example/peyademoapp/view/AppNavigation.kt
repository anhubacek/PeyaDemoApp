package com.example.peyademoapp.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.peyademoapp.view.ui.HomeScreen
import com.example.peyademoapp.view.ui.LoginScreen
import com.example.peyademoapp.view.ui.RegisterScreen
import kotlinx.coroutines.delay

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login" ,
        modifier = modifier
    ) {
        composable("login") {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                }

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
            HomeScreen()
        }

    }
}
