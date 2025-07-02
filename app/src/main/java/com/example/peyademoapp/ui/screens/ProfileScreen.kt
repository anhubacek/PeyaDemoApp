package com.example.peyademoapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.core.model.User
import com.example.peyademoapp.ui.components.BottomNavBar
import com.example.peyademoapp.ui.components.ProfileEdition
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

    val imageUri = null
    var editingProfile by rememberSaveable { mutableStateOf(false) }
    val userProfile = viewModel.userProfile.collectAsState().value
    fun handleCancelEdition() {
        editingProfile = false
    }

    Scaffold(
        bottomBar = {
            BottomNavBar(navController)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 36.dp, vertical = 16.dp)
                .fillMaxWidth(),
        ) {

            Box(
                modifier = Modifier
                    .size(170.dp)
                    .clickable {

                    }
                    .align(Alignment.CenterHorizontally)
            ) {
                if (imageUri != null) {
                    Image(
                        painter = rememberAsyncImagePainter(imageUri),
                        contentDescription = "Profile Image"
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFE0E0E0)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Subir foto",
                            color = Color.Black,
                            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                            modifier = Modifier
                                .padding(8.dp)

                        )
                    }
                }

            }
            if (editingProfile) {
                ProfileEdition(
                    viewModel = viewModel, navController = navController,
                    handleCancelEdition = { handleCancelEdition() },
                    handleSaveChanges = { data: User ->
                        // if save changes returns true, exit editing mode
                        if (viewModel.handleSaveChanges(data)) {
                            editingProfile = false
                        }
                    }

                )
            } else {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                        .padding(vertical = 30.dp),
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(250.dp)
                            .padding(vertical = 8.dp)
                            .align(Alignment.CenterVertically)

                    ) {

                        Text(
                            text = "Nombre",
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            modifier = Modifier.padding(bottom = 8.dp),
                            fontWeight = MaterialTheme.typography.titleMedium.fontWeight
                        )
                        Text(
                            text = "${userProfile?.name ?: ""} ${userProfile?.lastName ?: ""}",
                            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                        )
                        Spacer(
                            modifier = Modifier.height(20.dp)
                        )
                        Text(
                            text = "Correo electrónico",
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            modifier = Modifier.padding(bottom = 8.dp),
                            fontWeight = MaterialTheme.typography.titleMedium.fontWeight
                        )
                        Text(
                            text = userProfile?.email ?: "",
                            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                        )
                        Spacer(
                            modifier = Modifier.height(20.dp)
                        )
                        Text(
                            text = "Nacionalidad",
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            modifier = Modifier.padding(bottom = 8.dp),
                            fontWeight = MaterialTheme.typography.titleMedium.fontWeight
                        )
                        Text(
                            text = userProfile?.nationality ?: "",
                            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                        )
                        Spacer(
                            modifier = Modifier.height(20.dp)
                        )


                    }
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .align(Alignment.Top),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top
                    ) {

                        IconButton(
                            onClick = { editingProfile = true },
                            modifier = Modifier
                                .align(Alignment.Start)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "Editar perfil",
                                modifier = Modifier
                                    .padding(top = 16.dp)

                            )
                        }

                    }


                }

                Text(
                    text = "Cerrar sesión",
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    modifier = Modifier
                        .padding(top = 45.dp)
                        .clickable { handleLogout() }
                        .align(Alignment.CenterHorizontally),
                    fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                    style = TextStyle(textDecoration = TextDecoration.Underline),

                    )

            }


        }


    }
}


