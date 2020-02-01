package com.cibertec.cliniccepheusapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.cibertec.cliniccepheusapp.model.ReserveCita;
import com.cibertec.cliniccepheusapp.model.User;

@Database(entities = {User.class, ReserveCita.class}, version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context, AppDatabase.class, "CLINICPDB1").allowMainThreadQueries().build();
        }
        return instance;
    }

    public abstract UserDAO userDao();

    public abstract ReserveCitaDAO reserveCitaDAO();
}
