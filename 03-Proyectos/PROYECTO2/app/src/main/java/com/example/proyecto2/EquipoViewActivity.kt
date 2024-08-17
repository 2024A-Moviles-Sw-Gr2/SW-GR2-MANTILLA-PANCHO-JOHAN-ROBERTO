package com.example.proyecto2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.app.TimePickerDialog
import android.content.Intent
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import java.text.SimpleDateFormat
import java.util.*


class EquipoViewActivity : AppCompatActivity() {
    private lateinit var spinnerTiempo: Spinner
    private lateinit var tvTiempoSeleccionado: TextView
    private lateinit var btnSolicitar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_equipo_view)

        val nombreEquipo = intent.getStringExtra("EQUIPO_NOMBRE") ?: "Equipo"
        val imagenId = intent.getIntExtra("EQUIPO_IMAGEN_ID", R.mipmap.raqueta)

        findViewById<TextView>(R.id.id_titulo_implemento).text = nombreEquipo
        findViewById<ImageView>(R.id.id_imagen_implemento).setImageResource(imagenId)

        spinnerTiempo = findViewById(R.id.spinnerTiempo)
        tvTiempoSeleccionado = findViewById(R.id.tvTiempoSeleccionado)
        btnSolicitar = findViewById(R.id.btn_solicitar)

        btnSolicitar.setOnClickListener {
            val intent = Intent(this, DetallePedido::class.java)
            intent.putExtra("EQUIPO_NOMBRE", nombreEquipo)
            intent.putExtra("EQUIPO_IMAGEN_ID", imagenId)

            // Get the selected item text using getSelectedItem()
            val selectedTime = spinnerTiempo.selectedItem.toString()
            intent.putExtra("TIEMPO_SELECCIONADO", selectedTime)
            startActivity(intent)
        }


        // Definir las horas predeterminadas
        val horas = arrayOf("7am a 9am", "9am a 11am", "12pm a 14pm", "14pm a 16pm")

        // Crear el adaptador para el Spinner
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, horas)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerTiempo.adapter = adapter

        // Manejar la selección del Spinner
        spinnerTiempo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {

            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // No se necesita hacer nada aquí
            }
        }
    }
}