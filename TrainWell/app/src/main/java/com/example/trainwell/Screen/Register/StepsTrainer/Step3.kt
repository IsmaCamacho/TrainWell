package com.example.trainwell.Screen.Register.StepsTrainer

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.trainwell.Routes
import com.example.trainwell.ViewModel.Register.RegisterViewModel

@SuppressLint("UnrememberedMutableState")
@Composable
fun TrainerScreen3(navController: NavHostController, rvm: RegisterViewModel) {
    val mainGreen = Color(0xFF22E67B)
    val lightGreenCard = Color(0xFFE8F5E9).copy(alpha = 0.5f)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp)
    ) {
        // Cabecera
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier.clickable { navController.popBackStack() }
            )
            Text(
                text = "Rates and availability",
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Stepper (Paso 3 de 3)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(Color.LightGray))
            Spacer(modifier = Modifier.width(4.dp))
            Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(Color.LightGray))
            Spacer(modifier = Modifier.width(4.dp))
            Box(modifier = Modifier.size(width = 30.dp, height = 8.dp).clip(CircleShape).background(mainGreen))
        }

        Spacer(modifier = Modifier.height(30.dp))

        Text(text = "Latest details", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Text(
            text = "Set your prices and schedules so students know when they can train with you.",
            color = Color.Gray,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(32.dp))

        //Precio mensual
        Text(text = "Monthly price (€)", color = mainGreen, fontWeight = FontWeight.Bold, fontSize = 14.sp)
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = rvm.precioMensual,
            onValueChange = { if (it.all { char -> char.isDigit() }) rvm.precioMensual = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Ej: 25") },
            shape = RoundedCornerShape(20.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            leadingIcon = { Icon(Icons.Default.Payments, contentDescription = null, tint = mainGreen) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = mainGreen,
                unfocusedBorderColor = Color(0xFFE0E0E0)
            )
        )

        Spacer(modifier = Modifier.height(24.dp))
        Text("Availability", fontSize = 20.sp, fontWeight = FontWeight.Bold)

        // Switch "Aceptar nuevos clientes"
        Card(
            Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = BorderStroke(1.dp, Color(0xFFE0E0E0))
        ) {
            Row(Modifier.padding(15.dp), verticalAlignment = Alignment.CenterVertically) {
                Column(Modifier.weight(1f)) {
                    Text("Accept new clients", fontWeight = FontWeight.Bold)
                    Text("Your profile will be visible in the search engine", color = mainGreen, fontSize = 11.sp)
                }
                Switch(
                    checked = rvm.aceptarNuevosClientes,
                    onCheckedChange = { rvm.aceptarNuevosClientes = it },
                    colors = SwitchDefaults.colors(checkedThumbColor = Color.White, checkedTrackColor = mainGreen)
                )
            }
        }

        Spacer(Modifier.height(20.dp))
        Text("Maximum customer capacity", fontSize = 14.sp, fontWeight = FontWeight.Bold)
        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = rvm.cupoMaximo,
                onValueChange = { rvm.cupoMaximo = it },
                modifier = Modifier.width(80.dp),
                shape = RoundedCornerShape(25.dp),
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center, fontWeight = FontWeight.Bold)
            )
            Text(" We recommend starting with a manageable number.", color = mainGreen, fontSize = 11.sp, modifier = Modifier.padding(start = 10.dp))
        }

        Spacer(Modifier.height(20.dp))
        Text("Favorite training days", fontSize = 14.sp, fontWeight = FontWeight.Bold)

        // Selector de días (Chips)
        val diasSemana = listOf("Mon", "Tue", "Wed", "Thu", "Fri","Sat","Sun")
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            diasSemana.forEach { dia ->
                DayChip(dia, rvm.diasSeleccionados.contains(dia)) {
                    if (rvm.diasSeleccionados.contains(dia)) {
                        rvm.diasSeleccionados.remove(dia)
                    } else {
                        rvm.diasSeleccionados.add(dia)
                    }
                }
            }
        }

        // Aviso de comisión (Cuadro informativo verde)
        Spacer(Modifier.height(25.dp))
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = lightGreenCard)
        ) {
            Row(Modifier.padding(15.dp), verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Info, null, tint = mainGreen)
                Text(
                    " TRAINWELL charges a 10% platform management fee on each payment received. By completing registration, you agree to our terms of service.",
                    fontSize = 10.sp, color = Color.DarkGray, lineHeight = 14.sp
                )
            }
        }

        Spacer(Modifier.height(30.dp))

        // Botón FINALIZAR REGISTRO
        Button(
            onClick = {
                rvm.finalizarRegistroEntrenador()
                navController.navigate(Routes.DASHTRAINER) {
                    popUpTo(Routes.trainerRegisterStep3) { inclusive = true } //el inclusive borra toda la memoria anterior
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            enabled = rvm.precioMensual.isNotEmpty(),
            colors = ButtonDefaults.buttonColors(containerColor = mainGreen),
            shape = RoundedCornerShape(25.dp)
        ) {
            Text("Finish and create profile", color = Color.Black, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun DayChip(dia: String, isSelected: Boolean, onClick: () -> Unit) {
    val mainGreen = Color(0xFF22E67B)
    Surface(
        modifier = Modifier.clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        color = if (isSelected) mainGreen else Color.White,
        border = if (isSelected) null else BorderStroke(1.dp, Color.LightGray)
    ) {
        Text(
            dia,
            Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            color = if (isSelected) Color.White else Color.Gray,
            fontWeight = FontWeight.Bold
        )
    }
}