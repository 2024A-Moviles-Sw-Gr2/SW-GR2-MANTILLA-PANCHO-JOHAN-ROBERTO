import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter

class Archivo {
    private val dataDirectory = File("/Users/escritorio.virtual31/Documents/AplicacionesMoviles2024A/SW-GR2-MANTILLA-PANCHO-JOHAN-ROBERTO/02-Deberes/Deber-01/src/main/kotlin")
    private val herramientasFile = File(dataDirectory, "herramientas.txt")
    private val ferreteriasFile = File(dataDirectory, "ferreterias.txt")

    init {
        if (!dataDirectory.exists()) {
            dataDirectory.mkdirs()
        }
    }

    fun leerHerramientas(): List<Herramienta> {
        if (!herramientasFile.exists()) return emptyList()
        val lines = herramientasFile.readLines()
        return lines.mapNotNull { line ->
            val parts = line.split(",")
            if (parts.size == 5) {
                Herramienta(
                    parts[0].toInt(),
                    parts[1],
                    parts[2].toFloat(),
                    parts[3].toBoolean(),
                    parts[4].toLong()
                )
            } else {
                null
            }
        }
    }

    fun escribirHerramientas(herramientas: List<Herramienta>) {
        val writer = BufferedWriter(FileWriter(herramientasFile))
        for (herramienta in herramientas) {
            writer.write("${herramienta.idHerramienta},${herramienta.nombre},${herramienta.precio},${herramienta.enStock},${herramienta.codigoBarra}")
            writer.newLine()
        }
        writer.close()
    }

    fun leerFerreterias(): List<Ferreteria> {
        if (!ferreteriasFile.exists()) return emptyList()
        val lines = ferreteriasFile.readLines()
        return lines.mapNotNull { line ->
            val parts = line.split(",")
            if (parts.size == 5) {
                Ferreteria(
                    parts[0].toInt(),
                    parts[1],
                    parts[2].toBoolean(),
                    parts[3].toLong(),
                    parts[4].toFloat()
                )
            } else {
                null
            }
        }
    }

    fun escribirFerreterias(ferreterias: List<Ferreteria>) {
        val writer = BufferedWriter(FileWriter(ferreteriasFile))
        for (ferreteria in ferreterias) {
            writer.write("${ferreteria.idFerreteria},${ferreteria.nombre},${ferreteria.abierta},${ferreteria.numeroTelefono}, ${ferreteria.area}")
            writer.newLine()
        }
        writer.close()
    }
}
