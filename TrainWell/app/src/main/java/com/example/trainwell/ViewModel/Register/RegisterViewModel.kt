package com.example.trainwell.ViewModel.Register

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.trainwell.Collections
import com.example.trainwell.Data
import com.example.trainwell.Model.Register.Customer
import com.example.trainwell.Model.Register.Trainer
import com.example.trainwell.Model.Register.User
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow


class RegisterViewModel: ViewModel() {

    var username by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")

    var price by mutableStateOf("")
    var averages by mutableStateOf("")




    var sex by mutableStateOf("")
    var height by mutableStateOf("")
    var weight by mutableStateOf("")
    var goal by mutableStateOf("")




    // Datos para la tabla TRAINER
    var especializacionesSeleccionadas = mutableStateListOf<String>()
    var biografia by mutableStateOf("")
    var precioMensual by mutableStateOf("")

    var aceptarNuevosClientes by mutableStateOf(false)
    var cupoMaximo by mutableStateOf("")
    var diasSeleccionados = mutableStateListOf<String>()

    var showErrorDialog by mutableStateOf(false)
    var registroExitoso = MutableStateFlow(false)

    fun toggleEspecializacion(especialidad: String) {
        if (especializacionesSeleccionadas.contains(especialidad)) {
            especializacionesSeleccionadas.remove(especialidad)
        } else {
            especializacionesSeleccionadas.add(especialidad)
        }
    }


    //Funciones para actualizar los campos
    fun onSexSelected(selectedSex: String) { sex = selectedSex }

    private val auth : FirebaseAuth = Firebase.auth
    fun finalizarRegistro() {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { authResult ->
                val uid = authResult.user?.uid ?: ""
                val user = User(
                    passwd = password,
                    name = username,
                    email = email,
                    role = "CUSTOMER",
                    dateRegister = System.currentTimeMillis().toString()
                )

                val customer = Customer(
                    goal = goal,
                    weight = weight.toDoubleOrNull() ?: 0.0,
                    height = height.toIntOrNull() ?: 0,
                    sex = sex
                )
                addUserCustomer(user, customer)
                registroExitoso.value = true
            }
            .addOnFailureListener { e ->
                Log.e("Ismael", "Error en Auth: ${e.message}")
                showErrorDialog = true

            }

    }

    fun finalizarRegistroEntrenador() {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { authResult ->
                val user = User(
                    passwd = password,
                    name = username,
                    email = email,
                    role = "TRAINER",
                    dateRegister = System.currentTimeMillis().toString()
                )

                val trainer = Trainer(
                    biography = biografia,
                    price = price.toDoubleOrNull() ?: 0.0,
                    average = averages.toDoubleOrNull() ?: 0.0,
                    specializations = especializacionesSeleccionadas.joinToString(", ")

                )

                addUserTrainer(user, trainer)

                registroExitoso.value = true
            }
            .addOnFailureListener { e ->
                Log.e("Ismael", "Error en Auth Trainer: ${e.message}")
                showErrorDialog = true
            }
    }

    val db = Firebase.firestore


    fun addUserCustomer(user: User, customer: Customer){
        try {
            val docRef = db.collection(Collections.users)
                .document()

            val userWithId = user.copy(userId = docRef.id)
            Data.idUser = userWithId.userId
            docRef.set(userWithId).addOnSuccessListener {
                Log.d("Ismael", "usuario añadido")
                // hacemos insert en customer
                val customerWithId = customer.copy(userId = userWithId.userId)
                addCustomer(customerWithId)
            }
                .addOnFailureListener { e ->
                    Log.e("Ismael", "Error agregando cliente", e)
                }

        } catch (e: Exception) {
            Log.e("Ismael", "Error agregando cliente")
        }
    }

    fun addCustomer(customer: Customer){
        try {
            db.collection(Collections.customers)
                .document(customer.userId)
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

            val userWithId = user.copy(userId = docRef.id)
            Data.idUser = userWithId.userId
            docRef.set(userWithId).addOnSuccessListener {
                Log.d("Ismael", "usuario añadido")
                // hacemos insert en customer
                val trainerWithId = trainer.copy(userId = userWithId.userId)
                addTrainer(trainerWithId)
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
                .document(trainer.userId)
                .set(trainer)
            Log.d("Ismael", "entrenador añadido")
        } catch (e: Exception) {
            Log.e("Ismael", "Error agregando entrenador")
        }
    }
}