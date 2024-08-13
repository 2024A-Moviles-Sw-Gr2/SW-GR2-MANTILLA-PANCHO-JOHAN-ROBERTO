package com.example.ferreteria

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar

class EntrenadorFerreteria : AppCompatActivity() {

    fun mostrarSnackbar(texto:String){
        val snack = Snackbar.make(
            findViewById(R.id.pnl_ferreteria),
            texto,
            Snackbar.LENGTH_INDEFINITE
        )
        snack.show()
    } // cl_sqlite

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entrenador_ferreteria)

        val botonGuardar = findViewById<Button>(R.id.btn_guardar)
        botonGuardar.setOnClickListener {
            val nombre = findViewById<EditText>(R.id.input_nombre)
            val estaAbierto = findViewById<EditText>(R.id.input_abierta)
            val respuesta = EBaseDeDatos.tablaEntrenador!!
                .crearEntrenador(
                    nombre.text.toString(),
                    estaAbierto.text.toString()
                )
            if(respuesta) mostrarSnackbar("Entr. creado!")
            //retrasar 2 segundos la notiificacion
            irActividad(MainActivity::class.java)
        }

    }

    fun irActividad(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }

}