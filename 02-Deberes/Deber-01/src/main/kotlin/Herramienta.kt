data class Herramienta(
    val idHerramienta: Int,
    val nombre: String,
    val precio: Double,
    val esHerramientaDeConstruccion: Boolean,
    val pesoEnMetros: Float
) {
    companion object {
        private val archivo = Archivo()

        fun listar(): List<Herramienta> {
            return archivo.leerHerramientas()
        }

        fun buscar(id: Int): Herramienta? {
            return archivo.leerHerramientas().find { it.idHerramienta == id }
        }

        fun eliminar(id: Int) {
            val herramientas = archivo.leerHerramientas().toMutableList()
            herramientas.removeIf { it.idHerramienta == id }
            archivo.escribirHerramientas(herramientas)
        }
    }

    fun agregar() {
        val herramientas = archivo.leerHerramientas().toMutableList()
        herramientas.add(this)
        archivo.escribirHerramientas(herramientas)
    }

    fun actualizar(nuevaHerramienta: Herramienta) {
        val herramientas = archivo.leerHerramientas().toMutableList()
        val index = herramientas.indexOfFirst { it.idHerramienta == nuevaHerramienta.idHerramienta }
        if (index != -1) {
            herramientas[index] = nuevaHerramienta
            archivo.escribirHerramientas(herramientas)
        }
    }
}
