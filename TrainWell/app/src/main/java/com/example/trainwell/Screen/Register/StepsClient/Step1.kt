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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.trainwell.R
import com.example.trainwell.Routes
import com.example.trainwell.Screen.Register.Auxiliar.ProgressBarViewModel
import com.example.trainwell.Screen.Register.Auxiliar.RegistrationLayout
import com.example.trainwell.ViewModel.NavigationViewModel
import com.example.trainwell.ViewModel.Register.RegisterViewModel

@Composable
fun ClientScreen1(
    navController: NavHostController,
    rvm: RegisterViewModel,
    navm: NavigationViewModel,
    pbvm: ProgressBarViewModel
) {

    RegistrationLayout(pbvm = pbvm, step = 1, {
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

                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Your fitness journey", color = Color.White, fontSize = 30.sp)
                Text(text = "STARTS HERE", color = colorResource(id=R.color.greenBT), fontSize = 30.sp)
                Spacer(modifier = Modifier.padding(10.dp))
                Text(text = "Let's set up your profile and start tracking progress", color = colorResource(id = R.color.greyTXT), fontSize = 15.sp)

                MenCard(isSelected = rvm.sex == "M") { rvm.onSexSelected("M")}
                WomenCard(isSelected = rvm.sex == "W") { rvm.onSexSelected("W")}
                OtherCard(isSelected = rvm.sex == "O") { rvm.onSexSelected("O")}
            }
            Button(
                onClick = {
                        navController.navigate(Routes.REGISTWO)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(16.dp),
                enabled = rvm.sex.isNotEmpty(),
                colors = ButtonDefaults.buttonColors(
                    // Color cuando el botón está habilitado
                    containerColor = colorResource(id = R.color.greenBT),
                    contentColor = Color.Black,
                    // Color cuando el botón NO está habilitado
                    disabledContainerColor = colorResource(id = R.color.greenCard),
                    disabledContentColor = Color.Black)
            ) {
                Text("Continue")
            }
        }
    })
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