package com.example.trainwell

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.trainwell.Screen.Login.Login
import com.example.trainwell.Screen.Register.ClientForm
import com.example.trainwell.Screen.Register.Register
import com.example.trainwell.Screen.Register.TrainerForm
import com.example.trainwell.ViewModel.Login.LoginViewModel
import com.example.trainwell.ViewModel.Register.RegisterViewModel
import com.example.trainwell.ui.theme.TrainWellTheme

class MainActivity : ComponentActivity() {

//    val auth = Firebase.auth
    val lvm = LoginViewModel()
    val rvm = RegisterViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TrainWellTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Routes.register){
                    composable(Routes.login){
                        Login(navController, lvm)
                    }
                    composable(Routes.register){
                        Register(navController)
                    }
                    composable(Routes.clientRegister) {
                        ClientForm(navController,rvm)
                    }
                    composable(Routes.trainerRegister) {
                        TrainerForm(navController)
                    }
                }
            }
        }
    }
}