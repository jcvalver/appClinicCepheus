package com.cibertec.cliniccepheusapp.database;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.cibertec.cliniccepheusapp.model.User;

import java.util.List;

@Dao
public interface UserDAO {

    @Query(" SELECT * FROM user ")
    List<User> listar();

    @Insert
    long insertar(User user);

    @Update
    int actualizar(User user);

    @Delete
    int eliminar(User user);

    @Query(" SELECT * FROM user WHERE id = :id ")
    Cursor getxId(long id);

    @Query(" SELECT * FROM user WHERE documentDni = :username AND password=:password")
    User getxUser(String username,String password);
}
