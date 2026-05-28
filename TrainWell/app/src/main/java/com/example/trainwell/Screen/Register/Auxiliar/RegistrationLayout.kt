package com.example.trainwell.Screen.Register.Auxiliar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.trainwell.R
import com.example.trainwell.ViewModel.NavigationViewModel

@Composable
fun RegistrationLayout(
    pbvm: ProgressBarViewModel,
    step: Int,
    content: @Composable () -> Unit
) {
    // Actualizamos el progreso cada vez que este layout se cargue en una pantalla
    LaunchedEffect(step) {
        pbvm.updateProgress(step)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(colorResource(id = R.color.greenBG2), colorResource(id = R.color.greenBG))
                )
            )
    ) {
        // Barra de progreso
        LinearProgressIndicator(
            progress = { pbvm.currentProgress },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 60.dp, start = 20.dp, end = 20.dp)
                .height(8.dp)
                .clip(RoundedCornerShape(10.dp)),
            color = colorResource(id = R.color.greenBT),
            trackColor = Color.DarkGray
        )

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
            content()
        }
    }
}