package com.example.trainwell.Screen.Register.StepsClient

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.trainwell.Routes
import com.example.trainwell.Screen.Register.Auxiliar.ProgressBarViewModel
import com.example.trainwell.Screen.Register.Auxiliar.RegistrationLayout

//SCREEN PARA EL PESO
@Composable
fun ClientScreen4(navController: NavHostController, pbvm: ProgressBarViewModel) {
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
            Text(text = "Aqui elegimos el peso")
            Button(onClick = {
                navController.navigate(Routes.REGISFIVE)
            }) { }
        }
    })
}