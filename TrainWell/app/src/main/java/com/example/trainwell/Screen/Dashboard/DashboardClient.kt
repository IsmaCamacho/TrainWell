package com.example.trainwell.Screen.Dashboard

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.trainwell.ui.theme.DarkBackground
import com.example.trainwell.ui.theme.CardBackground
import com.example.trainwell.ui.theme.PrimaryGreen
import com.example.trainwell.ui.theme.TextGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientDashboardScreen(navController: NavHostController) {
    Scaffold(
        containerColor = DarkBackground,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(containerColor = DarkBackground),
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = android.R.drawable.ic_menu_gallery), // Sustituir por tu recurso
                            contentDescription = null,
                            modifier = Modifier.size(40.dp).clip(CircleShape).background(Color.Gray)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text("Hola, Alex 👋", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                            Text("¡A por todas hoy!", color = TextGray, fontSize = 12.sp)
                        }
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Notifications, contentDescription = null, tint = Color.White)
                    }
                }
            )
        },
        bottomBar = {
            // Reutilizamos la lógica del BottomBar anterior pero con los iconos de la nueva imagen
            NavigationBar(containerColor = Color(0xFF0F160F)) {
                val items = listOf("Inicio", "Explorar", "", "Planes", "Perfil")
                val icons = listOf(Icons.Default.Home, Icons.Default.Search, Icons.Default.Add, Icons.Default.CalendarMonth, Icons.Default.Person)

                items.forEachIndexed { index, item ->
                    if (index == 2) { // Espacio para el FAB central si quieres o un item vacío
                        Spacer(Modifier.weight(1f))
                    } else {
                        NavigationBarItem(
                            selected = index == 0,
                            onClick = {},
                            icon = { Icon(icons[index], contentDescription = null) },
                            label = { Text(item, fontSize = 10.sp) },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = PrimaryGreen,
                                unselectedIconColor = Color.Gray,
                                indicatorColor = Color.Transparent
                            )
                        )
                    }
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                containerColor = PrimaryGreen,
                shape = CircleShape,
                modifier = Modifier.offset(y = 50.dp) // Para que encaje en el hueco del BottomBar
            ) {
                Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(30.dp))
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // 1. TARJETA DE PROGRESO SEMANAL
            item {
                WeeklyProgressCard()
            }

            // 2. TU PLAN DE HOY
            item {
                SectionHeader(title = "Tu Plan de Hoy", actionText = "Ver calendario")
                TodayWorkoutCard()
            }

            // 3. ACCIONES RÁPIDAS
            item {
                Text("Acciones Rápidas", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(12.dp))
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        QuickActionItem("Registrar Peso", Icons.Default.MonitorWeight, Modifier.weight(1f))
                        QuickActionItem("Ver Dieta", Icons.Default.Restaurant, Modifier.weight(1f))
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        QuickActionItem("Mensajes", Icons.Default.Chat, Modifier.weight(1f))
                        QuickActionItem("Buscar Coach", Icons.Default.Search, Modifier.weight(1f))
                    }
                }
            }

            // 4. ENTRENADORES DESTACADOS
            item {
                SectionHeader(title = "Entrenadores Destacados", actionText = "Ver todos")
                LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    items(3) { FeaturedCoachItem() }
                }
            }

            item { Spacer(Modifier.height(20.dp)) }
        }
    }
}

