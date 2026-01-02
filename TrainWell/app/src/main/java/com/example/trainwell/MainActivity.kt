package com.example.trainwell

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.trainwell.Screen.Login.login
import com.example.trainwell.ViewModel.Login.LoginViewModel
import com.example.trainwell.ui.theme.TrainWellTheme

class MainActivity : ComponentActivity() {

//    val auth = Firebase.auth
    val lvm = LoginViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TrainWellTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Routes.login){
                    composable(Routes.login){
                        login(navController, lvm)
                    }
                }
            }
        }
    }
}