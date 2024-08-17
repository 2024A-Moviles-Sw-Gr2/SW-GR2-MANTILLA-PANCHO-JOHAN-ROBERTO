package com.example.proyecto2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class DetallePedido : AppCompatActivity() {
    private lateinit var btnVolver: Button
    private lateinit var tvTiempoSeleccionado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_pedido)

        // Obtener los datos enviados desde EquipoViewActivity
        val nombreEquipo = intent.getStringExtra("EQUIPO_NOMBRE") ?: "Equipo"
        val Rentado = intent.getStringExtra("TIEMPO_SELECCIONADO") ?: "Equipo"

        // Mostrar los datos en la vista (asumiendo que tienes estos elementos en tu layout)
        findViewById<TextView>(R.id.tvNombreEquipo).text = nombreEquipo
        findViewById<TextView>(R.id.tv_renta).text = "Rentado $Rentado"

        btnVolver = findViewById(R.id.btnVolver)
        btnVolver.setOnClickListener {
            finish() // Esto cerrará la actividad actual y volverá a la anterior (EquipoViewActivity)
        }
    }
}