package com.example.trainwell.Screen.Register.StepsClient

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.trainwell.Model.Login.Login
import com.example.trainwell.R
import com.example.trainwell.Routes
import com.example.trainwell.Screen.Register.Auxiliar.ProgressBarViewModel
import com.example.trainwell.ViewModel.Register.RegisterViewModel

@Composable
fun ClientScreenFinal(navController: NavHostController, rvm: RegisterViewModel) {
    val context = LocalContext.current
    var emailError by remember { mutableStateOf(false) }
    var usernameError by remember { mutableStateOf(false) }
    var passwdError by remember { mutableStateOf(false) }
    var passwordVisible by remember { mutableStateOf(false) }
    val registroExitoso by rvm.registroExitoso.collectAsState()
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(registroExitoso) {
        if (registroExitoso) {
            navController.navigate(Routes.DASHCLIENT) {
                popUpTo(Routes.REGISFINAL) { inclusive = true }
            }
            // Resetear el estado para que no vuelva a navegar al entrar de nuevo
            rvm.registroExitoso.value = false
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        colorResource(id = R.color.greenBG2), //verde mas clarito
                        colorResource(id = R.color.greenBG) // verde mas oscuro
                    )
                )
            )
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_loginimage),
            contentDescription = "Login Image",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .fillMaxWidth()
                .height(450.dp) // Ajusta según tu diseño
                .graphicsLayer { alpha = 0.99f }
                .drawWithContent {
                    drawContent()
                    drawRect(
                        brush = Brush.verticalGradient(
                            // INVERTIMOS: Sólido arriba (Blanco/Negro) y Transparente abajo
                            colors = listOf(
                                Color.Black,       // Parte superior (se ve la imagen)
                                Color.Transparent  // Parte inferior (se difumina)
                            ),
                            startY = size.height * 0.5f, // Empezamos a difuminar a la mitad
                            endY = size.height           // Terminamos en el borde inferior
                        ),
                        blendMode = BlendMode.DstIn // Aplica la transparencia del brush a la imagen
                    )
                }
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 250.dp)
                .padding(start = 20.dp)
                .padding(end = 20.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Ready to train?", color = Color.White, fontSize = 30.sp)
            Text(text = "CREATE YOUR ACCOUNT", color = colorResource(id=R.color.greenBT), fontSize = 30.sp)

            //Campo para el username
            OutlinedTextField(
                value = rvm.username,
                onValueChange = {
                    rvm.username = it
                    usernameError = it.isBlank()
                },
                label = { Text(text= "Username", color = colorResource(id=R.color.greyTXT)) },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.White,   // Color mientras escribe
                    unfocusedTextColor = Color.White, // Color cuando no está seleccionado
                    cursorColor = Color.White         // Color de la barra de escritura
                ),
                isError = usernameError,
                modifier = Modifier.fillMaxWidth().focusRequester(focusRequester),
                singleLine = true
            )

            if (usernameError) {
                Text(
                    text = "Username cannot be empty",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            //Campo para el EMAIL
            OutlinedTextField(
                value = rvm.email,
                onValueChange = {
                    rvm.email = it
                    emailError = it.isBlank() || !isValidEmail(it)
                },
                label = { Text(text= "Email", color = colorResource(id=R.color.greyTXT)) },
                isError = emailError,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.White,   // Color mientras escribe
                    unfocusedTextColor = Color.White, // Color cuando no está seleccionado
                    cursorColor = Color.White         // Color de la barra de escritura
                ),
                modifier = Modifier.fillMaxWidth().focusRequester(focusRequester),
                singleLine = true
            )

            if (emailError) {
                Text(
                    text = "Email cannot be empty or invalid",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            //Campo para la contraseña
            OutlinedTextField(
                value = rvm.password,
                onValueChange = {
                    rvm.password = it
                    passwdError = it.isBlank() || it.length < 6
                },
                label = { Text("Password", color = colorResource(id=R.color.greyTXT)) },
                isError = passwdError,
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.White,   // Color mientras escribe
                    unfocusedTextColor = Color.White, // Color cuando no está seleccionado
                    cursorColor = Color.White         // Color de la barra de escritura
                ),
                singleLine = true,
                visualTransformation = if (passwordVisible)
                    VisualTransformation.None
                else
                    PasswordVisualTransformation(),
                trailingIcon = {
                    val image = if (passwordVisible)
                        Icons.Default.Visibility
                    else Icons.Default.VisibilityOff

                    IconButton(onClick = {
                        passwordVisible = !passwordVisible
                    }) {
                        Icon(imageVector = image, "")
                    }
                },

                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            if (passwdError) {
                Text(
                    text = "Password cannot be empty",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }
            //si no existe, texto de que se ha equivocado en el login
//            if (existe == false) {
//                Text(
//                    text = "Incorrect email or password",
//                    color = MaterialTheme.colorScheme.error,
//                    modifier = Modifier.padding(top = 12.dp)
//                )
//            }

            //Botón para enviar
            ElevatedButton(
                onClick = {
                    if (rvm.username.isNotBlank() && rvm.email.isNotBlank() && rvm.password.isNotBlank()) {

                        usernameError = false
                        emailError = false
                        passwdError = false

                        rvm.finalizarRegistro()  //lo añade a la bbdd

                    } else {
                        usernameError = rvm.username.isBlank()
                        emailError = rvm.email.isBlank() || !isValidEmail(rvm.email)
                        passwdError = rvm.password.isBlank() || rvm.password.length < 6
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = rvm.username.isNotBlank() && rvm.email.isNotBlank() && rvm.password.isNotBlank(),
                colors = ButtonDefaults.buttonColors(
                    // Color cuando el botón está habilitado
                    containerColor = colorResource(id = R.color.greenBT), //color de fondo del boton
                    contentColor = Color.Black,
                    // Color cuando el botón NO está habilitado
                    disabledContainerColor = colorResource(id = R.color.greenCard), //color de fondo del boton
                    disabledContentColor = Color.Black)
            ) {
                Text("Create Account")
            }
            if (rvm.showErrorDialog) {
                AlertDialog(
                    onDismissRequest = { rvm.showErrorDialog = false },
                    confirmButton = {
                        TextButton(
                            onClick = { rvm.showErrorDialog = false }
                        ) {
                            Text("Accept")
                        }
                    },
                    title = { Text("Error") },
                    text = { Text("This account already exists") }
                )
            }
            Spacer(modifier = Modifier.padding(10.dp))

            Text(
                text = "Or create with",
                style = MaterialTheme.typography.labelMedium.copy(color = Color(0xFF64748B)),
                modifier = Modifier.padding(bottom = 16.dp),
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                BtGoogle {}
                Spacer(modifier = Modifier.width(16.dp))
                BtFacebook {}
            }
        }
    }
}

@Composable
fun BtGoogle(onClick: () -> Unit) {
    OutlinedButton(
        onClick = onClick,
        shape = MaterialTheme.shapes.medium
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.ic_google), //logo descargado
                contentDescription = "Google Logo",
                tint = Color.Unspecified,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Google", color = Color.White)
        }
    }
}

@Composable
fun BtFacebook(onClick: () -> Unit) {
    OutlinedButton(
        onClick = onClick,
        shape = MaterialTheme.shapes.medium
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.ic_facebook), //logo descargado
                contentDescription = "Facebook Logo",
                tint = Color.Unspecified,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Facebook", color = Color.White)
        }
    }
}

fun isValidEmail(email:String): Boolean {
    return email.endsWith("@gmail.com") || email.endsWith("@outlook.com") || email.endsWith("@hotmail.com")
}