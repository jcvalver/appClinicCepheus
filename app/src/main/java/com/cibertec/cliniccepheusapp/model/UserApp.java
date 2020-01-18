package com.cibertec.cliniccepheusapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UserApp implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private String fullName;
    private String email;
    private String documentDni;
    private String password;

    public  UserApp(){

    }

    protected UserApp(Parcel in) {
        this.id = in.readLong();
        this.fullName = in.readString();
        this.email = in.readString();
        this.documentDni = in.readString();
        this.password = in.readString();
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NonNull
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

    public static final Parcelable.Creator<UserApp> CREATOR = new Parcelable.Creator<UserApp>() {
        @Override
        public UserApp createFromParcel(Parcel source) {
            return new UserApp(source);
        }

        @Override
        public UserApp[] newArray(int size) {
            return new UserApp[size];
        }
    };
}
