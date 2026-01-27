package com.example.trainwell.ViewModel.Register

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.trainwell.Collections
import com.example.trainwell.Data
import com.example.trainwell.Model.Register.Customer
import com.example.trainwell.Model.Register.Trainer
import com.example.trainwell.Model.Register.User
import com.google.firebase.Firebase
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.tasks.await

class RegisterViewModel: ViewModel() {

    //AUXILIAR PARA CUANDO HAGA FALTA
    // Datos para la tabla USUARIO (Screen 7)
    var nombreUsuario by mutableStateOf("")
    var correo by mutableStateOf("")
    var password by mutableStateOf("")

    // Datos para la tabla CLIENTE (Screens 1, 3, 4, 5)
    var sexo by mutableStateOf("")      // Screen 1
    var altura by mutableStateOf("")    // Screen 3
    var peso by mutableStateOf("")      // Screen 4
    var objetivo by mutableStateOf("")  // Screen 5

    // Dato temporal (Screen 2)
    var edad by mutableStateOf("")      // No va a BBDD según tu esquema, pero sirve para cálculos

    // Función final en la Screen 7
    fun finalizarRegistro() {
        // 1. Lógica para crear el usuario en Authentication (Firebase o tu sistema)
        // 2. Insertar en la tabla USUARIO (nombre, correo, pass)
        // 3. Insertar en la tabla CLIENTE usando el ID del usuario creado
    }

    val db = Firebase.firestore

    val sex = MutableStateFlow<String>("")

    // Función para actualizar el valor de sex
    fun onSexSelected(selectedSex: String) {
        sex.value = selectedSex
    }


    //AÑADIR A LA BBDD un usuario
    fun addUserCustomer(user: User, customer: Customer){
        try {
            val docRef = db.collection(Collections.users)
                .document()

            val userWithId = user.copy(userId = docRef.id) //si quiero que se guarde el id y asi poder usarlo en el customer
            Data.idUser = userWithId.userId
            docRef.set(userWithId).addOnSuccessListener { // si se añade el usuario, añadimos el cliente
                Log.d("Ismael", "usuario añadido")
                // hacemos insert en customer
                val customerWithId = customer.copy(userId = userWithId.userId)
                addCustomer(customerWithId) // añadimos el cliente con la id autogenerada del usuario general
            }
                .addOnFailureListener { e ->
                    Log.e("Ismael", "Error agregando cliente", e)
                }

        } catch (e: Exception) {
            Log.e("Ismael", "Error agregando cliente")
        }
    }

    //Añadimos un cliente con el id autogenerado del usuario creado anteriormente (el usuario general)
    fun addCustomer(customer: Customer){
        try {
            db.collection(Collections.customers)
                .document(customer.userId) // asigna como documento el id del campo userId
                .set(customer)
            Log.d("Ismael", "cliente añadido")
        } catch (e: Exception) {
            Log.e("Ismael", "Error agregando cliente")
        }
    }

    fun addUserTrainer(user: User, trainer: Trainer){
        try {
            val docRef = db.collection(Collections.users)
                .document()

            val userWithId = user.copy(userId = docRef.id) //si quiero que se guarde el id y asi poder usarlo en el customer
            Data.idUser = userWithId.userId
            docRef.set(userWithId).addOnSuccessListener { // si se añade el usuario, añadimos el entrenador
                Log.d("Ismael", "usuario añadido")
                // hacemos insert en customer
                val trainerWithId = trainer.copy(userId = userWithId.userId)
                addTrainer(trainerWithId) // añadimos el entrenador con la id autogenerada del usuario general
            }
                .addOnFailureListener { e ->
                    Log.e("Ismael", "Error agregando entrenador", e)
                }

        } catch (e: Exception) {
            Log.e("Ismael", "Error agregando entrenador")
        }
    }

    fun addTrainer(trainer: Trainer){
        try {
            db.collection(Collections.trainers)
                .document(trainer.userId) // asigna como documento el id del campo userId
                .set(trainer)
            Log.d("Ismael", "entrenador añadido")
        } catch (e: Exception) {
            Log.e("Ismael", "Error agregando entrenador")
        }
    }




}