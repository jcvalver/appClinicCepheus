package com.cibertec.cliniccepheusapp.model;

public class Especialidad {

    private String codigoEspecialidad;
    private String nombreEspecialidad;


    public Especialidad(){

    }

    public Especialidad(String codigoEspecialidad, String nombreEspecialidad) {
        this.setCodigoEspecialidad(codigoEspecialidad);
        this.setNombreEspecialidad(nombreEspecialidad);
    }


    public String getCodigoEspecialidad() {
        return codigoEspecialidad;
    }

    public void setCodigoEspecialidad(String codigoEspecialidad) {
        this.codigoEspecialidad = codigoEspecialidad;
    }

    public String getNombreEspecialidad() {
        return nombreEspecialidad;
    }

    public void setNombreEspecialidad(String nombreEspecialidad) {
        this.nombreEspecialidad = nombreEspecialidad;
    }


    @Override
    public String toString() {
        return nombreEspecialidad;
    }
}
