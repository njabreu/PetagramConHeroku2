package com.example.nelson.petagram.db;

import android.content.ContentValues;
import android.content.Context;

import com.example.nelson.petagram.R;
import com.example.nelson.petagram.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by Nelson on 5/25/2016.
 */
public class ConstructorMascotas {


    private static final Integer LIKE = 1;
    private Context context;
    public ConstructorMascotas(Context context){
        this.context = context;
    }

    public ArrayList<Mascota> obtenerDatos()
    {
        BaseDatos db = new BaseDatos(context);
        return  db.obtenerTodosLasMascotas();
    }

    public void insertarCincoMascotas(BaseDatos db)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesDB.TABLE_MASCOTAS_NOMBRE, "Missy");
        contentValues.put(ConstantesDB.TABLE_MASCOTAS_FOTO, R.drawable.gatito_2);
        db.insertarMascotas(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesDB.TABLE_MASCOTAS_NOMBRE, "Miguelito");
        contentValues.put(ConstantesDB.TABLE_MASCOTAS_FOTO, R.drawable.tortuga_5);
        db.insertarMascotas(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesDB.TABLE_MASCOTAS_NOMBRE, "Ham");
        contentValues.put(ConstantesDB.TABLE_MASCOTAS_FOTO, R.drawable.hamster_3);
        db.insertarMascotas(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesDB.TABLE_MASCOTAS_NOMBRE, "Chiqui");
        contentValues.put(ConstantesDB.TABLE_MASCOTAS_FOTO, R.drawable.perrita_1);
        db.insertarMascotas(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesDB.TABLE_MASCOTAS_NOMBRE, "Pepito");
        contentValues.put(ConstantesDB.TABLE_MASCOTAS_FOTO, R.drawable.loro_4);
        db.insertarMascotas(contentValues);

        db.close();
    }

    public void darLikeMascota(Mascota mascota){
        BaseDatos db = new BaseDatos(context);
        ContentValues values = new ContentValues();
        values.put(ConstantesDB.TABLE_LIKES_MASCOTAS_ID_MASCOTAS, mascota.getId());
        values.put(ConstantesDB.TABLE_LIKES_MASCOTAS_NUMERO_LIKES, LIKE);
        db.insertarLikeMascotas(values);
    }

    public int obtenerLikesMascota(Mascota mascota){
        BaseDatos db = new BaseDatos(context);
        return db.obtenerLikesMascota(mascota);
    }

    public ArrayList<Mascota> obtenerTopCincoMascotas(){
        BaseDatos db = new BaseDatos(context);
        return db.obtenerTopCincoMascotas();
    }

}
