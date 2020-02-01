package com.cibertec.cliniccepheusapp.model;

import androidx.annotation.NonNull;

public class Sede {

    private String codigoSede;
    private String nombreSede;

    public Sede(){

    }

    public Sede(String codigoSede, String nombreSede) {
        this.setCodigoSede(codigoSede);
        this.setNombreSede(nombreSede);
    }


    public String getCodigoSede() {
        return codigoSede;
    }

    public void setCodigoSede(String codigoSede) {
        this.codigoSede = codigoSede;
    }

    public String getNombreSede() {
        return nombreSede;
    }

    public void setNombreSede(String nombreSede) {
        this.nombreSede = nombreSede;
    }

    @NonNull
    @Override
    public String toString() {
        return nombreSede;
    }
}
