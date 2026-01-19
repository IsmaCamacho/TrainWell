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
        //cada pantalla es 0.2
        currentProgress = step * 0.20f
    }
}