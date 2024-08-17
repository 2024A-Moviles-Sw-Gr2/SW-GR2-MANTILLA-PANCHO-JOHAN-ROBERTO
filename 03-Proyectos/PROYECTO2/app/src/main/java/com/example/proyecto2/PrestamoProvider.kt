package com.example.proyecto2

class PrestamoProvider {
    companion object{
        val arregloPrestamos = arrayListOf<PrestamoModel>()

        init{
            arregloPrestamos.add(
                PrestamoModel("17/08/2024", "De 8:00AM a 10:00AM")
            )
            arregloPrestamos.add(
                PrestamoModel("12/08/2024", "De 13:00PM a 14:00PM")
            )
        }
    }
}