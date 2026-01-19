package com.example.trainwell.Screen.Register.StepsClient

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
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
import com.example.trainwell.ViewModel.NavigationViewModel
import com.example.trainwell.ViewModel.Register.RegisterViewModel
import java.time.LocalDateTime

@Composable
fun ClientScreen1(
    navController: NavHostController,
    viewModel: RegisterViewModel,
    navm: NavigationViewModel
) {
    var sex by remember { mutableStateOf("") }
    //PARA LA BARRA DE PROGRESO
    var globalProgress by remember { mutableFloatStateOf(0.2f) } // Empezamos en 20% (1/5)

    // val existe by viewModel.usuarioExiste.observeAsState()

//    LaunchedEffect(existe) {
//        if (existe == true) {
//            navController.navigate(Routes.register)
//        }
//    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        colorResource(id = R.color.greenBG2), //verde mas clarito
                        colorResource(id = R.color.greenBG) // verde mas oscuro
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 100.dp)
                .padding(start = 20.dp)
                .padding(end = 20.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Your fitness journey", color = Color.White, fontSize = 30.sp)
            Text(text = "STARTS HERE", color = colorResource(id=R.color.greenBT), fontSize = 30.sp)
            Spacer(modifier = Modifier.padding(10.dp))
            Text(text = "Let's set up your profile and start tracking progress", color = colorResource(id = R.color.greyTXT), fontSize = 15.sp)

            MenCard(isSelected = sex == "M") { sex = "M"}
            WomenCard(isSelected = sex == "W") { sex = "W"}
            OtherCard(isSelected = sex == "O") { sex = "O"}

            //Botón para registrar
            Button(
                onClick = {
                    if (sex.isNotEmpty()) {
                        navController.navigate(Routes.REGISTWO)
                    } else {
                        Log.e("ismael", "No has pulsado en ninguna opcion")
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = if (sex.isNotEmpty()) false else true,
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
    }
}

@Composable
fun MenCard(isSelected:Boolean, onClick: () -> Unit) {
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
                    Log.e("ismael", "Has pulsado en la men card")
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
            Text(text = "Men", fontSize = 20.sp, color = Color.White, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(id = R.drawable.ic_men),
                contentDescription = null,
                modifier = Modifier.size(100.dp)
            )
        }
    }
}

@Composable
fun WomenCard(isSelected:Boolean, onClick: () -> Unit) {
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
                    Log.e("ismael", "Has pulsado en la women card")
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
            Text(text = "Women", fontSize = 20.sp, color = Color.White, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(id = R.drawable.ic_women),
                contentDescription = null,
                modifier = Modifier.size(100.dp)
            )
        }
    }
}

@Composable
fun OtherCard(isSelected:Boolean, onClick: () -> Unit) {
    var context = LocalContext.current
    Card(
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.greenCard)
        ),
        border = if (isSelected) BorderStroke(2.dp, colorResource(id = R.color.greenBT)) else null,
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .combinedClickable(
                onClick = {
                    onClick()
                    Log.e("ismael", "Has pulsado en la other card")
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
            Text(text = "Other", fontSize = 20.sp, color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun ClientScreen2(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 50.dp)
            .padding(start = 20.dp)
            .padding(end = 20.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Aqui elegimos el género")
        Button(onClick = {
            navController.navigate(Routes.REGISTHREE)
        }) { }
    }
}

@Composable
fun ClientScreen3(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 50.dp)
            .padding(start = 20.dp)
            .padding(end = 20.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Aqui elegimos la edad")
        Button(onClick = {
            navController.navigate(Routes.REGISFOUR)
        }) { }
    }
}

@Composable
fun ClientScreen4(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 50.dp)
            .padding(start = 20.dp)
            .padding(end = 20.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Aqui elegimos la altura")
        Button(onClick = {
            navController.navigate(Routes.REGISFIVE)
        }) { }
    }
}

@Composable
fun ClientScreen5(navController: NavHostController) {
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
            navController.navigate(Routes.REGISTWO)
        }) { }
    }
}

@Composable
fun ClientScreen6(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 50.dp)
            .padding(start = 20.dp)
            .padding(end = 20.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Aqui elegimos el objetivo")
    }

}

