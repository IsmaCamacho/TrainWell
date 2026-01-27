package com.example.trainwell.Screen.Dashboard

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.trainwell.ViewModel.Register.RegisterViewModel

@Composable
fun ClientScreenDashboard(navController: NavHostController, rvm: RegisterViewModel) {
    Text(text = "Dashboard del cliente, que ha sido añadido con exito a la BBDD")
}