package com.example.trainwell.Screen.Register

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.trainwell.Routes

@Composable
fun Register(
    navController: NavHostController
){

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top= 250.dp)
            .padding(start = 20.dp)
            .padding(end = 20.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Welcome to Trainwell")
        Spacer(modifier = Modifier.size(8.dp))
        Text(text = "To start personalizing your experience, tell us who you are:")
        Spacer(modifier = Modifier.size(15.dp))
        UserCard()
        Spacer(modifier = Modifier.size(20.dp))
        Button(
            onClick = {
                // se navega a la screen del formulario cliente o del entrenador
            }
        ) {
            Row {
                Text(text = "Continue ")
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "Arow"
                )
            }
        }
        Spacer(modifier = Modifier.size(10.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Do you already have an account?",
                style = MaterialTheme.typography.labelMedium.copy(color = Color(0xFF64748B)),
            )
            TextButton(
                onClick = {
                    navController.navigate(Routes.login) //irse al formulario de registro
                }
            ) {
                Text(
                    text = "Log in",
                    style = MaterialTheme.typography.labelLarge.copy(
                        color = Color(0xFF64748B),
                        fontWeight = FontWeight.Bold)
                )
            }
        }
    }
}

@Composable
fun UserCard() {
    var context = LocalContext.current
    Card(
        border = BorderStroke(2.dp, Color.White),
        modifier = Modifier

            .fillMaxWidth()
            .height(80.dp)
            .combinedClickable(
                onDoubleClick = {

                },
                onLongClick = {
                },
                onClick = {
                    Toast.makeText(context,"I'm client",Toast.LENGTH_SHORT).show()
                }
            )
            .padding(5.dp)
    )
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "I'm client"
            )

        }
    }
    Card(
        border = BorderStroke(2.dp, Color.White),
        modifier = Modifier

            .fillMaxWidth()
            .height(80.dp)
            .combinedClickable(
                onDoubleClick = {

                },
                onLongClick = {
                },
                onClick = {
                    Toast.makeText(context,"soy entrenador",Toast.LENGTH_SHORT).show()
                }
            )
            .padding(5.dp)
    )
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "I'm trainer"
            )

        }
    }
}