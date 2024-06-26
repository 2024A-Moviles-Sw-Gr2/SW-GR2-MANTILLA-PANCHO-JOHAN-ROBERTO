data class Ferreteria(
    val idFerreteria: Int,
    val nombre: String,
    val abierta: Boolean,
    val numeroTelefono:Long,
    val area: Float
) {
    companion object {
        private val archivo = Archivo()

        fun listarFerreterias(): List<Ferreteria> {
            return archivo.leerFerreterias()
        }

        fun buscar(id: Int): Ferreteria? {
            return archivo.leerFerreterias().find { it.idFerreteria == id }
        }

        fun eliminar(id: Int) {
            val ferreterias = archivo.leerFerreterias().toMutableList()
            ferreterias.removeIf { it.idFerreteria == id }
            archivo.escribirFerreterias(ferreterias)
        }

        fun actualizar(nuevaFerreteria: Ferreteria) {
            val ferreterias = archivo.leerFerreterias().toMutableList()
            val index = ferreterias.indexOfFirst { it.idFerreteria == nuevaFerreteria.idFerreteria }
            if (index != -1) {
                ferreterias[index] = nuevaFerreteria
                archivo.escribirFerreterias(ferreterias)
            }
        }
    }

    fun agregar() {
        val ferreterias = archivo.leerFerreterias().toMutableList()
        ferreterias.add(this)
        archivo.escribirFerreterias(ferreterias)
    }


}
