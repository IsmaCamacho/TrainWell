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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.trainwell.R
import com.example.trainwell.Routes


@Composable
fun ClientScreen9(
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
                .padding(horizontal = 25.dp)
                .padding(top = 40.dp, bottom = 100.dp), // Espacio para el botón abajo
            verticalArrangement = Arrangement.SpaceEvenly, // Distribuye el contenido
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Trainwell will help you lead a healthier and happier life",
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                lineHeight = 34.sp
            )

            Image(
                painter = painterResource(id = R.drawable.ic_helpmotivimage),
                contentDescription = "Motivation Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp) // Tamaño controlado para que no desplace el texto
                    .clip(RoundedCornerShape(15.dp)), // Bordes un poco más redondeados
                contentScale = ContentScale.Crop
            )

            Text(
                text = "“Focus on the progress; the rest will follow.”",
                color = colorResource(id = R.color.greenBT), // Color de acento para la cita
                fontSize = 22.sp,
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 10.dp)
            )
        }

        // Botón FINALIZAR anclado abajo
        Button(
            onClick = {
                navController.navigate(Routes.REGISFINAL)
            },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(20.dp)
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.greenBT),
                contentColor = Color.Black
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "Continue to Account",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }

}


