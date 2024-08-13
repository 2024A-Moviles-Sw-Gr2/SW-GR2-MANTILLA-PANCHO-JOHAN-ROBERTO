package com.example.ferreteria

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class VerFerreteria : AppCompatActivity() {
    private lateinit var listView: ListView
    private lateinit var adaptador: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_ferreteria)

        listView = findViewById(R.id.lv_ferreterias)

        val ferreterias = EBaseDeDatos.tablaEntrenador?.consultarTodasLasFerreterias() ?: listOf()
        val nombresFerreterias = ferreterias.map { it.nombre }

        adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1, nombresFerreterias)
        listView.adapter = adaptador
    }
}