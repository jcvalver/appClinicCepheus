package com.cibertec.cliniccepheusapp.model;

import androidx.annotation.NonNull;

public class Doctor {

    private String codigoDoctor;
    private String nombreDoctor;


    public Doctor(){

    }

    public Doctor(String codigoDoctor, String nombreDoctor) {
        this.setCodigoDoctor(codigoDoctor);
        this.setNombreDoctor(nombreDoctor);
    }


    public String getCodigoDoctor() {
        return codigoDoctor;
    }

    public void setCodigoDoctor(String codigoDoctor) {
        this.codigoDoctor = codigoDoctor;
    }

    public String getNombreDoctor() {
        return nombreDoctor;
    }

    public void setNombreDoctor(String nombreDoctor) {
        this.nombreDoctor = nombreDoctor;
    }

    @NonNull
    @Override
    public String toString() {
        return nombreDoctor;
    }
}
