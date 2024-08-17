package com.example.proyecto2;

public class PrestamoModel {

    String fecha;
    String horario;

    public PrestamoModel(String fecha, String horario) {
        this.fecha = fecha;
        this.horario = horario;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHorario() {
        return horario;
    }
}
