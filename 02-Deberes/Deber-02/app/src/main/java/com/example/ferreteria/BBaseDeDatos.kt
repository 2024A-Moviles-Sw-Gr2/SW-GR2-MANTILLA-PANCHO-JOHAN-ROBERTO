package com.example.ferreteria

import android.content.Context

class EBaseDeDatos {
    companion object {
        var tablaEntrenador: ESqliteHelperEntrenador? = null

        fun inicializarBD(contexto: Context) {
            if (tablaEntrenador == null) {
                tablaEntrenador = ESqliteHelperEntrenador(contexto)
            }
        }
    }
}