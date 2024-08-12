package com.example.ferreteria

import android.content.DialogInterface
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class BListViewFerreteria : AppCompatActivity() {
    val arreglo = BBaseDeDatosEnMemoria.arregloBFerreteria


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val listView = findViewById<ListView>(R.id.list_view_ferreteria)
        //val listView = findViewById<ListView>(R.id.list_view_ferreteria)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arreglo
        )

        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()
        val botonCrearFerreteriaEnListView = findViewById<Button>(R.id.btn_crear_ferreteria)

        botonCrearFerreteriaEnListView.setOnClickListener {
            añadirFerreteria(adaptador)
        }

        registerForContextMenu(listView)
    }

    var posicionItemSeleccionado = -1

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        //Obtener id
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val posicion = info.position
        posicionItemSeleccionado = posicion
    }


    override fun onContextItemSelected(
        item: MenuItem
    ): Boolean {
        return when (item.itemId) {
            R.id.mi_editar -> {
                mostrarSnackBar(
                    "Editar $posicionItemSeleccionado"
                )
                return true
            }

            R.id.mi_eliminar -> {
                mostrarSnackBar(
                    "Eliminar $posicionItemSeleccionado"
                )
                abrirDialogo() //Nueva linea
                return true
            }

            else -> super.onContextItemSelected(item)
        }

    }

    fun abrirDialogo() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Desea Eliminar?")
        builder.setPositiveButton(
            "Aceptar",
            DialogInterface.OnClickListener { dialog, which ->
                eliminarFerreteriaDeLista()
                mostrarSnackBar("Ferreteria eliminada")
            }
        )
        builder.setNegativeButton("Cancelar", null)

        val dialogo = builder.create()
        dialogo.show()
    }

    fun mostrarSnackBar(texto: String) {
        val snack = Snackbar.make(
            findViewById(R.id.id_pnl_main),
            texto,
            Snackbar.LENGTH_INDEFINITE
        )
        snack.show()

    }


    fun añadirFerreteria(adaptador: ArrayAdapter<BFerreteria>) {
        arreglo.add(
            BFerreteria(4, "Ferreterias Dorado", "Si", 936845275, 1010.0f)
        )
    }

    fun eliminarFerreteriaDeLista() {

        // Verificar que la posición seleccionada esté dentro del rango del arreglo
        if (posicionItemSeleccionado >= 0 && posicionItemSeleccionado < arreglo.size) {
            val ferreteria = arreglo.removeAt(posicionItemSeleccionado)
            // Notificar al adaptador que los datos han cambiado
            //(listView.adapter as ArrayAdapter<BFerreteria>).notifyDataSetChanged()
            //mostrarSnackBar("Se eliminó la ferretería: ${ferreteria.nombre}")
        }
    }


}