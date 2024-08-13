package com.example.ferreteria

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class BListView_Herramienta : AppCompatActivity() {
    private lateinit var listView: ListView
    private lateinit var adaptador: ArrayAdapter<BHerramienta>
    private val arreglo = arrayListOf<BHerramienta>()
    private var posicionItemSeleccionado = -1
    private var ferreteriaId: Int = -1
    private lateinit var ferreteriaNombre: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blist_view_herramienta)

        ferreteriaId = intent.getIntExtra("FERRETERIA_ID", -1)
        ferreteriaNombre = intent.getStringExtra("FERRETERIA_NOMBRE") ?: "Ferretería"

        val tituloTextView = findViewById<TextView>(R.id.tv_titulo_ferreteria)
        tituloTextView.text = ferreteriaNombre

        listView = findViewById(R.id.lv_herramientas)
        adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arreglo
        )
        listView.adapter = adaptador
        cargarHerramientas()

        val botonCrear = findViewById<Button>(R.id.btn_crear_herramienta)
        botonCrear.setOnClickListener {
            val intent = Intent(this, EntrenadorHerramienta::class.java)
            intent.putExtra("FERRETERIA_ID", ferreteriaId)
            startActivityForResult(intent, REQUEST_CODE_CREAR)
        }

        registerForContextMenu(listView)
    }

    private fun cargarHerramientas() {
        arreglo.clear()
        val herramientas =
            EBaseDeDatos.tablaEntrenador?.consultarHerramientasPorFerreteria(ferreteriaId)
                ?: listOf()
        arreglo.addAll(herramientas)
        adaptador.notifyDataSetChanged()
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.menu_herramientas, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        posicionItemSeleccionado = info.position
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_editar_herramienta -> {
                editarHerramienta()
                true
            }

            R.id.mi_eliminar_herramienta -> {
                eliminarHerramienta()
                true
            }

            else -> super.onContextItemSelected(item)
        }
    }

    private fun editarHerramienta() {
        val herramienta = arreglo[posicionItemSeleccionado]
        val intent = Intent(this, EntrenadorHerramienta::class.java).apply {
            putExtra("HERRAMIENTA_ID", herramienta.id)
            putExtra("HERRAMIENTA_NOMBRE", herramienta.nombre)
            putExtra("HERRAMIENTA_PRECIO", herramienta.precio)
            putExtra("FERRETERIA_ID", ferreteriaId)
        }
        startActivityForResult(intent, REQUEST_CODE_EDITAR)
    }

    private fun eliminarHerramienta() {
        val herramienta = arreglo[posicionItemSeleccionado]
        AlertDialog.Builder(this)
            .setTitle("¿Desea eliminar esta herramienta?")
            .setPositiveButton("Aceptar") { _, _ ->
                val eliminado =
                    EBaseDeDatos.tablaEntrenador?.eliminarHerramienta(herramienta.id) ?: false
                if (eliminado) {
                    cargarHerramientas()
                    mostrarSnackbar("Herramienta eliminada")
                } else {
                    mostrarSnackbar("Error al eliminar la herramienta")
                }
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            cargarHerramientas()
        }
    }

    private fun mostrarSnackbar(texto: String) {
        Snackbar.make(
            findViewById(R.id.layout_herramientas),
            texto,
            Snackbar.LENGTH_SHORT
        ).show()
    }

    companion object {
        private const val REQUEST_CODE_CREAR = 1
        private const val REQUEST_CODE_EDITAR = 2
    }
}