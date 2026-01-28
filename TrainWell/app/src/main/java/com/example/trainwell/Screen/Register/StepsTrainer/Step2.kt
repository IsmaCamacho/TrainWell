package com.example.trainwell.Screen.Register.StepsTrainer

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.trainwell.Routes
import com.example.trainwell.ViewModel.Register.RegisterViewModel

@Composable
fun TrainerScreen2(navController: NavHostController, rvm: RegisterViewModel) {
    val mainGreen = Color(0xFF22E67B)
    val chips = listOf("Bodybuilding", "Yoga", "Sports nutrition", "CrossFit", "Running", "Pilates", "Rehabilitation")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FA)) // Fondo casi blanco/grisáceo muy claro
            .padding(20.dp)
    ) {
        // Cabecera sencilla
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
                text = "Trainer Registration",
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Stepper (Paso 2 de 4)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(Color.LightGray))
            Spacer(modifier = Modifier.width(4.dp))
            Box(modifier = Modifier.size(width = 30.dp, height = 8.dp).clip(CircleShape).background(mainGreen))
            Spacer(modifier = Modifier.width(4.dp))
            Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(Color.LightGray))
        }

        Spacer(modifier = Modifier.height(30.dp))

        Text(text = "Define your profile", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Text(
            text = "Select your areas of expertise and tell us how you work to connect with ideal clients.",
            color = Color.Gray,
            fontSize = 14.sp,
            lineHeight = 20.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "Areas of specialization", color = mainGreen, fontWeight = FontWeight.Bold, fontSize = 14.sp)

        // Contenedor de Chips (FlowRow para que salten de línea solos)
        // Nota: FlowRow requiere la dependencia de layouts o usar una alternativa manual
        Spacer(modifier = Modifier.height(8.dp))

        // Implementación manual simplificada de los chips
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            // Fila 1
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                SpecialtyChip("Bodybuilding", rvm)
                SpecialtyChip("Yoga", rvm)
            }
            // Fila 2
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                SpecialtyChip("Sports nutrition", rvm)
                SpecialtyChip("CrossFit", rvm)
            }
            // Fila 3
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                SpecialtyChip("Running", rvm)
                SpecialtyChip("Pilates", rvm)
                SpecialtyChip("Rehabilitation", rvm)
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(text = "Experience and biography", color = mainGreen, fontWeight = FontWeight.Bold, fontSize = 14.sp)
        Spacer(modifier = Modifier.height(8.dp))

        // Campo de texto grande (Bio)
        OutlinedTextField(
            value = rvm.biografia,
            onValueChange = { if (it.length <= 500) rvm.biografia = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            placeholder = { Text("Describe your approach, certifications, or years of experience...", color = Color.LightGray) },
            shape = RoundedCornerShape(20.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color(0xFFE0E0E0),
                focusedBorderColor = mainGreen,
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White
            )
        )
        Text(
            text = "${rvm.biografia.length} / 500",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.End,
            fontSize = 12.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.weight(1f))

        // Botón Continuar
        Button(
            onClick = { navController.navigate(Routes.trainerRegisterStep3) },
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            colors = ButtonDefaults.buttonColors(containerColor = mainGreen),
            shape = RoundedCornerShape(25.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Continue", color = Color.Black, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(8.dp))
                Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null, tint = Color.Black)
            }
        }
    }
}

@Composable
fun SpecialtyChip(text: String, rvm: RegisterViewModel) {
    val isSelected = rvm.especializacionesSeleccionadas.contains(text)
    val mainGreen = Color(0xFF22E67B)

    Surface(
        modifier = Modifier.clickable { rvm.toggleEspecializacion(text) },
        shape = RoundedCornerShape(20.dp),
        color = if (isSelected) mainGreen else Color.White,
        border = if (isSelected) null else BorderStroke(1.dp, Color.LightGray)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (isSelected) {
                Icon(Icons.Default.Check, contentDescription = null, modifier = Modifier.size(16.dp), tint = Color.Black)
                Spacer(modifier = Modifier.width(4.dp))
            }
            Text(text = text, color = if (isSelected) Color.Black else Color.Gray, fontSize = 14.sp)
        }
    }
}