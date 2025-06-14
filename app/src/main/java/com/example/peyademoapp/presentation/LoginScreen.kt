package com.example.peyademoapp.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.peyademoapp.viewmodel.LoginViewModel
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onLoginSuccess: () -> Unit = {},
    navController: NavController
) {
    val coroutineScope = rememberCoroutineScope()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val error = viewModel.loginError.collectAsState("").value
    val loading = viewModel.loading.collectAsState(false).value

    suspend fun handleLogin() {
        if (viewModel.login(email, password)) {
            onLoginSuccess()
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 36.dp, vertical = 16.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "RAÍZ",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 6.dp, top = 80.dp)

        )
        Text(
            text = "URBANA",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 36.dp)

        )

        if (loading) {
            Loader()
        } else {
            OutlinedTextField(
                value = email, onValueChange = { email = it },
                label = { Text("Correo electrónico") },
                modifier = Modifier
                    .fillMaxWidth()
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                visualTransformation = PasswordVisualTransformation(),
                label = { Text("Contraseña") },
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth()

            )


            Text(
                text = error,
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                color = Color.Red
            )

            Button(
                modifier = Modifier.padding(top = 36.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF51643F),
                    contentColor = Color.White
                ),
                onClick = {
                    coroutineScope.launch {
                        handleLogin()
                    }
                }
            ) {
                Text(
                    "Iniciar sesión",
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize
                )
            }
            Text(
                text = "Aún no estas registrado?",
                modifier = Modifier.padding(top = 36.dp),
                fontSize = MaterialTheme.typography.bodyLarge.fontSize
            )
            Text(
                text = "Crear una cuenta",
                modifier = Modifier.padding(top = 8.dp),
                fontWeight = FontWeight.Bold,
                fontSize = MaterialTheme.typography.bodyLarge.fontSize
            )

        }


    }

}
