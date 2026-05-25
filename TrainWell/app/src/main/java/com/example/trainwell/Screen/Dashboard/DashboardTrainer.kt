package com.example.trainwell.Screen.Dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import com.example.trainwell.ui.theme.DarkBackground
import com.example.trainwell.ui.theme.CardBackground
import com.example.trainwell.ui.theme.PrimaryGreen
import com.example.trainwell.ui.theme.TextGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrainerScreenDashboard(navController: NavHostController, username: String) {
    // Estados para controlar las pestañas, búsquedas y filtros
    var selectedTab by remember { mutableIntStateOf(0) }
    var searchQuery by remember { mutableStateOf("") }
    var selectedFilter by remember { mutableStateOf("Todos") }

    // Listas sencillas de datos inventados (solo Strings)
    val pendingRequests = listOf("Ana García", "Marcos Ruiz", "Elena Sanz")
    val myClients = listOf("Juan Pérez", "María Gómez", "Carlos Ruiz", "Ana Torres")

    Scaffold(
        containerColor = DarkBackground,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(containerColor = DarkBackground),
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box {
                            Image(
                                painter = painterResource(id = R.drawable.ic_men),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.size(45.dp).clip(CircleShape)
                            )
                            Box(modifier = Modifier.size(12.dp).background(PrimaryGreen, CircleShape).align(Alignment.BottomEnd))
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text("Welcome", fontSize = 12.sp, color = TextGray)
                            Text(text = "Coach $username 👋", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
                        }
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar(containerColor = Color(0xFF0F160F), contentColor = PrimaryGreen) {
                val items = listOf("Inicio", "Clientes", "Chat", "Perfil")
                val icons = listOf(Icons.Default.Home, Icons.Default.Group, Icons.Default.Chat, Icons.Default.Person)

                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
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
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
            when (selectedTab) {
                0 -> StartTabContent(pendingRequests, myClients) // Pestaña Inicio con TODO recuperado
                1 -> ClientsTabContent(
                    clients = myClients,
                    searchQuery = searchQuery,
                    onSearchQueryChange = { searchQuery = it },
                    selectedFilter = selectedFilter,
                    onFilterSelected = { selectedFilter = it }
                )
            }
        }
    }
}

// ==========================================
// --- PESTAÑA INICIO COMPLETA ---
// ==========================================
@Composable
fun StartTabContent(requests: List<String>, clients: List<String>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // 1. Tarjetas de Estadísticas (Clients y Pending)
        item {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                StatCard("Clients", "24", "+2", Icons.Default.Group, Modifier.weight(1f))
                StatCard("Pending", "${requests.size}", "Requests", Icons.Default.Mail, Modifier.weight(1f))
            }
        }

        // 2. Carrusel Horizontal de Solicitudes Pendientes
        item {
            SectionHeader("Pending Requests", "View all")
            LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp), contentPadding = PaddingValues(vertical = 8.dp)) {
                items(requests) { name ->
                    val avatar = if (name.startsWith("A") || name.startsWith("E")) R.drawable.ic_women else R.drawable.ic_trainer1
                    Card(
                        modifier = Modifier.width(280.dp),
                        colors = CardDefaults.cardColors(containerColor = CardBackground),
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Image(
                                    painter = painterResource(id = avatar),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.size(50.dp).clip(RoundedCornerShape(8.dp))
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Column {
                                    Text(name, fontWeight = FontWeight.Bold, color = Color.White)
                                    Surface(color = Color.DarkGray, shape = RoundedCornerShape(4.dp)) {
                                        Text("Gain Muscle", modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp), fontSize = 10.sp, color = Color.LightGray)
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.height(12.dp))
                            Text("Hi Coach, I'm looking for help preparing for a competi...", color = TextGray, fontSize = 13.sp, maxLines = 2)
                            Spacer(modifier = Modifier.height(16.dp))
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                OutlinedButton(onClick = {}, modifier = Modifier.weight(1f), colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White)) { Text("Rechazar", fontSize = 12.sp) }
                                Button(onClick = {}, modifier = Modifier.weight(1f), colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen)) {
                                    Icon(Icons.Default.Check, null, modifier = Modifier.size(16.dp), tint = Color.Black)
                                    Text(" Accept", fontSize = 12.sp, color = Color.Black)
                                }
                            }
                        }
                    }
                }
            }
        }

        // 3. Listado de Mis Clientes (Vista básica de Inicio)
        item { SectionHeader("My clients", null, showAddIcon = true) }
        items(clients) { name ->
            val avatar = if (name.endsWith("z")) R.drawable.ic_men else R.drawable.ic_women
            Card(
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                colors = CardDefaults.cardColors(containerColor = CardBackground),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                    Box {
                        Image(painter = painterResource(id = avatar), contentDescription = null, contentScale = ContentScale.Crop, modifier = Modifier.size(40.dp).clip(CircleShape))
                        Box(Modifier.size(10.dp).background(PrimaryGreen, CircleShape).align(Alignment.BottomEnd))
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = name, fontWeight = FontWeight.Bold, color = Color.White)
                            Spacer(modifier = Modifier.weight(1f))
                            Text("2h ago", fontSize = 10.sp, color = TextGray)
                        }
                        Text(text = "Strength Plan • Week 3", fontSize = 12.sp, color = TextGray)
                        Spacer(modifier = Modifier.height(4.dp))
                        LinearProgressIndicator(progress = { 0.7f }, modifier = Modifier.fillMaxWidth().height(6.dp).clip(CircleShape), color = PrimaryGreen, trackColor = Color.DarkGray)
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Icon(Icons.Default.ChatBubble, null, tint = Color.Gray)
                }
            }
        }
    }
}

