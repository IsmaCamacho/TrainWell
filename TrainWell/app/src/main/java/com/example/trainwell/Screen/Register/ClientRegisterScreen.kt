package com.example.trainwell.Screen.Register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.trainwell.Data
import com.example.trainwell.Model.Login.Login
import com.example.trainwell.Model.Register.Customer
import com.example.trainwell.Model.Register.User
import com.example.trainwell.Routes
import com.example.trainwell.ViewModel.Register.RegisterViewModel
import com.google.type.DateTime
import java.time.LocalDateTime

@Composable
fun ClientForm(
    navController: NavHostController,
    viewModel: RegisterViewModel
) {
    val context = LocalContext.current
    var name by remember { mutableStateOf("") }
    var goal by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var passwd by remember { mutableStateOf("") }
    var goalError by remember { mutableStateOf(false) }
    var weightError by remember { mutableStateOf(false) }
    var heightError by remember { mutableStateOf(false) }
    var nameError by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
    var passwdError by remember { mutableStateOf(false) }
    // val existe by viewModel.usuarioExiste.observeAsState()
    val focusRequester = remember { FocusRequester() }

//    LaunchedEffect(existe) {
//        if (existe == true) {
//            navController.navigate(Routes.register)
//        }
//    }

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
            label = {Text("Name")},
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
            value = goal,
            onValueChange = {
                goal = it
                goalError = it.isBlank()
            },
            label = {Text("Goal")},
            isError = goalError,
            modifier = Modifier
                .fillMaxWidth()


        )
        if (goalError) {
            Text(
                text = "goal cannot be empty",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }
        // Campo para peso
        OutlinedTextField(
            value = weight,
            onValueChange = {
                weight = it
                weightError = it.isBlank() || it.toDoubleOrNull() == null
            },
            label = {Text("Weight")},
            isError = weightError,
            modifier = Modifier
                .fillMaxWidth()


            )
        if (weightError) {
            val errorMessage = when {
                weight.isBlank() -> "Weight cannot be empty"
                weight.toDoubleOrNull() == null -> "Please enter a valid number (e.g. 75.5)"
                else -> ""
            }
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }
        // Campo para altura
        OutlinedTextField(
            value = height,
            onValueChange = {
                height = it
                heightError = it.isBlank() || it.toIntOrNull() == null
            },
            label = {Text("Height")},
            isError = heightError,
            modifier = Modifier
                .fillMaxWidth()


            )
        if (heightError) {
            val errorMessage = when {
                height.isBlank() -> "Height cannot be empty"
                height.toIntOrNull() == null -> "Please enter a valid number (e.g. 75)"
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
                if (email.isNotBlank() && passwd.isNotBlank() && name.isNotBlank() && weight.isNotBlank() &&
                    height.isNotBlank() && goal.isNotBlank() && weight.toDoubleOrNull() != null && height.toIntOrNull() != null) {
                    // Añadimos el usuario general con su cliente
                    var user = User(name = name, email = email, passwd = passwd, role = "C", dateRegister = LocalDateTime.now().toString())
                    var customer = Customer(goal = goal, weight = weight.toDouble(), height = height.toInt())
                    viewModel.addUser(user,customer)

                    emailError = false
                    passwdError = false
                    nameError = false
                    weightError = false
                    heightError = false
                    goalError = false
                    focusRequester.requestFocus() //Devuelve el foco a la caja de texto nombre.
                } else {
                    emailError = email.isBlank()
                    passwdError = passwd.isBlank()
                    nameError = name.isBlank()
                    weightError = weight.isBlank() || weight.toDoubleOrNull() == null
                    heightError = height.isBlank() || height.toIntOrNull() == null
                    goalError = goal.isBlank()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = email.isNotBlank() && passwd.isNotBlank() && name.isNotBlank() && weight.isNotBlank() &&
                    height.isNotBlank() && goal.isNotBlank() && weight.toDoubleOrNull() != null && height.toIntOrNull() != null
        ) {
            Text("Register")
        }
        //si no existe, texto de que se ha equivocado en el login
//        if (existe==false) {
//            Text(
//                text = "Email o contraseña incorrectos",
//                color = MaterialTheme.colorScheme.error,
//                modifier = Modifier.padding(top = 12.dp)
//            )
//        }
    }
}