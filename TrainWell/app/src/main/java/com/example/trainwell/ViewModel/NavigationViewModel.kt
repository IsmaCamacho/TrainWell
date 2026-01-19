package com.example.trainwell.ViewModel

import com.example.trainwell.Routes

class NavigationViewModel {
    // Función para calcular el progreso basado en la ruta
    fun getProgress(route: String?): Float {
        return when (route) {
            Routes.REGISONE -> 0.25f
            Routes.REGISTWO -> 0.50f
            Routes.REGISTHREE -> 0.75f
            Routes.REGISFOUR -> 1.0f
            else -> 0.0f
        }
    }
}