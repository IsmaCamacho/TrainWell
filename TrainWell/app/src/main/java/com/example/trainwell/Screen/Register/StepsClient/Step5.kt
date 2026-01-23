package com.example.trainwell.Screen.Register.StepsClient

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.trainwell.R
import com.example.trainwell.Routes
import com.example.trainwell.Screen.Register.Auxiliar.ProgressBarViewModel
import com.example.trainwell.Screen.Register.Auxiliar.RegistrationLayout

@Composable
fun ClientScreen5(
    navController: NavHostController,
    pbvm: ProgressBarViewModel
) {
    var goal by remember { mutableStateOf("") }
    //PARA LA BARRA DE PROGRESO

    RegistrationLayout(pbvm = pbvm, step = 5, {
        Box(modifier = Modifier.fillMaxSize()){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 100.dp)
                    .padding(start = 20.dp)
                    .padding(end = 20.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(text = "How much do you weigh?", color = Color.White, fontSize = 30.sp)
                Spacer(modifier = Modifier.height(30.dp))
                GainMuscleCard(isSelected = goal == "W") { goal = "W"}
                LoseWeightCard(isSelected = goal == "L") { goal = "L"}
            }
            //Botón para CONTINUAR
            Button(
                onClick = {
                    if (goal.isNotEmpty()) {
                        navController.navigate(Routes.REGISFINAL)
                    } else {
                        Log.e("ismael", "No has pulsado en ninguna opcion")
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(16.dp),
                enabled = if (goal.isEmpty()) false else true,
                colors = ButtonDefaults.buttonColors(
                    // Color cuando el botón está habilitado
                    containerColor = colorResource(id = R.color.greenBT), //color de fondo del boton
                    contentColor = Color.Black,
                    // Color cuando el botón NO está habilitado
                    disabledContainerColor = colorResource(id = R.color.greenCard), //color de fondo del boton
                    disabledContentColor = Color.Black)
            ) {
                Text("Continue")
            }
        }
    })
}

@Composable
fun GainMuscleCard(isSelected:Boolean, onClick: () -> Unit) {
    var context = LocalContext.current
    Card(
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.greenCard)
        ),
        border = if (isSelected) BorderStroke(2.dp, colorResource(id = R.color.greenBT)) else null,
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .combinedClickable(
                onClick = {
                    onClick()
                    Log.e("ismael", "Has pulsado en la gain muscle card")
                }
            )
            .padding(5.dp)
    )
    {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Gain Muscle", fontSize = 20.sp, color = Color.White, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(id = R.drawable.ic_gainmuscle),
                contentDescription = null,
                modifier = Modifier.size(100.dp)
            )
        }
    }
}

@Composable
fun LoseWeightCard(isSelected:Boolean, onClick: () -> Unit) {
    var context = LocalContext.current
    Card(
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.greenCard)
        ),
        border = if (isSelected) BorderStroke(2.dp, colorResource(id = R.color.greenBT)) else null,
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .combinedClickable(
                onClick = {
                    onClick()
                    Log.e("ismael", "Has pulsado en la lose weight card")
                }
            )
            .padding(5.dp)
    )
    {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Lose Weight", fontSize = 20.sp, color = Color.White, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(id = R.drawable.ic_loseweight),
                contentDescription = null,
                modifier = Modifier.size(100.dp)
            )
        }
    }
}