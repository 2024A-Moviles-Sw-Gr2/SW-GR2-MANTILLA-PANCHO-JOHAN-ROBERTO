package com.example.ferreteria

class BBaseDeDatosEnMemoria {

    companion object {
        val arregloBFerreteria = arrayListOf<BFerreteria>()
        //val arregloBHerramienta = arrayListOf<BHerramienta>()

        init {
            arregloBFerreteria
                .add(
                    BFerreteria(1, "EcuaFerris", "Si", 983706479, 215.0f)
                )

            arregloBFerreteria
                .add(
                    BFerreteria(2, "Mega Kywi", "No", 962527296, 2505.0f)
                )

            arregloBFerreteria
                .add(
                    BFerreteria(3, "EL" +
                            "EL FERRETERO", "No", 962527111, 1303.0f)
                )

        }
    }

}