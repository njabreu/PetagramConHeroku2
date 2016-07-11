package com.example.nelson.petagram.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.nelson.petagram.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by Nelson on 5/25/2016.
 */
public class BaseDatos extends SQLiteOpenHelper {


    private Context context;

    public BaseDatos(Context context) {
        super(context, ConstantesDB.DATABASE_NAME, null, ConstantesDB.DATABASE_VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String qryCrearTablaMascota =
                "CREATE TABLE "
                        + ConstantesDB.TABLE_MASCOTAS +" ( "
                        + ConstantesDB.TABLE_MASCOTAS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + ConstantesDB.TABLE_MASCOTAS_NOMBRE + " TEXT, "
                        + ConstantesDB.TABLE_MASCOTAS_FOTO  + " INTEGER"
                        + " )";
        String qryCrearTablaLikesMascotas =
                "CREATE TABLE "
                        + ConstantesDB.TABLE_LIKES_MASCOTAS + " ( "
                        + ConstantesDB.TABLE_LIKES_MASCOTAS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + ConstantesDB.TABLE_LIKES_MASCOTAS_ID_MASCOTAS + " INTEGER, "
                        + ConstantesDB.TABLE_LIKES_MASCOTAS_NUMERO_LIKES + " INTEGER, "
                        + " FOREIGN KEY ("+ ConstantesDB.TABLE_LIKES_MASCOTAS_ID_MASCOTAS + ") "
                        + " REFERENCES " + ConstantesDB.TABLE_MASCOTAS +" (" + ConstantesDB.TABLE_MASCOTAS_ID +") "
                        + " )";

        db.execSQL(qryCrearTablaMascota);
        db.execSQL(qryCrearTablaLikesMascotas);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST "+  ConstantesDB.TABLE_MASCOTAS);
        db.execSQL("DROP TABLE IF EXIST "+  ConstantesDB.TABLE_LIKES_MASCOTAS);
        onCreate(db);
    }


    public ArrayList<Mascota> obtenerTodosLasMascotas()
    {
        ArrayList<Mascota> mascotas = new ArrayList<>();
        String qry = "SELECT * FROM " + ConstantesDB.TABLE_MASCOTAS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(qry, null);
        while(registros.moveToNext()){
            Mascota item = new Mascota();
            item.setId(registros.getInt(0));
            item.setNombre(registros.getString(1));
            item.setFoto(registros.getInt(2));

            String qryLikes = "SELECT COUNT("+ ConstantesDB.TABLE_LIKES_MASCOTAS_NUMERO_LIKES  +") FROM "
                    + ConstantesDB.TABLE_LIKES_MASCOTAS
                    + " WHERE "
                    + ConstantesDB.TABLE_LIKES_MASCOTAS_ID_MASCOTAS +"="
                    + item.getId();
            Cursor registrosLikes = db.rawQuery(qryLikes, null);
            if(registrosLikes.moveToNext())
                item.setTotalLikes(registrosLikes.getInt(0));
            else
                item.setTotalLikes(0);

            mascotas.add(item);
        }

        db.close();
        return mascotas;
    }

    public ArrayList<Mascota> obtenerTopCincoMascotas()
    {
        ArrayList<Mascota> mascotas = new ArrayList<>();

        String qryLikes = "SELECT "
                + ConstantesDB.TABLE_LIKES_MASCOTAS_ID_MASCOTAS +", "
                + " COUNT("+ ConstantesDB.TABLE_LIKES_MASCOTAS_NUMERO_LIKES  +") "
                + " FROM "
                + ConstantesDB.TABLE_LIKES_MASCOTAS
                + " GROUP BY  " + ConstantesDB.TABLE_LIKES_MASCOTAS_ID_MASCOTAS
                + " ORDER BY COUNT("+ ConstantesDB.TABLE_LIKES_MASCOTAS_NUMERO_LIKES  +") DESC"
                + " LIMIT 5";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(qryLikes, null);
        while(registros.moveToNext()){
            Mascota item = new Mascota();
            item.setId(registros.getInt(0));
            item.setTotalLikes(registros.getInt(1));
            String qry = "SELECT "
                         + ConstantesDB.TABLE_MASCOTAS_ID + ", "
                         + ConstantesDB.TABLE_MASCOTAS_NOMBRE + ", "
                         + ConstantesDB.TABLE_MASCOTAS_FOTO
                         + " FROM " + ConstantesDB.TABLE_MASCOTAS
                         + " WHERE " + ConstantesDB.TABLE_MASCOTAS_ID +"="+item.getId();

            Cursor datos = db.rawQuery(qry, null);
            if(datos.moveToNext())
            {
                item.setNombre(datos.getString(1));
                item.setFoto(datos.getInt(2));
            }
            mascotas.add(item);
        }

        db.close();
        return mascotas;
    }


    public void insertarMascotas(ContentValues contentValues)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesDB.TABLE_MASCOTAS, null, contentValues);
        db.close();
    }

    public void insertarLikeMascotas(ContentValues contentValues)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesDB.TABLE_LIKES_MASCOTAS, null, contentValues);
        db.close();
    }



    public Integer obtenerLikesMascota(Mascota mascota){
        int likes = 0;
        String qry = "SELECT COUNT("+ ConstantesDB.TABLE_LIKES_MASCOTAS_NUMERO_LIKES  +") FROM "
                + ConstantesDB.TABLE_LIKES_MASCOTAS
                + " WHERE "
                + ConstantesDB.TABLE_LIKES_MASCOTAS_ID_MASCOTAS +"="
                + mascota.getId();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(qry, null);
        if(registros.moveToNext()){
            likes = registros.getInt(0);
        }

        return likes;
    }
}
