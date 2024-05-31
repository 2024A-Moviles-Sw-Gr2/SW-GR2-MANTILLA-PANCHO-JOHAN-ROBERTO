import Ferreteria.Companion.listarFerreterias
import Herramienta.Companion.listarHerramientas

fun main() {

    while (true) {
        println("-------------------------------------------------| SISTEMA DE FERRETERIAS |-------------------------------------------------| ")
        println("MENU DE OPERACIONES CRUD")
        println(
            "1. Agregar Ferreteria" + "\t2. Eliminar Ferreteria" + "\t3. Buscar Ferreteria" + "\t4. Actualizar Ferreteria"
                    + "\t5. Listar Ferreterias"
        )
        println(
            "6. Agregar Herramienta" + "\t7. Eliminar Herramienta" + "\t8. Buscar Herramienta" + "\t9. Actualizar Herramienta"
                    + "\t10. Listar Herramientas"
        )

        println("Ingrese la opción deseada: ")
        val opcion = readLine().toString().toInt()

        when (opcion) {
            1 -> {
                try {
                    crearFerreteriaDesdeTeclado().agregar()
                    println("Ferretería creada")
                } catch (e: Exception) {
                    println("No se creo la ferreteria: ${e.message}")
                }
            }

            2 -> {
                val idFerreteriaAEliminar = leerValorParaEliminar()
                try {
                    Ferreteria.eliminar(idFerreteriaAEliminar)
                    println("Ferretería eliminada")
                } catch (e: Exception) {
                    println("Ferretería no eliminada: ${e.message}")
                }
            }

            3 -> {
                val valorBusqueda = leerValorParaBuscar()
                if (Ferreteria.buscar(valorBusqueda) != null) {
                    println("Ferretería encontrada")
                } else {
                    println("No se encontró la ferretería")
                }
            }
            4 -> {
                val ferreteriaActualizada = crearFerreteriaDesdeTeclado()
                try {
                    Ferreteria.actualizar(ferreteriaActualizada)
                    println("Ferretería actualizada")
                } catch (e: Exception) {
                    println("Ferretería no actualizada: ${e.message}")
                }
            }
            5 -> {
                val ferreterias = listarFerreterias()
                if (ferreterias.isNotEmpty()) {
                    println("Listado de ferreterias:")
                    ferreterias.forEach { println(it) }
                } else {
                    println("No hay ferreterias registradas")
                }
            }
            6 -> {
                try {
                    crearHerramientaDesdeTeclado().agregar()
                    println("Herramienta creada")
                } catch (e: Exception) {
                    println("No se creo la Herramienta: ${e.message}")
                }
            }
            7 -> {
                val idHerramientaAEliminar = leerValorParaEliminar()
                try {
                    Herramienta.eliminar(idHerramientaAEliminar)
                    println("Herramienta eliminada")
                } catch (e: Exception) {
                    println("Herramienta no eliminada: ${e.message}")
                }
            }
            8 -> {
                val valorBusqueda = leerValorParaBuscar()
                if (Herramienta.buscar(valorBusqueda) != null) {
                    println("Herramienta encontrada")
                } else {
                    println("No se encontró la herramienta")
                }
            }
            9 -> {
                val herramientaActualizada = crearHerramientaDesdeTeclado()
                try {
                    Herramienta.actualizar(herramientaActualizada)
                    println("Herramienta actualizada")
                } catch (e: Exception) {
                    println("Herramienta no actualizada: ${e.message}")
                }
            }
            10 -> {
                val herramientas = listarHerramientas()
                if (herramientas.isNotEmpty()) {
                    println("Listado de herramientas:")
                    herramientas.forEach { println(it) }
                } else {
                    println("No hay herramientas registradas")
                }
            }
            // Agregar casos para otros tipos de objetos
            else -> println("Opción inválida.")
        }
    }


}

//Herramienta(1, "Martillo Pro", 20.99, true, 1.5f)
fun crearHerramientaDesdeTeclado(): Herramienta {
    while (true) {
        try {
            println("Ingrese el ID de la Herramienta:")
            val idHerramienta = readLine().toString().toInt()

            println("Ingrese el nombre de la Herramienta:")
            val nombre = readLine().toString()

            println("Ingrese el precio de la Herramienta")
            val precio = readLine().toString().toDouble()

            println("Ingrese (true/false) para indicar si la herramienta es de construccion):")
            val esHerramientaDeConstruccion = readLine().toBoolean()

            println("Ingrese el peso en metros (Ejm: 210f)")
            val pesoEnMetros = readLine().toString().toFloat()

            return Herramienta(idHerramienta, nombre, precio, esHerramientaDeConstruccion, pesoEnMetros)
        } catch (e: Exception) {
            println("Error: ${e.message}")
            println("Intente nuevamente.")
        }
    }
}

fun leerValorParaEliminar(): Int {
    println("Ingrese el ID de la ferreteria a eliminar")
    val valorAEliminar = readLine().toString().toInt()
    return valorAEliminar
}

//Ferreteria(1, "EcuaFerris", false, 89.9, 256f)
fun crearFerreteriaDesdeTeclado(): Ferreteria {
    while (true) {
        try {
            println("Ingrese el ID de la Ferreteria:")
            val idFerreteria = readLine().toString().toInt()

            println("Ingrese el nombre de la Ferreteria:")
            val nombre = readLine().toString()

            println("Ingrese (true/false) para saber si la Ferreteria tiene Proveedores")
            val tieneProveedores = readLine().toBoolean()

            println("Ingrese el porcentaje de cumplimiento de la Ferreteria:")
            val porcentajeDeCumpliento = readLine().toString().toDouble()

            println("Ingrese la capacidad en metros de la Ferreteria (Ejm: 250f)")
            val capacidadEnMetros = readLine().toString().toFloat()

            return Ferreteria(idFerreteria, nombre, tieneProveedores, porcentajeDeCumpliento, capacidadEnMetros)
        } catch (e: Exception) {
            println("Error: ${e.message}")
            println("Intente nuevamente.")
        }
    }
}


fun leerValorParaBuscar(): Int {
    println("Ingrese el ID de la ferreteria a buscar")
    val valorABuscar = readLine().toString().toInt()
    return valorABuscar
}