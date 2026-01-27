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
fun ClientScreen7(
    navController: NavHostController,
) {

    Box(modifier = Modifier
        .fillMaxSize()
        .background(
            brush = Brush.verticalGradient(colors = listOf(colorResource(id = R.color.greenBG2), colorResource(id = R.color.greenBG)))))
    {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 100.dp, start = 20.dp, end = 20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp), // Un poco más de espacio entre botones
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Any previous training experience?",
                color = Color.White,
                fontSize = 30.sp,
                lineHeight = 36.sp, // Mejor lectura en títulos largos
                textAlign = TextAlign.Center
            )
            Image(
                painter = painterResource(id = R.drawable.ic_step7),
                contentDescription = "Training illustration",
                modifier = Modifier
                    .height(180.dp)
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                contentScale = ContentScale.Fit //para no deformar la imagen
            )
            Spacer(modifier = Modifier.height(20.dp))

            // Lista de opciones para no repetir código (DRY: Don't Repeat Yourself)
            val opciones = listOf(
                "Yes, I train regularly",
                "Yes, less than a year ago",
                "Yes, more than a year ago",
                "No, none"
            )

            opciones.forEach { texto ->
                OutlinedButton(
                    onClick = { navController.navigate(Routes.REGISEIGHT) },
                    modifier = Modifier.fillMaxWidth(),
                    border = BorderStroke(1.dp, colorResource(id = R.color.greenBT)), // Borde verde
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color.White // Texto en blanco para que se lea bien
                    )
                ) {
                    Text(
                        text = texto,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }
        }

    }

}


