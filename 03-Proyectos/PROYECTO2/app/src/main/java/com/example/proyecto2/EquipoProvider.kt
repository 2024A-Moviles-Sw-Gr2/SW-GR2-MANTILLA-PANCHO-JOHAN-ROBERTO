package com.example.proyecto2

class EquipoProvider {
    companion object{
        val arregloEquipos = arrayListOf<EquipoModel>()

        init{
            arregloEquipos.add(
                EquipoModel("Raqueta", R.mipmap.raqueta)
            )
            arregloEquipos.add(
                EquipoModel("Pupos", R.mipmap.pupos)
            )
            arregloEquipos.add(
                EquipoModel("Pelota de Fútbol", R.mipmap.pelota_futbol)
            )
            arregloEquipos.add(
                EquipoModel("Pelota de Basquet", R.mipmap.pelota_basquet)
            )
            arregloEquipos.add(
                EquipoModel("Pelota de Volley", R.mipmap.pelota_volley)
            )
        }
    }
}