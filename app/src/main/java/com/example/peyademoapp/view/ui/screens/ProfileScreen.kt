package com.example.peyademoapp.view.ui.screens

import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.core.model.User
import com.example.peyademoapp.R
import com.example.peyademoapp.view.ui.components.BottomNavBar
import com.example.peyademoapp.view.ui.components.ProfileEdition
import com.example.peyademoapp.view.viewmodel.ProfileViewModel


@RequiresApi(Build.VERSION_CODES.P)
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

    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current
    var editingProfile by rememberSaveable { mutableStateOf(false) }
    val userProfile = viewModel.userProfile.collectAsState().value
    val isImageUploading = viewModel.isImageUploading.collectAsState().value
    fun handleCancelEdition() {
        editingProfile = false
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
    ) { uri: Uri? ->
        imageUri = uri
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

            if (imageUri != null) {
                val bitmap = remember(imageUri) {
                    val source = ImageDecoder.createSource(context.contentResolver, imageUri!!)
                    ImageDecoder.decodeBitmap(source).asImageBitmap()
                }

                AlertDialog(
                    onDismissRequest = {},
                    confirmButton = {},
                    dismissButton = {},
                    title = {
                        Text(
                            text = "Confirmar foto de perfil",
                            fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        )
                    },
                    text = {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()

                        ) {
                            if (isImageUploading) {
                                CircularProgressIndicator()
                            } else {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                ) {
                                    Image(
                                        bitmap = bitmap,
                                        contentDescription = "Profile Image",
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .clip(CircleShape)
                                            .size(170.dp)
                                            .align(Alignment.CenterHorizontally)
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
                                                imageUri = null
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
                                                viewModel.saveProfileImage(imageUri ?: Uri.EMPTY)
                                                imageUri = null
                                            }
                                        ) {
                                            Text(
                                                text = "Guardar",
                                                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                                            )
                                        }


                                    }


                                }

                            }

                        }
                    },

                    )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Mi Perfil",
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    fontWeight = MaterialTheme.typography.titleLarge.fontWeight
                )
                if (editingProfile.not()) {
                    Row(
                        modifier = Modifier
                            .clickable { editingProfile = true }
                            .padding(8.dp),
                    ) {
                        Text(
                            text = "Editar",
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            modifier = Modifier
                                .padding(end = 8.dp),
                            fontWeight = MaterialTheme.typography.titleSmall.fontWeight
                        )
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Editar perfil",
                            modifier = Modifier.size(20.dp)
                        )

                    }
                }


            }



            Box(
                modifier = Modifier
                    .size(140.dp)
                    .clickable {
                        launcher.launch("image/*")

                    }
                    .align(Alignment.CenterHorizontally)
            ) {
                if (userProfile?.image != null) {
                    Image(
                        painter = rememberAsyncImagePainter(
                            model = userProfile.image,
                            error = painterResource(R.drawable.image_error),
                            placeholder = painterResource(R.drawable.gray_placeholder)

                        ),
                        contentDescription = "Profile Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape)
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


