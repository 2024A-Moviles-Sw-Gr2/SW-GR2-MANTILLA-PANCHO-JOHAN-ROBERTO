fun main() {
    println("Bienvenido al sistema de gestión de la ferretería")

    // Ejemplo de uso de las funciones CRUD
    val herramienta1 = Herramienta(1, "Martillo", 15.99, true, 1.5f)
    herramienta1.agregar()

    val ferreteria1 = Ferreteria(1, "EcuaFerris", false, 89.9,256f)
    ferreteria1.agregar()

    val herramienta2 = Herramienta(2, "Taladro", 45.50, true, 2.0f)
    herramienta2.agregar()

    val herramientas = Herramienta.listar()
    println("Listado de herramientas: $herramientas")

    val herramientaEncontrada = Herramienta.buscar(1)
    println("Herramienta encontrada: $herramientaEncontrada")

    herramienta1.actualizar(Herramienta(1, "Martillo Pro", 20.99, true, 1.5f))

    Herramienta.eliminar(2)

    val herramientasActualizadas = Herramienta.listar()
    println("Listado actualizado de herramientas: $herramientasActualizadas")
}
