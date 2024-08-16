package com.example.proyecto2

object GestorUsuario {
    private val usuarios = mutableListOf(
        Usuario("Johan", "Monjas", "0983706479", "123"),
        Usuario("Pablo", "El Dorado", "0987654321", "admin123")
    )

    fun agregarUsuario(usuario: Usuario) {
        usuarios.add(usuario)
    }

    fun autenticar(nombre: String, contrasena: String): Boolean {
        return usuarios.any { it.nombre == nombre && it.contrasena == contrasena }
    }

    fun obtenerUsuarios(): List<Usuario> = usuarios.toList()
}