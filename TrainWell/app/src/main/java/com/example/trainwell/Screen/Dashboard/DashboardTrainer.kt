package com.example.trainwell.Screen.Dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.trainwell.R
import com.example.trainwell.ViewModel.Register.RegisterViewModel
import com.example.trainwell.ui.theme.DarkBackground
import com.example.trainwell.ui.theme.CardBackground
import com.example.trainwell.ui.theme.PrimaryGreen
import com.example.trainwell.ui.theme.TextGray

// Estructuras de datos simuladas (Mocks) para alimentar las listas individuales
data class RequestData(val name: String, val goal: String, val text: String, val imageRes: Int)
data class ClientData(val name: String, val plan: String, val progress: Float, val imageRes: Int)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrainerScreenDashboard(navController: NavHostController, rvm: RegisterViewModel) {

    // Lista 1: Peticiones pendientes con nombres e imágenes distintas
    val pendingRequests = listOf(
        RequestData("Ana García", "Gain Muscle", "Hi Coach, I'm looking for help preparing for a competi...", R.drawable.ic_women),
        RequestData("Marcos Ruiz", "Lose Weight", "Hola! Me gustaría empezar un plan de choque de 3 meses...", R.drawable.ic_trainer1),
        RequestData("Elena Sanz", "Pilates", "Busco un entrenamiento enfocado a mejorar la postura...", R.drawable.ic_trainer2)
    )

    // Lista 2: Mis clientes con nombres, planes, barras de progreso e imágenes distintas
    val myClients = listOf(
        ClientData("Juan Pérez", "Strength Plan • Week 3", 0.7f, R.drawable.ic_loseweight),
        ClientData("Sofía Torres", "Gain Muscle • Week 1", 0.2f, R.drawable.ic_women),
        ClientData("Carlos Valls", "Fat Loss • Week 5", 0.9f, R.drawable.ic_trainer),
        ClientData("Lucía M.", "Running Base • Week 2", 0.4f, R.drawable.ic_loseweightgirl)
    )

    Scaffold(
        containerColor = DarkBackground,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = DarkBackground,
                    titleContentColor = Color.White
                ),
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth().padding(end = 16.dp)
                    ) {
                        Box {
                            Image(
                                painter = painterResource(id = R.drawable.ic_men),
                                contentDescription = "Profile",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(45.dp)
                                    .clip(CircleShape)
                            )
                            Box(
                                modifier = Modifier
                                    .size(12.dp)
                                    .background(PrimaryGreen, CircleShape)
                                    .align(Alignment.BottomEnd)
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text("Welcome", fontSize = 12.sp, color = TextGray)
                            Text("Coach Carlos 👋", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(Icons.Default.Notifications, contentDescription = "Notif", tint = Color.White)
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar(containerColor = Color(0xFF0F160F), contentColor = PrimaryGreen) {
                val items = listOf("Start", "Clients", "Messages", "Agenda", "Profile")
                val icons = listOf(Icons.Default.Home, Icons.Default.Group, Icons.Default.Chat, Icons.Default.CalendarMonth, Icons.Default.Person)

                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = index == 0,
                        onClick = { /* Navegar */ },
                        icon = { Icon(icons[index], contentDescription = item) },
                        label = { Text(item, fontSize = 10.sp) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = PrimaryGreen, selectedTextColor = PrimaryGreen,
                            unselectedIconColor = Color.Gray, unselectedTextColor = Color.Gray,
                            indicatorColor = Color.Transparent
                        )
                    )
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { }, containerColor = PrimaryGreen, contentColor = Color.Black, shape = CircleShape) {
                Icon(Icons.Default.Add, contentDescription = "Add", modifier = Modifier.size(30.dp))
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    StatCard("Clients", "24", "+2", Icons.Default.Group, Modifier.weight(1f))
                    StatCard("Pending", "3", "Requests", Icons.Default.Mail, Modifier.weight(1f))
                }
            }

            // --- SECCIÓN SOLICITUDES PENDIENTES DINÁMICA ---
            item {
                SectionHeader("Pending Requests", "View all")
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(vertical = 8.dp)
                ) {
                    items(pendingRequests) { itemRequest ->
                        RequestCard(data = itemRequest)
                    }
                }
            }

            // --- SECCIÓN MIS CLIENTES DINÁMICA ---
            item {
                SectionHeader("My clients", null, showAddIcon = true)
            }

            items(myClients) { itemClient ->
                ClientListItem(data = itemClient)
            }

            item { Spacer(modifier = Modifier.height(20.dp)) }
        }
    }
}

@Composable
fun StatCard(title: String, value: String, subValue: String, icon: ImageVector, modifier: Modifier) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = CardBackground),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(title, color = TextGray, fontSize = 14.sp)
                Spacer(modifier = Modifier.weight(1f))
                Icon(icon, contentDescription = null, tint = PrimaryGreen, modifier = Modifier.size(20.dp))
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.Bottom) {
                Text(value, fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White)
                Spacer(modifier = Modifier.width(8.dp))
                Text(subValue, fontSize = 12.sp, color = PrimaryGreen)
            }
        }
    }
}

@Composable
fun RequestCard(data: RequestData) {
    Card(
        modifier = Modifier.width(280.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Imagen de recurso dinámica con esquinas redondeadas según diseño
                Image(
                    painter = painterResource(id = data.imageRes),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(text = data.name, fontWeight = FontWeight.Bold, color = Color.White)
                    Surface(color = Color.DarkGray, shape = RoundedCornerShape(4.dp)) {
                        Text(
                            text = data.goal,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                            fontSize = 10.sp,
                            color = Color.LightGray
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = data.text, color = TextGray, fontSize = 13.sp, maxLines = 2)
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedButton(
                    onClick = {},
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White)
                ) { Text("Rechazar", fontSize = 12.sp) }
                Button(
                    onClick = {},
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen)
                ) {
                    Icon(Icons.Default.Check, contentDescription = null, modifier = Modifier.size(16.dp))
                    Text(" Accept", fontSize = 12.sp, color = Color.Black)
                }
            }
        }
    }
}

@Composable
fun ClientListItem(data: ClientData) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            Box {
                // Imagen de recurso dinámica y circular para el listado de clientes activos
                Image(
                    painter = painterResource(id = data.imageRes),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                )
                Box(
                    Modifier
                        .size(10.dp)
                        .background(PrimaryGreen, CircleShape)
                        .align(Alignment.BottomEnd)
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = data.name, fontWeight = FontWeight.Bold, color = Color.White)
                    Spacer(modifier = Modifier.weight(1f))
                    Text("2h ago", fontSize = 10.sp, color = TextGray)
                }
                Text(text = data.plan, fontSize = 12.sp, color = TextGray)
                Spacer(modifier = Modifier.height(4.dp))
                LinearProgressIndicator(
                    progress = { data.progress },
                    modifier = Modifier.fillMaxWidth().height(6.dp).clip(CircleShape),
                    color = PrimaryGreen,
                    trackColor = Color.DarkGray
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Icon(Icons.Default.ChatBubble, contentDescription = null, tint = Color.Gray)
        }
    }
}