// ==========================================
// --- PESTAÑA CLIENTES AVANZADA ---
// ==========================================
@Composable
fun ClientsTabContent(
    clients: List<String>,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    selectedFilter: String,
    onFilterSelected: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item { Spacer(modifier = Modifier.height(8.dp)) }
        item { Text("Mis Clientes", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White) }

        // Buscador
        item {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = onSearchQueryChange,
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Buscar por nombre...", color = TextGray) },
                leadingIcon = { Icon(Icons.Default.Search, null, tint = TextGray) },
                shape = RoundedCornerShape(25.dp),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = CardBackground, unfocusedContainerColor = CardBackground,
                    focusedBorderColor = Color.Transparent, unfocusedBorderColor = Color.Transparent,
                    focusedTextColor = Color.White, unfocusedTextColor = Color.White
                )
            )
        }

        // Filtros rápidos horizontales
        item {
            val filtros = listOf("Todos", "Activos", "Pendientes", "Avisos")
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(filtros) { filtro ->
                    val isSelected = selectedFilter == filtro
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(if (isSelected) PrimaryGreen else CardBackground)
                            .clickable { onFilterSelected(filtro) }
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Text(text = filtro, color = if (isSelected) Color.Black else Color.White, fontWeight = FontWeight.SemiBold)
                    }
                }
            }
        }

        // Banner de invitación
        item {
            Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = CardBackground)) {
                Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.PersonAdd, null, tint = PrimaryGreen, modifier = Modifier.size(30.dp))
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text("Invitar nuevo cliente", fontWeight = FontWeight.Bold, color = Color.White)
                        Text("Envía un enlace de registro", color = TextGray, fontSize = 12.sp)
                    }
                }
            }
        }

        // Listado avanzado filtrado
        val filteredClients = clients.filter { it.contains(searchQuery, ignoreCase = true) }

        items(filteredClients) { name ->
            val esAlerta = name == "María Gómez"

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = CardBackground),
                shape = RoundedCornerShape(16.dp)
            ) {
                Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                    val avatar = if (name.endsWith("z")) R.drawable.ic_men else R.drawable.ic_women
                    Image(
                        painter = painterResource(id = avatar),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(50.dp).clip(CircleShape)
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = name, fontWeight = FontWeight.Bold, color = Color.White, fontSize = 18.sp)
                        Text(text = if (esAlerta) "Pérdida de Peso • Semana 2" else "Hipertrofia • Semana 4", color = TextGray, fontSize = 13.sp)
                        Spacer(modifier = Modifier.height(6.dp))

                        Surface(
                            color = if (esAlerta) Color(0xFF3A2414) else Color(0xFF142416),
                            shape = RoundedCornerShape(6.dp)
                        ) {
                            Text(
                                text = if (esAlerta) "⚠️ Requiere Revisión" else "Check-in completado",
                                color = if (esAlerta) Color(0xFFFFA500) else PrimaryGreen,
                                fontSize = 11.sp,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    if (esAlerta) {
                        Text("Ver Perfil", color = PrimaryGreen, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    } else {
                        Box(contentAlignment = Alignment.Center, modifier = Modifier.size(40.dp)) {
                            CircularProgressIndicator(progress = { 0.75f }, color = PrimaryGreen, trackColor = Color(0xFF1A261A), strokeWidth = 3.5.dp)
                            Text("75%", color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}

// ==========================================
// --- COMPONENTES AUXILIARES DE SOPORTE ---
// ==========================================
@Composable
fun StatCard(title: String, value: String, subValue: String, icon: ImageVector, modifier: Modifier) {
    Card(modifier = modifier, colors = CardDefaults.cardColors(containerColor = CardBackground), shape = RoundedCornerShape(16.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(title, color = TextGray, fontSize = 14.sp)
                Spacer(modifier = Modifier.weight(1f))
                Icon(icon, null, tint = PrimaryGreen, modifier = Modifier.size(20.dp))
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