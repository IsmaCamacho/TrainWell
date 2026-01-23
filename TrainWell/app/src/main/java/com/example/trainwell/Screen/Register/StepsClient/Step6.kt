package com.example.trainwell.Screen.Register.StepsClient

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
fun ClientScreen6(
    navController: NavHostController,
) {
//Botón para CONTINUAR
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
            UserGridSection()
        }
        Button(
            onClick = {
                navController.navigate(Routes.REGISTWO)
            },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(
                // Color cuando el botón está habilitado
                containerColor = colorResource(id = R.color.greenBT), //color de fondo del boton
                contentColor = Color.Black,
                // Color cuando el botón NO está habilitado
                disabledContainerColor = colorResource(id = R.color.greenCard), //color de fondo del boton
                disabledContentColor = Color.Black)
        ) {
            Text("Finish")
        }
    }

}

@Composable
fun UserGridSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "To date, we have helped 2,136,539 men gain muscle mass",
            color = Color.White,
            fontSize = 26.sp,
            fontWeight = FontWeight.ExtraBold,
            lineHeight = 32.sp,
            textAlign = TextAlign.Start
        )

        Spacer(modifier = Modifier.height(30.dp))

        // Rejilla de fotos con efecto de desvanecimiento
        Box(modifier = Modifier.fillMaxWidth().height(400.dp)) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(6),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                userScrollEnabled = false
            ) {
                items(36) { index ->
                    //API PUBLICA PARA FOTOS
                    AsyncImage(
                        model = "https://i.pravatar.cc/150?u=user$index", //API pública para coger imagenes de fotos de perfil gratis
                        contentDescription = "Avatar de usuario",
                        modifier = Modifier
                            .aspectRatio(1f)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Will you be the next to make a big change?",
            color = colorResource(id=R.color.greenBT),
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}
