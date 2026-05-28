package com.example.trainwell.Screen.Register

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.trainwell.R
import com.example.trainwell.Routes

@SuppressLint("ResourceAsColor")
@Composable
fun Register(
    navController: NavHostController
) {
    var role by remember { mutableStateOf("") }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        colorResource(id = R.color.greenBG2),
                        colorResource(id = R.color.greenBG)
                    )
                )
            )
    ){
        Image(
            painter = painterResource(id = R.drawable.ic_mainimage),
            contentDescription = "Main Image",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .fillMaxWidth()
                .height(450.dp)
                .graphicsLayer { alpha = 0.99f }
                .drawWithContent {
                    drawContent()
                    drawRect(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Black,
                                Color.Transparent
                            ),
                            startY = size.height * 0.5f,
                            endY = size.height
                        ),
                        blendMode = BlendMode.DstIn
                    )
                }
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 250.dp)
                .padding(start = 20.dp)
                .padding(end = 20.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Welcome to ", color = Color.White, fontSize = 30.sp)
            Text(text = "TrainWell", color = colorResource(id=R.color.greenBT), fontSize = 30.sp)
            Spacer(modifier = Modifier.size(8.dp))
            Text(text = "To start personalizing your experience" , color = colorResource(id=R.color.greyTXT))
            Text(text = "tell us who you are:" , color = colorResource(id=R.color.greyTXT))
            Spacer(modifier = Modifier.size(15.dp))
            // Cuando el usuario pulsa en la tarjeta, la variable role pasa a ser "C", se recompone,
            // por lo que isSelected detecta que role == "C" y se cambia el borde y entra dentro de la función.
            ClientCard(isSelected = role == "C") {
                role = "C"
                Log.e("sergio", "El role es: $role")
            }
            TrainerCard(isSelected = role == "E") {
                role = "E"
                Log.e("sergio", "El role es: $role")
            }
            Spacer(modifier = Modifier.size(20.dp))
            Row {
                ElevatedButton(onClick = {
                    when (role) {
                        "C" -> {
                            Log.e("sergio", "en el boton continue, has pulsado en client")
                            navController.navigate(Routes.clientRegister)
                        }
                        "E" -> {
                            Log.e("sergio", "en el boton continue, has pulsado en entrenador")
                            navController.navigate(Routes.trainerRegister)
                        }
                        else -> Log.e("sergio", "no has pulsao na")
                    }
                },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.greenBT),
                        contentColor = Color.Black)
                ) {
                    Text(text = "Continue")
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "Arrow"
                    )
                }
            }
            Spacer(modifier = Modifier.size(10.dp))
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Do you already have an account?", color = colorResource(id=R.color.greyTXT),
                    style = MaterialTheme.typography.labelMedium.copy(color = Color(0xFF64748B)),
                )
                TextButton(
                    onClick = {
                        navController.navigate(Routes.login)
                    }
                ) {
                    Text(
                        text = "Log in", color = colorResource(id=R.color.greenBT),
                        style = MaterialTheme.typography.labelLarge.copy(
                            color = Color(0xFF64748B),
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun ClientCard(isSelected:Boolean, onClick: () -> Unit) {
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
                    Log.e("sergio", "Has pulsado en la card cliente")
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
            Image(
                painter = painterResource(id = R.drawable.ic_client),
                contentDescription = null,
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "I'm a Client", fontSize = 20.sp, color = Color.White,  fontWeight = FontWeight.Bold)
                Text(
                    text = "I'm looking for personalized training and nutrition plans",
                    color = colorResource(id = R.color.greyTXT),
                    fontSize = 13.sp
                )
            }
        }
    }

}

@Composable
fun TrainerCard(isSelected:Boolean,onClick: () -> Unit) {
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
                    Log.e("sergio", "Has pulsado en la card entrenador")
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
            Image(
                painter = painterResource(id = R.drawable.ic_trainer),
                contentDescription = null,
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "I'm a Trainer", fontSize = 20.sp, color = Color.White,  fontWeight = FontWeight.Bold)
                Text(
                    text = "I am a professional and I want to offer my services.",
                    color = colorResource(id = R.color.greyTXT),
                    fontSize = 13.sp
                )
            }
        }
    }
}
