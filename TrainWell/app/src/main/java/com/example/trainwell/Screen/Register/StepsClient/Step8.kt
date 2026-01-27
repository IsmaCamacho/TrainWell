package com.example.trainwell.Screen.Register.StepsClient

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.trainwell.R
import com.example.trainwell.Routes


@Composable
fun ClientScreen8(
    navController: NavHostController,
) {

    Box(modifier = Modifier
        .fillMaxSize()
        .background(
            brush = Brush.verticalGradient(colors = listOf(colorResource(id = R.color.greenBG2), colorResource(id = R.color.greenBG)))))
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 40.dp, start = 20.dp, end = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "How active are you?",
                color = Color.White,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                lineHeight = 38.sp
            )

            Image(
                painter = painterResource(id = R.drawable.ic_step8),
                contentDescription = "Training illustration",
                modifier = Modifier
                    .height(210.dp)
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                contentScale = ContentScale.Fit //para no deformar la imagen
            )
            Spacer(modifier = Modifier.height(10.dp))

            // Lista de niveles de actividad
            val nivelesActividad = listOf(
                "Sedentary",
                "Lightly active",
                "Moderately active",
                "Very active"
            )

            nivelesActividad.forEach { nivel ->
                OutlinedButton(
                    onClick = {
                        // Aquí podrías guardar el nivel en tu rvm (RegisterViewModel)
                        navController.navigate(Routes.REGISNINE)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    border = BorderStroke(1.dp, colorResource(id = R.color.greenBT)),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = nivel,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(vertical = 10.dp)
                    )
                }
            }
        }

    }

}


