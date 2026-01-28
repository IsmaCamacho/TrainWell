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


class RegisterViewModel: ViewModel() {

    //Datos para la tabla user (screenStepFinal)
    var username by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")



    // Datos para la tabla CUSTOMER (Screens 1, 3, 4, 5)
    var sex by mutableStateOf("")      // Screen 1
    var height by mutableStateOf("")    // Screen 3
    var weight by mutableStateOf("")      // Screen 4
    var goal by mutableStateOf("")  // Screen 5

    // Dato temporal (Screen 2)
    var edad by mutableStateOf("")      // No va a BBDD según tu esquema, pero sirve para cálculos



    // Datos para la tabla TRAINER
    var especializacionesSeleccionadas = mutableStateListOf<String>()
    var biografia by mutableStateOf("")

    fun toggleEspecializacion(especialidad: String) {
        if (especializacionesSeleccionadas.contains(especialidad)) {
            especializacionesSeleccionadas.remove(especialidad)
        } else {
            especializacionesSeleccionadas.add(especialidad)
        }
    }


    //Funciones para actualizar los campos
    fun onSexSelected(selectedSex: String) { sex = selectedSex }
    fun onHeightChanged(it: String) { height = it }
    fun onWeightChanged(it: String) { weight = it }
    fun onGoalSelected(it: String) { goal = it }

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
                //llamamos  a la funcion de customer
                addUserCustomer(user, customer)
            }
            .addOnFailureListener { e ->
                Log.e("Ismael", "Error en Auth: ${e.message}")
            }
    }

    val db = Firebase.firestore


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