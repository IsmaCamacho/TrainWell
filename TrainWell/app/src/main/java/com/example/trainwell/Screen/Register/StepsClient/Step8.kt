package com.example.trainwell.Screen.Register.StepsClient

import androidx.compose.foundation.BorderStroke
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
                .padding(top = 60.dp)
        ) {
            Text(text = "How active are you?", color = Color.White, fontSize = 30.sp)

            OutlinedButton(
                onClick = {
                    navController.navigate(Routes.REGISNINE)
                },
                modifier = Modifier
                    .fillMaxWidth()

                    .padding(16.dp)

            ) {
                Text("Sedentary")
            }
            OutlinedButton(
                onClick = {
                    navController.navigate(Routes.REGISNINE)
                },
                modifier = Modifier
                    .fillMaxWidth()

                    .padding(16.dp)

            ) {
                Text("Lightly active ")
            }
            OutlinedButton(
                onClick = {
                    navController.navigate(Routes.REGISNINE)
                },
                modifier = Modifier
                    .fillMaxWidth()

                    .padding(16.dp)

            ) {
                Text("Moderately active ")
            }
            OutlinedButton(
                onClick = {
                    navController.navigate(Routes.REGISNINE)
                },
                modifier = Modifier
                    .fillMaxWidth()

                    .padding(16.dp)

            ) {
                Text("Very active ")
            }
        }

    }

}


