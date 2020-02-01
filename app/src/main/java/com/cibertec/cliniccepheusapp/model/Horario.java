package com.cibertec.cliniccepheusapp.model;

import androidx.annotation.NonNull;

public class Horario {

    private String codigoHorario;
    private String descripcionHorario;

    public Horario(){

    }

    public Horario(String codigoHorario, String descripcionHorario) {
        this.setCodigoHorario(codigoHorario);
        this.setDescripcionHorario(descripcionHorario);
    }


    public String getCodigoHorario() {
        return codigoHorario;
    }

    public void setCodigoHorario(String codigoHorario) {
        this.codigoHorario = codigoHorario;
    }

    public String getDescripcionHorario() {
        return descripcionHorario;
    }

    public void setDescripcionHorario(String descripcionHorario) {
        this.descripcionHorario = descripcionHorario;
    }

    @NonNull
    @Override
    public String toString() {
        return descripcionHorario;
    }
}
