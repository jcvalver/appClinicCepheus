package com.cibertec.cliniccepheusapp.database;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.cibertec.cliniccepheusapp.model.ReserveCita;

import java.util.List;


@Dao
public interface ReserveCitaDAO {

    @Query(" SELECT * FROM reservecita ")
    List<ReserveCita> listar();

    @Insert
    long insertar(ReserveCita reservecita);

    @Update
    int actualizar(ReserveCita reservecita);

    @Delete
    int eliminar(ReserveCita reservecita);

    @Query(" SELECT * FROM reservecita WHERE id = :id ")
    Cursor getxId(long id);

}
