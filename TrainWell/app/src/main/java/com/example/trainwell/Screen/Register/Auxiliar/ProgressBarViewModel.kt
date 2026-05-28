package com.example.trainwell.Screen.Register.Auxiliar

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ProgressBarViewModel : ViewModel() {
    var currentProgress by mutableFloatStateOf(0f)
        private set

    fun updateProgress(step: Int) {
        currentProgress = step * 0.20f
    }
}