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
import com.example.trainwell.ViewModel.Register.RegisterViewModel

//SCREEN PARA EL PESO
@Composable
fun ClientScreen4(navController: NavHostController, pbvm: ProgressBarViewModel, rvm: RegisterViewModel) {
    var pesoError by remember { mutableStateOf(false) }

    var isKg by remember { mutableStateOf(true) }

    Box(Modifier.fillMaxSize()) {
        RegistrationLayout(pbvm = pbvm, step = 4, {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 50.dp)
                    .padding(start = 20.dp)
                    .padding(end = 20.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "How much do you weigh?", color = Color.White, fontSize = 30.sp)
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
                    //Campo para la ALTURA
                    OutlinedTextField(
                        value = rvm.weight,
                        onValueChange = {
                            if (it.length <= 3)
                                rvm.weight = it
                            pesoError = it.isBlank() || it.toDoubleOrNull() == null
                        },
                        isError = pesoError,
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
                // SELECTOR DE UNIDADES (kg / lbl)
                Spacer(modifier = Modifier.height(14.dp))
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(25.dp))
                        .background(colorResource(id = R.color.greenCard))
                        .padding(4.dp)
                ) {
                    UnitOptionPeso(
                        text = "kg",
                        isSelected = isKg,
                        onClick = { isKg = true }
                    )
                    UnitOptionPeso(
                        text = "lb",
                        isSelected = !isKg,
                        onClick = { isKg = false }
                    )
                }
                if (pesoError) {
                    Text(
                        text = "Weight cannot be empty",
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
            //Botón para CONTINUAR
            Button(
                onClick = {
                    if (rvm.weight.isNotEmpty()) {
                        navController.navigate(Routes.REGISFIVE)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(16.dp),
                enabled = rvm.weight.isNotEmpty() && rvm.weight.toDoubleOrNull() != null,
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

@Composable
fun UnitOptionPeso(text: String, isSelected: Boolean, onClick: () -> Unit) {
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