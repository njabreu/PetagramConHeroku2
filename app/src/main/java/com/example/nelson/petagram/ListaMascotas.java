package com.example.nelson.petagram;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.nelson.petagram.adapter.MascotaAdapter;
import com.example.nelson.petagram.db.BaseDatos;
import com.example.nelson.petagram.db.ConstructorMascotas;
import com.example.nelson.petagram.pojo.Mascota;

import java.util.ArrayList;

public class ListaMascotas extends AppCompatActivity {

    ArrayList<Mascota> mascotas;
    RecyclerView listaMascotas;
    BaseDatos db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_mascotas);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //LLenar los cardview y presentarlos.. se piden valores fijos
        //FillMascotaList();

        db = new BaseDatos(this);
        ConstructorMascotas constructorMascotas= new ConstructorMascotas(this);
        mascotas = constructorMascotas.obtenerTopCincoMascotas();


        listaMascotas = (RecyclerView)findViewById(R.id.rvMascotasList);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listaMascotas.setLayoutManager(llm);
        inicializarAdaptador();

    }
    ///Inicializa el adaptador para el recyclerview
    public void inicializarAdaptador(){
        MascotaAdapter adapter = new MascotaAdapter(mascotas, this);
        listaMascotas.setAdapter(adapter);
    }

    //llena el arraylist the mascotas
    public void FillMascotaList(){
        mascotas = new ArrayList<Mascota>();
        mascotas.add(new Mascota("Pepito",R.drawable.loro_4,5));
        mascotas.add(new Mascota("Tutankamon",R.drawable.tortuga_5,4));
        mascotas.add(new Mascota("Chiqui",R.drawable.perrita_1,2));
        mascotas.add(new Mascota("Ramonita",R.drawable.gatito_2,1));
        mascotas.add(new Mascota("Kim",R.drawable.hamster_3,1));

    }

}
