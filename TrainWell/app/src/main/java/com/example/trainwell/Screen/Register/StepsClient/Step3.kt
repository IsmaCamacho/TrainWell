package com.example.trainwell.Screen.Register.StepsClient

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.trainwell.R
import com.example.trainwell.Routes
import com.example.trainwell.Screen.Register.Auxiliar.ProgressBarViewModel
import com.example.trainwell.Screen.Register.Auxiliar.RegistrationLayout

//SCREEN PARA LA ALTURA
@Composable
fun ClientScreen3(navController: NavHostController, pbvm: ProgressBarViewModel) {
    var altura by remember { mutableStateOf("") }
    var alturaError by remember { mutableStateOf(false) }

    var isCm by remember { mutableStateOf(true) }

    Box(Modifier.fillMaxSize()) {
        RegistrationLayout(pbvm = pbvm, step = 3, {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 50.dp)
                    .padding(start = 20.dp)
                    .padding(end = 20.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "How tall are you?", color = Color.White, fontSize = 30.sp)
            }
            Column(
                modifier = Modifier.align(Alignment.Center), // Centra el bloque en el box
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    //Campo para la ALTURA
                    OutlinedTextField(
                        value = altura,
                        onValueChange = {
                            if (it.length <= 3) //para que no deje escribir mas de 2 cifras
                                altura = it
                            alturaError = it.isBlank()
                        },
                        isError = alturaError,
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
                // SELECTOR DE UNIDADES (cm / pulgadas) BUSCADO (OPCION DE QUITAR)
                Spacer(modifier = Modifier.height(14.dp))
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(25.dp))
                        .background(colorResource(id = R.color.greenCard)) // Fondo oscuro/gris
                        .padding(4.dp)
                ) {
                    UnitOption(
                        text = "cm",
                        isSelected = isCm,
                        onClick = { isCm = true }
                    )
                    UnitOption(
                        text = "pies y pulg.",
                        isSelected = !isCm,
                        onClick = { isCm = false }
                    )
                }
                if (alturaError) {
                    Text(
                        text = "Height cannot be empty",
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
            //Botón para CONTINUAR
            Button(
                onClick = {
                    if (altura.isNotEmpty()) {
                        navController.navigate(Routes.REGISFOUR)
                    } else {
                        Log.e("ismael", "La altura está vacía")
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(16.dp),
                enabled = if (altura.isEmpty()) false else true,
                colors = ButtonDefaults.buttonColors(
                    // Color cuando el botón está habilitado
                    containerColor = colorResource(id = R.color.greenBT), //color de fondo del boton
                    contentColor = Color.Black,
                    // Color cuando el botón NO está habilitado
                    disabledContainerColor = colorResource(id = R.color.greenCard), //color de fondo del boton
                    disabledContentColor = Color.Black
                )
            ) {
                Text("Continue")
            }
        })
    }
}

@Composable
fun UnitOption(text: String, isSelected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(if (isSelected) Color.White else Color.Transparent)
            .clickable { onClick() }
            .padding(vertical = 8.dp, horizontal = 20.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = if (isSelected) Color.Black else Color.Gray,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
        )
    }
}