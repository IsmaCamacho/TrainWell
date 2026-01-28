package com.example.trainwell.Model.Register

//MIRAR VIEWMODEL Y PENSAR SI METER MAS DETALLES QUE ESTAÍA BASTANTE BIEN SERIAN LOS CAMPOS:
//aceptarNuevosClientes
//cupoMaximo
//diasSeleccionados
data class Trainer(
    val userId:String = "",
    val biography: String,
    val price: Double,
    val average: Double? = null,
    var specializations: String
)
