package com.example.nelson.petagram.db;

/**
 * Created by Nelson on 5/25/2016.
 */
public final class ConstantesDB {

    public static final String DATABASE_NAME = "Mascotas";
    public static final int DATABASE_VERSION =1;

    public static final String TABLE_MASCOTAS            = "mascota";
    public static final String TABLE_MASCOTAS_ID         = "id";
    public static final String TABLE_MASCOTAS_NOMBRE     = "nombre";
    public static final String TABLE_MASCOTAS_FOTO       = "foto";


    public static final String TABLE_LIKES_MASCOTAS                  = "mascota_likes";
    public static final String TABLE_LIKES_MASCOTAS_ID               = "id";
    public static final String TABLE_LIKES_MASCOTAS_ID_MASCOTAS      = "id_mascota";
    public static final String TABLE_LIKES_MASCOTAS_NUMERO_LIKES     = "numero_likes";
}
