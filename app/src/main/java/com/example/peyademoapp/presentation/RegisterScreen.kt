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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.peyademoapp.viewmodel.RegisterViewModel
import kotlinx.coroutines.launch

@Composable
@Preview(showBackground = true)
fun RegisterScreen(
    viewModel: RegisterViewModel = RegisterViewModel(),
    onSignUpSuccess: () -> Unit = {}
) {
    val coroutineScope = rememberCoroutineScope()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    suspend fun handleSignUp() {

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
            text = viewModel.signUpError,
            modifier = Modifier.padding(top = 16.dp),
            fontSize = MaterialTheme.typography.bodyLarge.fontSize
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
