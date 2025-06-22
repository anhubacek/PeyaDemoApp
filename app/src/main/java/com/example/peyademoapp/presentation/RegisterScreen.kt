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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.peyademoapp.viewmodel.RegisterViewModel
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel,
    navController: NavController
) {
    val coroutineScope = rememberCoroutineScope()
    var email by rememberSaveable { mutableStateOf("") }
    var name by rememberSaveable { mutableStateOf("") }
    var lastName by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val error = viewModel.signUpError.collectAsState("").value
    val loading = viewModel.loading.collectAsState(false).value

    suspend fun handleSignUp() {
        if (viewModel.signUp(email, name, lastName, password, confirmPassword)) {
            navController.navigate("home") {
                popUpTo("signup") { inclusive = true }
            }
        }

    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 36.dp, vertical = 16.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "RAÍZ URBANA",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(vertical = 36.dp)

        )
        if (loading) {
            Loader()
        } else {
            OutlinedTextField(
                value = name, onValueChange = { name = it },
                label = { Text("Nombre") },
                modifier = Modifier
                    .fillMaxWidth()

            )
            OutlinedTextField(
                value = lastName, onValueChange = { lastName = it },
                label = { Text("Apellido") },
                modifier = Modifier
                    .fillMaxWidth()

            )
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
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                visualTransformation = PasswordVisualTransformation(),
                label = { Text("Confirmar contraseña") },
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
                        handleSignUp()
                    }
                }
            ) {
                Text(
                    "Crear cuenta",
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize
                )
            }


        }


    }

}
