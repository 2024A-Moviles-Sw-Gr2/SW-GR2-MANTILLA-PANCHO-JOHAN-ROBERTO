package com.example.examen2bm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar

class EntrenadorFerreteria : AppCompatActivity() {
    private var idFerreteria: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entrenador_ferreteria)

        val titulo = findViewById<TextView>(R.id.id_operacion_ferreteria)
        val nombre = findViewById<EditText>(R.id.input_nombre)
        val estaAbierto = findViewById<EditText>(R.id.input_abierta)
        val botonGuardar = findViewById<Button>(R.id.btn_guardar)

        val extras = intent.extras
        if (extras != null) {
            idFerreteria = extras.getInt("ID")
            nombre.setText(extras.getString("NOMBRE"))
            estaAbierto.setText(extras.getString("ABIERTO"))
            titulo.text = "Editar Ferretería"
        } else {
            titulo.text = "Crear Ferretería"
        }

        botonGuardar.setOnClickListener {
            val nombreTexto = nombre.text.toString()
            val abiertaTexto = estaAbierto.text.toString()

            // Verifica que los campos no estén vacíos antes de intentar guardar
            if (nombreTexto.isNotEmpty() && abiertaTexto.isNotEmpty()) {
                val respuesta = if (idFerreteria == null) {
                    // Crear nueva ferretería
                    EBaseDeDatos.tablaEntrenador?.crearEntrenador(nombreTexto, abiertaTexto)
                } else {
                    // Editar ferretería existente
                    EBaseDeDatos.tablaEntrenador?.actualizarEntrenadorFormulario(
                        nombreTexto,
                        abiertaTexto,
                        idFerreteria!!
                    )
                }

                if (respuesta == true) {
                    mostrarSnackbar("Ferretería guardada!")
                    setResult(RESULT_OK)
                    finish()
                } else {
                    mostrarSnackbar("Error al guardar la ferretería")
                }
            } else {
                mostrarSnackbar("Por favor, llena todos los campos.")
            }
        }

    }

    private fun mostrarSnackbar(texto: String) {
        Snackbar.make(
            findViewById(R.id.pnl_ferreteria),
            texto,
            Snackbar.LENGTH_SHORT
        ).show()
    }
}