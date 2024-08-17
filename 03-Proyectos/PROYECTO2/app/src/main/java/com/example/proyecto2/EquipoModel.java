package com.example.proyecto2;

public class EquipoModel {
    String nombreEquipo;
    int imagenId;

    public EquipoModel(String nombreEquipo, int imagenRuta) {
        this.nombreEquipo = nombreEquipo;
        this.imagenId = imagenRuta;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public int getImagenId() {
        return imagenId;
    }
}
