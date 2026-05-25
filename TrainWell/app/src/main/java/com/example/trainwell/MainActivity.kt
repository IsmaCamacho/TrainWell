package com.example.trainwell

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.trainwell.Screen.Dashboard.ClientDashboardScreen

import com.example.trainwell.Screen.Dashboard.TrainerScreenDashboard
import com.example.trainwell.Screen.Login.Login
import com.example.trainwell.Screen.Register.Auxiliar.ProgressBarViewModel
import com.example.trainwell.Screen.Register.Register
import com.example.trainwell.Screen.Register.StepsClient.ClientScreen1
import com.example.trainwell.Screen.Register.StepsClient.ClientScreen2
import com.example.trainwell.Screen.Register.StepsClient.ClientScreen3
import com.example.trainwell.Screen.Register.StepsClient.ClientScreen4
import com.example.trainwell.Screen.Register.StepsClient.ClientScreen5
import com.example.trainwell.Screen.Register.StepsClient.ClientScreen6
import com.example.trainwell.Screen.Register.StepsClient.ClientScreen7
import com.example.trainwell.Screen.Register.StepsClient.ClientScreen8
import com.example.trainwell.Screen.Register.StepsClient.ClientScreen9
import com.example.trainwell.Screen.Register.StepsClient.ClientScreenFinal
import com.example.trainwell.Screen.Register.StepsTrainer.TrainerScreen1
import com.example.trainwell.Screen.Register.StepsTrainer.TrainerScreen2
import com.example.trainwell.Screen.Register.StepsTrainer.TrainerScreen3
import com.example.trainwell.ViewModel.Login.LoginViewModel
import com.example.trainwell.ViewModel.NavigationViewModel
import com.example.trainwell.ViewModel.Register.RegisterViewModel
import com.example.trainwell.ui.theme.TrainWellTheme

class MainActivity : ComponentActivity() {

//    val auth = Firebase.auth
    val lvm = LoginViewModel()
    val rvm = RegisterViewModel()
    val navm = NavigationViewModel()
    val pbvm = ProgressBarViewModel()

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
                        ClientScreen1(navController,rvm, navm, pbvm)
                    }
                    composable(Routes.REGISTWO) {
                        ClientScreen2(navController, pbvm)
                    }
                    composable(Routes.REGISTHREE) {
                        ClientScreen3(navController, pbvm, rvm)
                    }
                    composable(Routes.REGISFOUR) {
                        ClientScreen4(navController, pbvm, rvm)
                    }
                    composable(Routes.REGISFIVE) {
                        ClientScreen5(navController, pbvm, rvm)
                    }
                    composable(Routes.REGISSIX) {
                        ClientScreen6(navController)
                    }
                    composable(Routes.REGISSEVEN) {
                        ClientScreen7(navController)
                    }
                    composable(Routes.REGISEIGHT) {
                        ClientScreen8(navController)
                    }
                    composable(Routes.REGISNINE) {
                        ClientScreen9(navController)
                    }
                    composable(Routes.REGISFINAL) {
                        ClientScreenFinal(navController, rvm)
                    }
                    composable(Routes.DASHCLIENT) {
                        ClientDashboardScreen(navController)
                    }
                    composable(Routes.trainerRegister) {
                        TrainerScreen1(navController, rvm)
                    }
                    composable(Routes.trainerRegisterStep2) {
                        TrainerScreen2(navController, rvm)
                    }
                    composable(Routes.trainerRegisterStep3) {
                        TrainerScreen3(navController, rvm)
                    }
                    composable(Routes.DASHTRAINER) {
                        TrainerScreenDashboard(navController, rvm)
                    }
                }
            }
        }
    }
}