package com.example.nelson.petagram.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nelson.petagram.R;
import com.example.nelson.petagram.adapter.MascotaAdapter;
import com.example.nelson.petagram.adapter.PerfilMascotaAdapter;
import com.example.nelson.petagram.db.BaseDatos;
import com.example.nelson.petagram.db.ConstructorMascotas;
import com.example.nelson.petagram.pojo.Mascota;
import com.example.nelson.petagram.restAPI.EndpointsApi;
import com.example.nelson.petagram.restAPI.adapter.RestApiAdapter;
import com.example.nelson.petagram.restAPI.model.UsuarioResponse;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Nelson on 5/18/2016.
 */
public class RecyclerViewFragment extends Fragment {

    ArrayList<Mascota> mascotas;
    RecyclerView listaMascotas;
    BaseDatos db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);


        View v = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        listaMascotas= (RecyclerView)v.findViewById(R.id.rvMascotas);


        db = new BaseDatos(getContext());
        ConstructorMascotas constructorMascotas= new ConstructorMascotas(getContext());
        constructorMascotas.insertarCincoMascotas(db); //PARA INSERTAR SIEMPRE 5 MASCOTAS.
        mascotas = constructorMascotas.obtenerDatos();



        //FillMascotaList(); //carga los datos en el arreglo
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        //GridLayoutManager grid = new GridLayoutManager(this,2);
        listaMascotas.setLayoutManager(llm);

        inicializarAdaptador(); //conecta los datos

        return v;
    }

    ///Inicializa el adaptador para el recyclerview
    public void inicializarAdaptador(){
        MascotaAdapter adapter = new MascotaAdapter(mascotas, getActivity());
        listaMascotas.setAdapter(adapter);
    }

    //llena el arraylist the mascotas, cuando no se usa base de datos.
    public void FillMascotaList(){
        mascotas = new ArrayList<Mascota>();
        mascotas.add(new Mascota("Chiqui", R.drawable.perrita_1,0));
        mascotas.add(new Mascota("Ramonita",R.drawable.gatito_2,0));
        mascotas.add(new Mascota("Kim",R.drawable.hamster_3,0));
        mascotas.add(new Mascota("Pepito",R.drawable.loro_4,0));
        mascotas.add(new Mascota("Tutankamon",R.drawable.tortuga_5,0));
    }
}
