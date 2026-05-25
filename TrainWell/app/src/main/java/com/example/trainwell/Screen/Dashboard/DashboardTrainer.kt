package com.example.trainwell.Screen.Dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.trainwell.ViewModel.Register.RegisterViewModel
import com.example.trainwell.ui.theme.DarkBackground
import com.example.trainwell.ui.theme.CardBackground
import com.example.trainwell.ui.theme.PrimaryGreen
import com.example.trainwell.ui.theme.TextGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrainerScreenDashboard(navController: NavHostController, rvm: RegisterViewModel) {
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
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 16.dp)
                    ) {
                        // Avatar e información
                        Box {
                            Icon(
                                Icons.Default.AccountCircle, // Cambiar por Image real si tienes la URL
                                contentDescription = "Profile",
                                modifier = Modifier
                                    .size(45.dp)
                                    .clip(CircleShape),
                                tint = Color.Gray
                            )
                            // Indicador online verde
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
                            Text("Coach Carlos 👋", fontSize = 18.sp, fontWeight = FontWeight.Bold) //coger de la bbdd
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(
                            Icons.Default.Notifications,
                            contentDescription = "Notif",
                            tint = Color.White
                        )
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = Color(0xFF0F160F),
                contentColor = PrimaryGreen
            ) {
                val items = listOf("Start", "Clients", "Messages", "Agenda", "Profile")
                val icons = listOf(
                    Icons.Default.Home,
                    Icons.Default.Group,
                    Icons.Default.Chat,
                    Icons.Default.CalendarMonth,
                    Icons.Default.Person
                )

                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = index == 0,
                        onClick = { /* Navegar */ },
                        icon = { Icon(icons[index], contentDescription = item) },
                        label = { Text(item, fontSize = 10.sp) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = PrimaryGreen,
                            selectedTextColor = PrimaryGreen,
                            unselectedIconColor = Color.Gray,
                            unselectedTextColor = Color.Gray,
                            indicatorColor = Color.Transparent
                        )
                    )
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Añadir cliente */ },
                containerColor = PrimaryGreen,
                contentColor = Color.Black,
                shape = CircleShape
            ) {
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
            // --- SECCIÓN ESTADÍSTICAS ---
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    StatCard("Clients", "24", "+2", Icons.Default.Group, Modifier.weight(1f)) //coger de la bbdd
                    StatCard(
                        "Pending",
                        "3",
                        "Requests",
                        Icons.Default.Mail,
                        Modifier.weight(1f)
                    )
                }
            }

            // --- SECCIÓN SOLICITUDES PENDIENTES ---
            item {
                SectionHeader("Pending Requests", "View all")
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(vertical = 8.dp)
                ) {
                    items(3) { // Mock de 3 solicitudes
                        RequestCard()
                    }
                }
            }

            // --- SECCIÓN MIS CLIENTES ---
            item {
                SectionHeader("My clients", null, showAddIcon = true)
            }

            items(4) { // Mock de lista de clientes
                ClientListItem()
            }

            item { Spacer(modifier = Modifier.height(20.dp)) }
        }
    }
}

@Composable
fun StatCard(
    title: String,
    value: String,
    subValue: String,
    icon: ImageVector,
    modifier: Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = CardBackground),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(title, color = TextGray, fontSize = 14.sp)
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    icon,
                    contentDescription = null,
                    tint = PrimaryGreen,
                    modifier = Modifier.size(20.dp)
                )
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
fun RequestCard() {
    Card(
        modifier = Modifier.width(280.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .background(Color.Gray, RoundedCornerShape(8.dp))
                ) // Placeholder avatar
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text("Ana García", fontWeight = FontWeight.Bold, color = Color.White)  //Coger de la bbdd
                    Surface(color = Color.DarkGray, shape = RoundedCornerShape(4.dp)) {
                        Text(
                            "Gain Muscle",
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                            fontSize = 10.sp,
                            color = Color.LightGray
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                "Hi Coach, I'm looking for help preparing for a competi...",
                color = TextGray,
                fontSize = 13.sp,
                maxLines = 2
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
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
                    Icon(
                        Icons.Default.Check,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(" Accept", fontSize = 12.sp, color = Color.Black)
                }
            }
        }
    }
}

@Composable
fun ClientListItem() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            Box {
                Icon(
                    Icons.Default.AccountCircle,
                    contentDescription = null,
                    modifier = Modifier.size(40.dp),
                    tint = Color.Gray
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
                    Text("Juan Pérez", fontWeight = FontWeight.Bold, color = Color.White)  // coger de la bbdd
                    Spacer(modifier = Modifier.weight(1f))
                    Text("2h ago", fontSize = 10.sp, color = TextGray)
                }
                Text("Strength Plan • Week 3", fontSize = 12.sp, color = TextGray)  //coger de la bbdd
                Spacer(modifier = Modifier.height(4.dp))
                LinearProgressIndicator(
                    progress = { 0.7f },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                        .clip(CircleShape),
                    color = PrimaryGreen,
                    trackColor = Color.DarkGray
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Icon(Icons.Default.ChatBubble, contentDescription = null, tint = Color.Gray)
        }
    }
}