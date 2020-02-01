package com.cibertec.cliniccepheusapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ReserveCita implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private String paciente;
    private String codigoEspecialidad;
    private String codigoSede;
    private String fecha;
    private String codigoDoctor;
    private String codigoHorario;

    public ReserveCita(){

    }

    protected ReserveCita(Parcel in) {
        this.setId(in.readLong());
        this.setPaciente(in.readString());
        this.setCodigoEspecialidad(in.readString());
        this.setCodigoSede(in.readString());
        this.setFecha(in.readString());
        this.setCodigoDoctor(in.readString());
        this.setCodigoHorario(in.readString());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }

    public String getCodigoEspecialidad() {
        return codigoEspecialidad;
    }

    public void setCodigoEspecialidad(String codigoEspecialidad) {
        this.codigoEspecialidad = codigoEspecialidad;
    }

    public String getCodigoSede() {
        return codigoSede;
    }

    public void setCodigoSede(String codigoSede) {
        this.codigoSede = codigoSede;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCodigoDoctor() {
        return codigoDoctor;
    }

    public void setCodigoDoctor(String codigoDoctor) {
        this.codigoDoctor = codigoDoctor;
    }

    public String getCodigoHorario() {
        return codigoHorario;
    }

    public void setCodigoHorario(String codigoHorario) {
        this.codigoHorario = codigoHorario;
    }

    @NonNull
    @Override
    public String toString() {
        return "CITA"+id+": "+ paciente;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.paciente);
        dest.writeString(this.codigoEspecialidad);
        dest.writeString(this.codigoSede);
        dest.writeString(this.fecha);
        dest.writeString(this.codigoDoctor);
        dest.writeString(this.codigoHorario);

    }

    public static final Parcelable.Creator<ReserveCita> CREATOR = new Parcelable.Creator<ReserveCita>() {
        @Override
        public ReserveCita createFromParcel(Parcel source) {
            return new ReserveCita(source);
        }

        @Override
        public ReserveCita[] newArray(int size) {
            return new ReserveCita[size];
        }
    };
}
