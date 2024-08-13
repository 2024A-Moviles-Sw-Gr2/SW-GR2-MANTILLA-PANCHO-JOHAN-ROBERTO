package com.example.ferreteria

class Herramienta(
    val idHerramienta: Int,
    val nombre: String,
    val precio: Float,
    val enStock: Boolean,
    val codigoBarra: Long
) {
    override fun toString(): String {
        return "$idHerramienta, '$nombre', '$precio', $enStock, $codigoBarra)"
    }
}

