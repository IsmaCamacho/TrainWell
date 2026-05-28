package com.example.trainwell.Screen.Register.StepsClient

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalTextStyle
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.trainwell.R
import com.example.trainwell.Routes
import com.example.trainwell.Screen.Register.Auxiliar.ProgressBarViewModel
import com.example.trainwell.Screen.Register.Auxiliar.RegistrationLayout

//SCREEN PARA LA EDAD
@Composable
fun ClientScreen2(navController: NavHostController, pbvm: ProgressBarViewModel) {
    var edad by remember { mutableStateOf("") }
    var edadError by remember { mutableStateOf(false) }
    Box(Modifier.fillMaxSize()) {
        RegistrationLayout(pbvm = pbvm, step = 2, {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 50.dp)
                    .padding(start = 20.dp)
                    .padding(end = 20.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "What old are you?", color = Color.White, fontSize = 30.sp)
            }
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    //Campo para la EDAD
                    OutlinedTextField(
                        value = edad,
                        onValueChange = {
                            if (it.length <= 2)
                                edad = it
                            edadError = it.isBlank() || edad.toIntOrNull() == null
                        },
                        isError = edadError,
                        singleLine = true,
                        textStyle = LocalTextStyle.current.copy(
                            fontSize = 24.sp,
                            textAlign = TextAlign.Center,
                            color = Color.White
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.width(120.dp)
                    )
                }
                if (edadError) {
                    Text(
                        text = "Age cannot be empty",
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "years", color = colorResource(id=R.color.greenBT), fontSize = 20.sp)
            }
            //Botón para CONTINUAR
            Button(
                onClick = {
                    if (edad.isNotEmpty()) {
                        navController.navigate(Routes.REGISTHREE)
                    } else {
                        Log.e("ismael", "La edad está vacía")
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(16.dp),
                enabled = edad.isNotEmpty() && edad.toIntOrNull() != null,
                colors = ButtonDefaults.buttonColors(
                    // Color cuando el botón está habilitado
                    containerColor = colorResource(id = R.color.greenBT),
                    contentColor = Color.Black,
                    // Color cuando el botón NO está habilitado
                    disabledContainerColor = colorResource(id = R.color.greenCard),
                    disabledContentColor = Color.Black
                )
            ) {
                Text("Continue")
            }
        })
    }
}