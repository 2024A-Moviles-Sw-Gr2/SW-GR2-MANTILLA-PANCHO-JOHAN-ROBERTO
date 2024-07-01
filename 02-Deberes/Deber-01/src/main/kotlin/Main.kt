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
        val opcionDeMenu = readLine().toString().toInt()

        when (opcionDeMenu) {
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
                val idFerreteriaABuscar = leerValorParaBuscar()
                if (Ferreteria.buscar(idFerreteriaABuscar) != null) {
                    println("Ferretería encontrada")
                } else {
                    println("No se encontró la ferretería")
                }
            }
            4 -> {
                val nuevosDatosFerreteria = crearFerreteriaDesdeTeclado()
                try {
                    Ferreteria.actualizar(nuevosDatosFerreteria)
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
                val idHerramientaABuscar = leerValorParaBuscar()
                if (Herramienta.buscar(idHerramientaABuscar) != null) {
                    println("Herramienta encontrada")
                } else {
                    println("No se encontró la herramienta")
                }
            }
            9 -> {
                val nuevosDatosHerramienta = crearHerramientaDesdeTeclado()
                try {
                    Herramienta.actualizar(nuevosDatosHerramienta)
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

fun crearHerramientaDesdeTeclado(): Herramienta {
    while (true) {
        try {
            println("Ingrese el ID de la Herramienta:")
            val idHerramienta = readLine().toString().toInt()

            println("Ingrese el nombre de la Herramienta:")
            val nombre = readLine().toString()

            println("Ingrese el precio de la Herramienta:")
            val precio = readLine().toString().toFloat()
            if (precio <= 0) {
                throw IllegalArgumentException("El precio debe ser un número positivo.")
            }

            println("Ingrese (true/false) para indicar si la herramienta está en stock:")
            val enStock = readLine().toString().toBooleanStrictOrNull()
                ?: throw IllegalArgumentException("Valor inválido para 'en stock'. Debe ser 'true' o 'false'.")

            println("Ingrese el código de barras (13 dígitos):")
            val codigoBarra = readLine().toString().toLong()
            if (codigoBarra.toString().length != 13) {
                throw IllegalArgumentException("El código de barras debe tener 13 dígitos.")
            }

            val nuevaHerramienta = Herramienta(idHerramienta, nombre, precio, enStock, codigoBarra)
            if (!validarHerramienta(nuevaHerramienta)) {
                throw IllegalArgumentException("Datos inválidos para la herramienta.")
            }
            return nuevaHerramienta
        } catch (e: Exception) {
            println("Error: ${e.message}")
            println("Intente nuevamente.")
        }
    }
}

private fun validarHerramienta(herramienta: Herramienta): Boolean {
    if (herramienta.nombre.isBlank()) {
        println("El nombre no puede estar vacío.")
        return false
    }
    if (herramienta.precio <= 0) {
        println("El precio debe ser un número positivo.")
        return false
    }
    if (herramienta.codigoBarra.toString().length != 13) {
        println("El código de barras debe tener 13 dígitos.")
        return false
    }
    return true
}

fun String.toBooleanStrictOrNull(): Boolean? {
    return when {
        this.equals("true", ignoreCase = true) -> true
        this.equals("false", ignoreCase = true) -> false
        else -> null
    }
}


fun leerValorParaEliminar(): Int {
    println("Ingrese el ID de la ferreteria a eliminar")
    val valorAEliminar = readLine().toString().toInt()
    return valorAEliminar
}

fun crearFerreteriaDesdeTeclado(): Ferreteria {
    while (true) {
        try {
            println("Ingrese el ID de la Ferreteria:")
            val idFerreteria = readLine().toString().toInt()

            println("Ingrese el nombre de la Ferreteria:")
            val nombre = readLine().toString()

            println("Ingrese (true/false) para saber si la Ferreteria está abierta:")
            val abierta = readLine().toString().toBooleanStrictOrNull()
                ?: throw IllegalArgumentException("Valor inválido para 'abierta'. Debe ser 'true' o 'false'.")

            println("Ingrese el número de teléfono de la Ferreteria (10 dígitos):")
            val numeroTelefono = readLine().toString().toLong()
            if (numeroTelefono.toString().length != 10) {
                throw IllegalArgumentException("El número de teléfono debe tener 10 dígitos.")
            }

            println("Ingrese el area en metros de la Ferreteria (Ejm: 25.1f):")
            val area = readLine().toString().toFloat()
            if (area <= 0) {
                throw IllegalArgumentException("El área debe ser un número positivo.")
            }

            val nuevaFerreteria = Ferreteria(idFerreteria, nombre, abierta, numeroTelefono, area)
            if (!validarFerreteria(nuevaFerreteria)) {
                throw IllegalArgumentException("Datos inválidos para la ferretería.")
            }
            return nuevaFerreteria
        } catch (e: Exception) {
            println("Error: ${e.message}")
            println("Intente nuevamente.")
        }
    }
}

private fun validarFerreteria(ferreteria: Ferreteria): Boolean {
    if (ferreteria.nombre.isBlank()) {
        println("El nombre no puede estar vacío.")
        return false
    }
    if (ferreteria.numeroTelefono.toString().length != 10) {
        println("El número de teléfono debe tener 10 dígitos.")
        return false
    }
    if (ferreteria.area <= 0) {
        println("El área debe ser un número positivo.")
        return false
    }
    return true
}


fun leerValorParaBuscar(): Int {
    println("Ingrese el ID de la ferreteria a buscar")
    val valorABuscar = readLine().toString().toInt()
    return valorABuscar
}