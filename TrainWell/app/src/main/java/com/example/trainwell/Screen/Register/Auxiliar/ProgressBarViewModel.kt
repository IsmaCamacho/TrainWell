package com.example.trainwell.Screen.Register.Auxiliar

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ProgressBarViewModel : ViewModel() {
    // Estado que sobrevive al cambio de pantallas
    var currentProgress by mutableFloatStateOf(0f)
        private set

    fun updateProgress(step: Int) {
        // Si tienes 6 pantallas, cada una es 1/6 (aprox 0.16)
        currentProgress = 0.20f
    }
}