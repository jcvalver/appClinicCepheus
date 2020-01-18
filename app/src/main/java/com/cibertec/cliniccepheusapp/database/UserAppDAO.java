package com.cibertec.cliniccepheusapp.database;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.cibertec.cliniccepheusapp.model.UserApp;

import java.util.List;

@Dao
public interface UserAppDAO {

    @Query("SELECT * FROM userapp")
    List<UserApp> listar();

    @Insert
    long insertar(UserApp userapp);

    @Update
    int actualizar(UserApp userapp);

    @Delete
    int eliminar(UserApp userapp);

    @Query("SELECT * FROM userapp WHERE id = :id")
    Cursor obtenerPorId(long id);
}
