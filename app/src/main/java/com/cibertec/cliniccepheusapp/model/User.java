package com.cibertec.cliniccepheusapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private String fullName;
    private String email;
    private String documentDni;
    private String password;

    public User(){

    }

    protected User(Parcel in) {
        this.setId(in.readLong());
        this.setFullName(in.readString());
        this.setEmail(in.readString());
        this.setDocumentDni(in.readString());
        this.setPassword(in.readString());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDocumentDni() {
        return documentDni;
    }

    public void setDocumentDni(String documentDni) {
        this.documentDni = documentDni;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.fullName);
        dest.writeString(this.email);
        dest.writeString(this.documentDni);
        dest.writeString(this.password);
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
