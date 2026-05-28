package com.example.trainwell.Screen.Register.StepsTrainer

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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.trainwell.Routes
import com.example.trainwell.ViewModel.Register.RegisterViewModel

@Composable
fun TrainerScreen1(navController: NavHostController, rvm: RegisterViewModel) {
    val lightGreenBG = Color(0xFFE8F5E9)
    val mainGreen = Color(0xFF22E67B)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp))
                .background(lightGreenBG)
                .padding(top = 40.dp, bottom = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
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

            // Indicador de pasos (Paso 1 de 3)
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Box(modifier = Modifier.size(width = 30.dp, height = 6.dp).clip(CircleShape).background(mainGreen))
                Box(modifier = Modifier.size(width = 8.dp, height = 6.dp).clip(CircleShape).background(Color.LightGray))
                Box(modifier = Modifier.size(width = 8.dp, height = 6.dp).clip(CircleShape).background(Color.LightGray))
            }

            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Personal Data", fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)
            Text(
                text = "Let's start with your professional profile.\nThis information will be visible to customers.",
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
                color = Color.Gray,
                modifier = Modifier.padding(horizontal = 40.dp)
            )
        }

        // Cuerpo del formulario
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Selector de Foto de Perfil
            Box(contentAlignment = Alignment.BottomEnd) {
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .background(lightGreenBG),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(imageVector = Icons.Default.Person, contentDescription = null, modifier = Modifier.size(60.dp), tint = Color.Gray)
                }
                Box(
                    modifier = Modifier
                        .size(35.dp)
                        .clip(CircleShape)
                        .background(mainGreen)
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(imageVector = Icons.Default.PhotoCamera, contentDescription = null, tint = Color.White)
                }
            }
            Text(text = "PROFILE PHOTO", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = mainGreen, modifier = Modifier.padding(top = 8.dp))

            Spacer(modifier = Modifier.height(30.dp))

            TrainerInputField(label = "Username", value = rvm.username, icon = Icons.Default.Badge) { rvm.username = it }
            Spacer(modifier = Modifier.height(16.dp))
            TrainerInputField(label = "Email", value = rvm.email, icon = Icons.Default.Email) { rvm.email = it }
            Spacer(modifier = Modifier.height(16.dp))
            TrainerInputField(label = "Password", value = rvm.password, icon = Icons.Default.Lock, isPassword = true) { rvm.password = it }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { navController.navigate(Routes.trainerRegisterStep2) },
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
}

@Composable
fun TrainerInputField(
    label: String,
    value: String,
    icon: ImageVector,
    isPassword: Boolean = false,
    onValueChange: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = label, fontWeight = FontWeight.Bold, fontSize = 14.sp, modifier = Modifier.padding(bottom = 4.dp))
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            leadingIcon = { Icon(imageVector = icon, contentDescription = null, tint = Color.Gray) },
            trailingIcon = { if (isPassword) Icon(imageVector = Icons.Default.Visibility, contentDescription = null) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.LightGray,
                unfocusedBorderColor = Color(0xFFF0F0F0),
                focusedContainerColor = Color(0xFFFAFAFA),
                unfocusedContainerColor = Color(0xFFFAFAFA)
            ),
            singleLine = true
        )
    }
}