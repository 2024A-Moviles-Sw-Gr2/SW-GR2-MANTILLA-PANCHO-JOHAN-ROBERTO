package com.example.proyecto2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class RegistroLogin : AppCompatActivity() {
    private lateinit var inputNombre: EditText
    private lateinit var inputDireccion: EditText
    private lateinit var inputTelefono: EditText
    private lateinit var inputPassword: EditText
    private lateinit var btnRegistrarse: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_login)

        inputNombre = findViewById(R.id.input_nombre)
        inputDireccion = findViewById(R.id.input_direccion)
        inputTelefono = findViewById(R.id.input_telef)
        inputPassword = findViewById(R.id.input_password)
        btnRegistrarse = findViewById(R.id.btnIniciarSesion)

        btnRegistrarse.setOnClickListener {
            val nombre = inputNombre.text.toString()
            val direccion = inputDireccion.text.toString()
            val telefono = inputTelefono.text.toString()
            val contrasena = inputPassword.text.toString()

            if (nombre.isNotEmpty() && direccion.isNotEmpty() && telefono.isNotEmpty() && contrasena.isNotEmpty()) {
                val nuevoUsuario = Usuario(nombre, direccion, telefono, contrasena)
                GestorUsuario.agregarUsuario(nuevoUsuario)
                Toast.makeText(this, "Usuario registrado con éxito", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }


}