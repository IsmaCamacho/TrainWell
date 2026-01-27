package com.example.trainwell.Screen.Register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.trainwell.Model.Register.Customer
import com.example.trainwell.Model.Register.Trainer
import com.example.trainwell.Model.Register.User
import com.example.trainwell.R
import com.example.trainwell.ViewModel.Register.RegisterViewModel
import java.time.LocalDateTime

@Composable
fun TrainerForm(
    navController: NavHostController,
    viewModel: RegisterViewModel
) {
    val context = LocalContext.current
    var name by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var biography by remember { mutableStateOf("") }
    var passwd by remember { mutableStateOf("") }
    var bioError by remember { mutableStateOf(false) }
    var priceError by remember { mutableStateOf(false) }
    var nameError by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
    var passwdError by remember { mutableStateOf(false) }

    val focusRequester = remember { FocusRequester() }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(
            brush = Brush.verticalGradient(colors = listOf(colorResource(id = R.color.greenBG2), colorResource(id = R.color.greenBG)))))
    {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp)
                .padding(start = 20.dp)
                .padding(end = 20.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "REGISTER",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            OutlinedTextField(
                value = name,
                onValueChange = {
                    name = it
                    nameError = it.isBlank()
                },
                label = { Text("Name") },
                isError = nameError,
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
                singleLine = true
            )

            if (nameError) {
                Text(
                    text = "Name cannot be empty",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            //Campo para el EMAIL
            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                    emailError = it.isBlank()
                },
                label = { Text("Email") },
                isError = emailError,
                modifier = Modifier
                    .fillMaxWidth(),
                singleLine = true
            )

            if (emailError) {
                Text(
                    text = "Email cannot be empty",
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
                    text = "Password cannot be empty",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            // Campo para objetivo
            // Hacer desplegable en un futuro
            OutlinedTextField(
                value = biography,
                onValueChange = {
                    biography = it
                    bioError = it.isBlank()
                },
                label = { Text("Bio") },
                isError = priceError,
                modifier = Modifier
                    .fillMaxWidth()
            )

            if (bioError) {
                Text(
                    text = "Bio cannot be empty",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            // Campo para peso
            OutlinedTextField(
                value = price,
                onValueChange = {
                    price = it
                    priceError = it.isBlank() || it.toDoubleOrNull() == null
                },
                label = { Text("Price") },
                isError = priceError,
                modifier = Modifier
                    .fillMaxWidth()
            )
            
            if (priceError) {
                val errorMessage = when {
                    price.isBlank() -> "Price cannot be empty"
                    price.toDoubleOrNull() == null -> "Please enter a valid number (e.g. 75.5)"
                    else -> ""
                }
                Text(
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            //Botón para registrar
            Button(
                onClick = {
                    if (email.isNotBlank() && passwd.isNotBlank() && name.isNotBlank() &&
                        biography.isNotBlank() && price.toDoubleOrNull() != null && price.isNotBlank()
                    ) {
                        // Añadimos el usuario general con su cliente
                        var user = User(
                            name = name,
                            email = email,
                            passwd = passwd,
                            role = "T",
                            dateRegister = LocalDateTime.now().toString()
                        )
                        var trainer = Trainer(biography = biography, price = price.toDouble())
                        viewModel.addUserTrainer(user, trainer)

                        emailError = false
                        passwdError = false
                        nameError = false
                        priceError = false
                        bioError = false
                        focusRequester.requestFocus() //Devuelve el foco a la caja de texto nombre.
                    } else {
                        emailError = email.isBlank()
                        passwdError = passwd.isBlank()
                        nameError = name.isBlank()
                        priceError = price.isBlank() || price.toDoubleOrNull() == null
                        bioError = biography.isBlank()

                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = email.isNotBlank() && passwd.isNotBlank() && name.isNotBlank() && price.isNotBlank() &&
                        price.toDoubleOrNull() != null && biography.isNotBlank()
            ) {
                Text("Register")
            }
        }
    }

}