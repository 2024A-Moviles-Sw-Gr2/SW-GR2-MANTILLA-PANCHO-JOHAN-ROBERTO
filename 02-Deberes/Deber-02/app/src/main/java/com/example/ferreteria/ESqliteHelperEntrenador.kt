package com.example.ferreteria

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ESqliteHelperEntrenador(
    contexto: Context? // this
) : SQLiteOpenHelper(
    contexto,
    "movilesDeber",
    null,
    2
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptSQLCrearTablaEntrenador =
            """
                CREATE TABLE FERRETERIA(
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombre VARCHAR(50),
                    abierta VARCHAR(50)
                )
            """.trimIndent()
        db?.execSQL(scriptSQLCrearTablaEntrenador)

        val scriptSQLCrearTablaHerramienta =
            """
                CREATE TABLE HERRAMIENTA(
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombre VARCHAR(50),
                    precio REAL,
                    ferreteria_id INTEGER,
                    FOREIGN KEY(ferreteria_id) REFERENCES FERRETERIA(id)
                )
            """.trimIndent()
        db?.execSQL(scriptSQLCrearTablaHerramienta)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS HERRAMIENTA")
        db?.execSQL("DROP TABLE IF EXISTS FERRETERIA")
        onCreate(db)
    }

    fun crearEntrenador(
        nombre: String,
        abierta: String
    ): Boolean {
        val basedatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("nombre", nombre)
        valoresAGuardar.put("abierta", abierta)
        val resultadoGuardar = basedatosEscritura
            .insert(
                "FERRETERIA", // nombre tabla
                null,
                valoresAGuardar // valores
            )
        basedatosEscritura.close()
        return if (resultadoGuardar.toInt() == -1) false else true
    }

    fun eliminarEntrenadorFormulario(id: Int): Boolean {
        val conexionEscritura = writableDatabase
        // Consulta SQL: where ... ID=? AND NOMBRE=?  [?=1, ?=2]
        val parametrosConsultaDelete = arrayOf(id.toString())
        val resultadoEliminacion = conexionEscritura
            .delete(
                "FERRETERIA",
                "id=?",
                parametrosConsultaDelete
            )
        conexionEscritura.close()
        return if (resultadoEliminacion.toInt() == -1) false else true
    }

    fun actualizarEntrenadorFormulario(
        nombre: String, abierta: String, id: Int
    ): Boolean {
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("nombre", nombre)
        valoresAActualizar.put("abierta", abierta)
        // where: .....
        val parametrosConsultaActualizar = arrayOf(id.toString())
        val resultadoActualizacion = conexionEscritura
            .update(
                "FERRETERIA",
                valoresAActualizar, // nombre= Adrian, descripcion=B
                "id=?", // id=1
                parametrosConsultaActualizar // [1]
            )
        conexionEscritura.close()
        return if (resultadoActualizacion.toInt() == -1) false else true
    }

    fun consultarEntrenadorPorID(id: Int): BFerreteria? {
        val baseDatosLectura = readableDatabase
        val scriptConsultaLectura = """
            SELECT * FROM FERRETERIA WHERE ID = ?
        """.trimIndent()
        val arregloParametrosConsultaLectura = arrayOf(
            id.toString()
        )
        val resultadoConsultaLectura = baseDatosLectura
            .rawQuery(
                scriptConsultaLectura,
                arregloParametrosConsultaLectura
            )

        // Logica busqueda
        // Recibimos un arreglo (puede ser nulo)
        // Llenar un arreglo de Entrenadores
        // Si esta vacio, el arreglo no tiene elementos
        val existeAlMenosUno = resultadoConsultaLectura
            .moveToFirst()
        val arregloRespuesta = arrayListOf<BFerreteria>()
        if (existeAlMenosUno) {
            do {
                val entrenador = BFerreteria(
                    resultadoConsultaLectura.getInt(0),
                    resultadoConsultaLectura.getString(1),
                    resultadoConsultaLectura.getString(2)
                )
                arregloRespuesta.add(entrenador)
            } while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        // ESqliteHelperEntrenador.consultarEntrenadorPorID
        return if (arregloRespuesta.size > 0) arregloRespuesta[0] else null
    }

    fun consultarTodasLasFerreterias(): List<BFerreteria> {
        val listaFerreterias = mutableListOf<BFerreteria>()
        val baseDatosLectura = readableDatabase
        val scriptConsultaLectura = "SELECT * FROM FERRETERIA"
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(scriptConsultaLectura, null)

        if (resultadoConsultaLectura.moveToFirst()) {
            do {
                val ferreteria = BFerreteria(
                    resultadoConsultaLectura.getInt(0),
                    resultadoConsultaLectura.getString(1),
                    resultadoConsultaLectura.getString(2)
                )
                listaFerreterias.add(ferreteria)
            } while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return listaFerreterias
    }


    fun crearHerramienta(nombre: String, precio: Double, ferreteriaId: Int): Boolean {
        val basedatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("nombre", nombre)
        valoresAGuardar.put("precio", precio)
        valoresAGuardar.put("ferreteria_id", ferreteriaId)
        val resultadoGuardar = basedatosEscritura
            .insert(
                "HERRAMIENTA",
                null,
                valoresAGuardar
            )
        basedatosEscritura.close()
        return resultadoGuardar.toInt() != -1
    }

    fun eliminarHerramienta(id: Int): Boolean {
        val conexionEscritura = writableDatabase
        val parametrosConsultaDelete = arrayOf(id.toString())
        val resultadoEliminacion = conexionEscritura
            .delete(
                "HERRAMIENTA",
                "id=?",
                parametrosConsultaDelete
            )
        conexionEscritura.close()
        return resultadoEliminacion != -1
    }

    fun actualizarHerramienta(id: Int, nombre: String, precio: Double): Boolean {
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("nombre", nombre)
        valoresAActualizar.put("precio", precio)
        val parametrosConsultaActualizar = arrayOf(id.toString())
        val resultadoActualizacion = conexionEscritura
            .update(
                "HERRAMIENTA",
                valoresAActualizar,
                "id=?",
                parametrosConsultaActualizar
            )
        conexionEscritura.close()
        return resultadoActualizacion != -1
    }

    fun consultarHerramientasPorFerreteria(ferreteriaId: Int): List<BHerramienta> {
        val listaHerramientas = mutableListOf<BHerramienta>()
        val baseDatosLectura = readableDatabase
        val scriptConsultaLectura = "SELECT * FROM HERRAMIENTA WHERE ferreteria_id = ?"
        val parametrosConsulta = arrayOf(ferreteriaId.toString())
        val resultadoConsultaLectura =
            baseDatosLectura.rawQuery(scriptConsultaLectura, parametrosConsulta)

        if (resultadoConsultaLectura.moveToFirst()) {
            do {
                val herramienta = BHerramienta(
                    resultadoConsultaLectura.getInt(0),
                    resultadoConsultaLectura.getString(1),
                    resultadoConsultaLectura.getDouble(2),
                    resultadoConsultaLectura.getInt(3)
                )
                listaHerramientas.add(herramienta)
            } while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return listaHerramientas
    }


}