package com.example.trainwell.ViewModel.Register

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.trainwell.Collections
import com.example.trainwell.Data
import com.example.trainwell.Model.Register.Customer
import com.example.trainwell.Model.Register.Trainer
import com.example.trainwell.Model.Register.User
import com.google.firebase.Firebase
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

class RegisterViewModel: ViewModel() {

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