@Composable
fun WeeklyProgressCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = CardBackground),
        shape = RoundedCornerShape(24.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Gráfico Circular
            Box(contentAlignment = Alignment.Center, modifier = Modifier.size(140.dp)) {
                Canvas(modifier = Modifier.size(120.dp)) {
                    drawArc(Color.DarkGray, 0f, 360f, false, style = Stroke(12.dp.toPx(), cap = StrokeCap.Round))
                    drawArc(
                        brush = Brush.sweepGradient(listOf(Color(0xFF004D1A), PrimaryGreen)),
                        startAngle = -90f,
                        sweepAngle = 280f, // 80% de 360
                        useCenter = false,
                        style = Stroke(12.dp.toPx(), cap = StrokeCap.Round)
                    )
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("80%", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.ExtraBold)
                    Text("WEEKLY", color = TextGray, fontSize = 10.sp)
                }
            }

            Spacer(Modifier.height(16.dp))
            Text("Tu Progreso Semanal", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text("¡Casi alcanzas tu meta! Sigue así.", color = TextGray, fontSize = 13.sp)

            Spacer(Modifier.height(20.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                ProgressMiniStat(Icons.Default.FitnessCenter, "4/5 Workouts")
                Divider(modifier = Modifier.height(20.dp).width(1.dp), color = Color.Gray)
                ProgressMiniStat(Icons.Default.LocalFireDepartment, "2,100 Kcal")
            }

            Spacer(Modifier.height(20.dp))
            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Ver Estadísticas Completas", color = Color.Black, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun TodayWorkoutCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground)
    ) {
        Column {
            Box(modifier = Modifier.height(180.dp).fillMaxWidth()) {
                // Imagen de fondo del ejercicio
                Image(
                    painter = painterResource(id = android.R.drawable.ic_dialog_info), // Sustituir
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                // Badge "Día 12"
                Surface(
                    color = Color.Black.copy(alpha = 0.7f),
                    modifier = Modifier.align(Alignment.TopEnd).padding(12.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("DÍA 12", color = Color.White, modifier = Modifier.padding(6.dp), fontSize = 12.sp)
                }
            }

            Column(Modifier.padding(16.dp)) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    WorkoutTag("DEFÍCIL", Color(0xFF331111), Color.Red)
                    WorkoutTag("45 MIN", Color(0xFF112233), Color.Cyan)
                }
                Spacer(Modifier.height(8.dp))
                Text("Pierna - Hipertrofia", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text(
                    "Enfoque en cuádriceps y glúteos. Asegúrate de calentar bien antes de empezar...",
                    color = TextGray, fontSize = 13.sp
                )
                Spacer(Modifier.height(16.dp))
                Button(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0A1F0A)),
                    border = BorderStroke(1.dp, Color(0xFF1E3D1E))
                ) {
                    Icon(Icons.Default.PlayArrow, contentDescription = null, tint = PrimaryGreen)
                    Text(" Iniciar Sesión", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun QuickActionItem(text: String, icon: ImageVector, modifier: Modifier) {
    Card(
        modifier = modifier.height(100.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(Modifier.size(40.dp).background(Color(0xFF1F2E1F), CircleShape), contentAlignment = Alignment.Center) {
                Icon(icon, contentDescription = null, tint = PrimaryGreen, modifier = Modifier.size(20.dp))
            }
            Spacer(Modifier.height(8.dp))
            Text(text, color = Color.White, fontSize = 12.sp)
        }
    }
}

@Composable
fun FeaturedCoachItem() {
    Column(modifier = Modifier.width(160.dp)) {
        Box(modifier = Modifier.height(200.dp).clip(RoundedCornerShape(16.dp)).background(Color.Gray)) {
            // Imagen del Coach
            Text("Image", modifier = Modifier.align(Alignment.Center))

            // Badge Pro
            Surface(
                color = PrimaryGreen,
                modifier = Modifier.align(Alignment.BottomStart).padding(8.dp),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text("Pro", color = Color.Black, fontSize = 10.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 4.dp))
            }
        }
        Text("Carlos Fit", color = Color.White, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 8.dp))
        Text("Espec. en Hipertrofia", color = TextGray, fontSize = 12.sp)
    }
}

@Composable
fun WorkoutTag(text: String, bgColor: Color, textColor: Color) {
    Surface(color = bgColor, shape = RoundedCornerShape(4.dp)) {
        Text(text, color = textColor, fontSize = 10.sp, modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp))
    }
}

@Composable
fun ProgressMiniStat(icon: ImageVector, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, contentDescription = null, tint = PrimaryGreen, modifier = Modifier.size(18.dp))
        Spacer(Modifier.width(6.dp))
        Text(text, color = Color.White, fontSize = 13.sp)
    }
}

@Composable
fun SectionHeader(title: String, actionText: String?, showAddIcon: Boolean = false) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(title, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White)
        if (actionText != null) {
            Text(actionText, color = PrimaryGreen, fontSize = 14.sp)
        }
        if (showAddIcon) {
            Icon(Icons.Default.AddCircle, contentDescription = null, tint = Color.DarkGray)
        }
    }
}