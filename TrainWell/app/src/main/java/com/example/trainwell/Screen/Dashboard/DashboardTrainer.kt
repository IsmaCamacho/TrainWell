package com.example.trainwell.Screen.Dashboard

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.trainwell.ViewModel.Register.RegisterViewModel

@Composable
fun TrainerScreenDashboard(navController: NavHostController, rvm: RegisterViewModel) {
    Text(text = "Dashboard del entrenador, que ha sido añadido con exito a la BBDD")
}