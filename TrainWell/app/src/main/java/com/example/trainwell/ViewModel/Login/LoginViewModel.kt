package com.example.trainwell.ViewModel.Login

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trainwell.Collections
import com.example.trainwell.Data
import com.example.trainwell.Model.Login.Login
import com.google.firebase.Firebase
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class LoginViewModel: ViewModel(){

    val db = Firebase.firestore
    private val _usuarioExiste = MutableLiveData<Boolean>()
    val usuarioExiste: LiveData<Boolean> get() = _usuarioExiste
    var username by mutableStateOf("")
    fun getUser(userLogin: Login) = viewModelScope.launch {
        try {
            val result = db.collection(Collections.users)
                .whereEqualTo("email", userLogin.email)
                .whereEqualTo("passwd", userLogin.passwd)
                .get()
                .await()

            if(!result.isEmpty){
                Log.d("Ismael", "Usuario encontrado. ID: ${result.documents.first().id}")
                _usuarioExiste.value=true
                username = result.documents.first().get("name").toString()
                //guardo en el object los datos que voy a utilizar despues
                Data.idUser = result.documents.first().id  //quiero el documento para saber de quien es el id del user
                Data.emailUser = userLogin.email
                Data.role = result.documents.first().get("role").toString()
            }else{
                Log.d("Ismael", "FALLO: No coincide email o contraseña.")
                _usuarioExiste.value=false
            }

        } catch (e: Exception) {
            Log.e("Ismael", "Error al cargar usuarios", e)
            _usuarioExiste.value = false
        }
    }

}