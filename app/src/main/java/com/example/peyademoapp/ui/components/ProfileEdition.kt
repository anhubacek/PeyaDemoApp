package com.example.peyademoapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.core.model.User
import com.example.peyademoapp.viewmodel.ProfileViewModel

@Composable
fun ProfileEdition(
    viewModel: ProfileViewModel,
    navController: NavController,
    handleCancelEdition: () -> Unit,
    handleSaveChanges: (User) -> Unit
) {
    val userProfile = viewModel.userProfile.collectAsState().value
    val error = viewModel.error.collectAsState().value
    var editingPasssword by rememberSaveable { mutableStateOf(false) }
    var name by rememberSaveable { mutableStateOf(userProfile?.name ?: "") }
    var lastName by rememberSaveable { mutableStateOf(userProfile?.lastName ?: "") }
    var email by rememberSaveable { mutableStateOf(userProfile?.email ?: "") }
    var nationality by rememberSaveable { mutableStateOf(userProfile?.nationality ?: "") }
    var password by rememberSaveable { mutableStateOf(userProfile?.password ?: "") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
    ) {
        OutlinedTextField(
            value = name, onValueChange = { name = it },
            label = { Text("Nombre") },
            modifier = Modifier
                .fillMaxWidth()


        )
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            value = lastName, onValueChange = { lastName = it },
            label = { Text("Apellido") },
            modifier = Modifier
                .fillMaxWidth()

        )
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            value = email, onValueChange = { email = it },
            label = { Text("Correo electrónico") },
            modifier = Modifier
                .fillMaxWidth()

        )
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            value = nationality, onValueChange = { nationality = it },
            label = { Text("Nacionalidad") },
            modifier = Modifier
                .fillMaxWidth()

        )
        Spacer(modifier = Modifier.height(12.dp))


        if (editingPasssword) {
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                visualTransformation = PasswordVisualTransformation(),
                label = { Text("Nueva contraseña") },
                modifier = Modifier
                    .fillMaxWidth()

            )
            Spacer(modifier = Modifier.height(12.dp))
        }


        if (!editingPasssword) {
            Text(
                text = "Cambiar contraseña",
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                modifier = Modifier
                    .padding(top = 18.dp)
                    .clickable { editingPasssword = true },
                fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                style = TextStyle(textDecoration = TextDecoration.Underline)
            )
        }

        Text(
            text = error,
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
            color = Color.Red,
            modifier = Modifier.padding(top = 12.dp)

        )


        Row(
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,

            ) {
            Button(

                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFE0E0E0),
                    contentColor = Color.Black,

                    ),
                onClick = {
                    handleCancelEdition()
                }
            ) {
                Text(
                    text = "Cancelar",
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                )


            }



            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF51643F),
                    contentColor = Color.White
                ),
                onClick = {
                    handleSaveChanges(
                        User(
                            email = email,
                            name = name,
                            lastName = lastName,
                            nationality = nationality,
                            password = password
                        )
                    )
                }
            ) {
                Text(
                    text = "Guardar cambios",
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                )
            }


        }


    }


}