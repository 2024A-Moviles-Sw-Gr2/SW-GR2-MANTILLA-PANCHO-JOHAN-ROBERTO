package com.example.ferreteria

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar

class EntrenadorHerramienta : AppCompatActivity() {
    private var herramientaId: Int? = null
    private var ferreteriaId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entrenador_herramienta)

        val titulo = findViewById<TextView>(R.id.id_operacion_herramienta)
        val nombre = findViewById<EditText>(R.id.input_nombre_herramienta)
        val precio = findViewById<EditText>(R.id.input_precio_herramienta)
        val botonGuardar = findViewById<Button>(R.id.btn_guardar_herramientas)

        ferreteriaId = intent.getIntExtra("FERRETERIA_ID", -1)
        herramientaId = intent.getIntExtra("HERRAMIENTA_ID", -1)
        if (herramientaId != -1) {
            nombre.setText(intent.getStringExtra("HERRAMIENTA_NOMBRE"))
            precio.setText(intent.getDoubleExtra("HERRAMIENTA_PRECIO", 0.0).toString())
            titulo.text = "Editar Herramienta"
        }else{
            titulo.text = "Crear Herramienta"
        }

        botonGuardar.setOnClickListener {
            val nombreTexto = nombre.text.toString()
            val precioTexto = precio.text.toString()

            if (nombreTexto.isNotEmpty() && precioTexto.isNotEmpty()) {
                val precioDouble = precioTexto.toDoubleOrNull()
                if (precioDouble != null) {
                    val resultado = if (herramientaId == -1) {
                        EBaseDeDatos.tablaEntrenador?.crearHerramienta(
                            nombreTexto,
                            precioDouble,
                            ferreteriaId
                        ) ?: false
                    } else {
                        EBaseDeDatos.tablaEntrenador?.actualizarHerramienta(
                            herramientaId!!,
                            nombreTexto,
                            precioDouble
                        ) ?: false
                    }

                    if (resultado) {
                        setResult(Activity.RESULT_OK)
                        finish()
                    } else {
                        mostrarSnackbar("Error al guardar la herramienta")
                    }
                } else {
                    mostrarSnackbar("El precio debe ser un número válido")
                }
            } else {
                mostrarSnackbar("Por favor, completa todos los campos")
            }
        }
    }

    private fun mostrarSnackbar(texto: String) {
        Snackbar.make(
            findViewById(R.id.id_layout_entrenador_herramienta),
            texto,
            Snackbar.LENGTH_SHORT
        ).show()
    }
}