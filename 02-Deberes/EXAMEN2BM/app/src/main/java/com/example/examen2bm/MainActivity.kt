package com.example.examen2bm

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var listView: ListView
    private lateinit var adaptador: FerreteriaAdapter //adaptador personal
    private val arreglo = arrayListOf<BFerreteria>()
    private var posicionItemSeleccionado = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        EBaseDeDatos.inicializarBD(this)

        listView = findViewById(R.id.lv_list_view_ferreteria)
        adaptador = FerreteriaAdapter(
            this,
            arreglo
        )
        listView.adapter = adaptador
        cargarFerreterias()

        val botonCrear = findViewById<Button>(R.id.btn_crear_ferreteria)
        botonCrear.setOnClickListener {
            val intent = Intent(this, EntrenadorFerreteria::class.java)
            startActivityForResult(intent, REQUEST_CODE_CREAR)
        }

        registerForContextMenu(listView)

    }

    private fun cargarFerreterias() {
        // Simula la carga de datos desde la base de datos
        arreglo.clear()
        val ferreterias = EBaseDeDatos.tablaEntrenador?.consultarTodasLasFerreterias()
        if (ferreterias != null) {
            arreglo.addAll(ferreterias)
        }
        adaptador.notifyDataSetChanged()
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.menu, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        posicionItemSeleccionado = info.position
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_editar -> {
                editarItem()
                true
            }

            R.id.mi_eliminar -> {
                eliminarItem()
                true
            }

            R.id.mi_ver_herramienta -> {
                verHerramientas()
                true
            }

            else -> super.onContextItemSelected(item)
        }
    }

    private fun editarItem() {
        val ferreteria = arreglo[posicionItemSeleccionado]
        val intent = Intent(this, EntrenadorFerreteria::class.java).apply {
            putExtra("ID", ferreteria.idFerreteria)
            putExtra("NOMBRE", ferreteria.nombre)
            putExtra("ABIERTO", ferreteria.abierta)
        }
        startActivityForResult(intent, REQUEST_CODE_EDITAR)
    }

    private fun eliminarItem() {
        val ferreteria = arreglo[posicionItemSeleccionado]
        AlertDialog.Builder(this)
            .setTitle("Desea Eliminar?")
            .setPositiveButton("Aceptar") { _, _ ->
                EBaseDeDatos.tablaEntrenador?.eliminarEntrenadorFormulario(ferreteria.idFerreteria)
                cargarFerreterias()
                mostrarSnackbar("Ferretería eliminada")
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    private fun verHerramientas() {
        val ferreteria = arreglo[posicionItemSeleccionado]
        val intent = Intent(this, BListView_Herramienta::class.java).apply {
            putExtra("FERRETERIA_ID", ferreteria.idFerreteria)
            putExtra("FERRETERIA_NOMBRE", ferreteria.nombre)
        }
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            cargarFerreterias()
        }
    }

    private fun mostrarSnackbar(texto: String) {
        Snackbar.make(
            findViewById(R.id.id_pnl_main),
            texto,
            Snackbar.LENGTH_SHORT
        ).show()
    }

    companion object {
        private const val REQUEST_CODE_CREAR = 1
        private const val REQUEST_CODE_EDITAR = 2
    }

    fun irActividad(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }

    /*Mapa*/
    fun verMapa(ferreteria: BFerreteria) {
        val intent = Intent(this, GGoogleMapsActivity::class.java)
        intent.putExtra("FERRETERIA_NOMBRE", ferreteria.nombre)
        startActivity(intent)
    }

    fun moverEnMapa(posicion: Int) {
        val intent = Intent(this, GGoogleMapsActivity::class.java)
        intent.putExtra("POSICION", posicion)
        intent.putExtra("MOVER", true)
        startActivity(intent)
    }




}