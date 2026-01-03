package com.example.trainwell.Screen.Login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Label
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.trainwell.Model.Login.Login
import com.example.trainwell.R
import com.example.trainwell.Routes
import com.example.trainwell.ViewModel.Login.LoginViewModel

@Composable
fun Login(
    navController: NavHostController,
    viewModel: LoginViewModel
){
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var passwd by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf(false) }
    var passwdError by remember { mutableStateOf(false) }
    val existe by viewModel.usuarioExiste.observeAsState()
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(existe) {
        if (existe == true) {
            navController.navigate(Routes.register)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top= 250.dp)
            .padding(start = 20.dp)
            .padding(end = 20.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "LOG IN",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        //Campo para el nombre
        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                emailError = it.isBlank()
            },
            label = { Text("Email") },
            isError = emailError,
            modifier = Modifier.fillMaxWidth().focusRequester(focusRequester),
            singleLine = true
        )
        if (emailError) {
            Text(
                text = "El email no puede estar vacío",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        //Campo para la contraseña
        OutlinedTextField(
            value = passwd,
            onValueChange = {
                passwd = it
                passwdError = it.isBlank()
            },
            label = { Text("Password") },
            isError = passwdError,
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        if (passwdError) {
            Text(
                text = "La contraseña no puede estar vacía",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }
        //si no existe, texto de que se ha equivocado en el login
        if (existe==false) {
            Text(
                text = "Email o contraseña incorrectos",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 12.dp)
            )
        }

        //Botón para enviar
        Button(
            onClick = {
                if (email.isNotBlank() && passwd.isNotBlank()) {
                    var user = Login(email, passwd)
                    viewModel.getUser(user)  //llamamos al viewmodel y le pasamos el usuario
                    emailError = false
                    passwdError = false
                    focusRequester.requestFocus() //Devuelve el foco a la caja de texto nombre.
                } else {
                    emailError = email.isBlank()
                    passwdError = passwd.isBlank()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = email.isNotBlank() && passwd.isNotBlank()
        ) {
            Text("Log in")
        }

        Spacer(modifier = Modifier.padding(10.dp))

        Text(
            text = "Or continue with",
            style = MaterialTheme.typography.labelMedium.copy(color = Color(0xFF64748B)),
            modifier = Modifier.padding(bottom = 16.dp),
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            BtGoogle{}
            Spacer(modifier = Modifier.width(16.dp))
            BtFacebook {}
        }

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Don´t have account?",
                style = MaterialTheme.typography.labelMedium.copy(color = Color(0xFF64748B)),
            )
            TextButton(
                onClick = {
                    navController.navigate(Routes.register) //irse al formulario de registro
                }
            ) {
                Text(
                    text = "Create now",
                    style = MaterialTheme.typography.labelLarge.copy(
                        color = Color(0xFF64748B),
                        fontWeight = FontWeight.Bold)
                )
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
            Text(text = "Google", color = Color.Black)
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
            Text(text = "Facebook", color = Color.Black)
        }
    }
}