package com.example.trainwell

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.trainwell.Screen.Login.Login
import com.example.trainwell.Screen.Register.StepsClient.ClientForm
import com.example.trainwell.Screen.Register.Register
import com.example.trainwell.Screen.Register.StepsClient.Screen2
import com.example.trainwell.Screen.Register.StepsClient.Screen3
import com.example.trainwell.Screen.Register.StepsClient.Screen4
import com.example.trainwell.Screen.Register.StepsClient.Screen5
import com.example.trainwell.Screen.Register.StepsClient.Screen6
import com.example.trainwell.Screen.Register.TrainerForm
import com.example.trainwell.ViewModel.Login.LoginViewModel
import com.example.trainwell.ViewModel.NavigationViewModel
import com.example.trainwell.ViewModel.Register.RegisterViewModel
import com.example.trainwell.ui.theme.TrainWellTheme

class MainActivity : ComponentActivity() {

//    val auth = Firebase.auth
    val lvm = LoginViewModel()
    val rvm = RegisterViewModel()
    val navm = NavigationViewModel()

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
                        ClientForm(navController,rvm, navm)
                    }
                    composable(Routes.trainerRegister) {
                        TrainerForm(navController,rvm)
                    }
                    composable(Routes.REGISTWO) {
                        Screen2(navController)
                    }
                    composable(Routes.REGISTHREE) {
                        Screen3(navController)
                    }
                    composable(Routes.REGISFOUR) {
                        Screen4(navController)
                    }
                    composable(Routes.REGISFOUR) {
                        Screen5(navController)
                    }
                    composable(Routes.REGISFOUR) {
                        Screen6(navController)
                    }
                }
            }
        }
    }
}