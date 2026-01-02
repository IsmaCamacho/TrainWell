package com.example.trainwell.ViewModel.Login

import android.util.Log
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

    fun getUser(userLogin: Login) = viewModelScope.launch {
        try {
            val result = db.collection(Collections.users)
                .whereEqualTo("email", userLogin.email)
                .whereEqualTo("passwd", userLogin.passwd)
                .get()
                .await()

            //si devuelve un usuario es porque existe
            if(!result.isEmpty){
                _usuarioExiste.value=true
                //guardo en el object los datos que voy a utilizar despues
                Data.idUser = result.documents.first().id  //quiero el documento para al agregar la nota saber de quien es el id del user
                Data.emailUser = userLogin.email
            }else{
                _usuarioExiste.value=false
            }

        } catch (e: Exception) {
            Log.e("Ismael", "Error al cargar usuarios", e)
            _usuarioExiste.value = false
        }
    }

}