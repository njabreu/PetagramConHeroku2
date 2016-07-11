package com.example.nelson.petagram.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nelson.petagram.R;
import com.example.nelson.petagram.adapter.MascotaAdapter;
import com.example.nelson.petagram.adapter.PerfilMascotaAdapter;
import com.example.nelson.petagram.adapter.UsuarioAdapter;
import com.example.nelson.petagram.pojo.Mascota;
import com.example.nelson.petagram.pojo.Usuario;
import com.example.nelson.petagram.restAPI.EndpointsApi;
import com.example.nelson.petagram.restAPI.adapter.RestApiAdapter;
import com.example.nelson.petagram.restAPI.model.UsuarioResponse;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilFragment extends Fragment {


    ArrayList<Mascota> mascotas;
    RecyclerView rvListaMascotas;
    ImageView imgPerfil;
    TextView tvNombreMascotaPerfil;
    TextView tvUsernamePerfil;
    ArrayList<Usuario> usuarios;

    public PerfilFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_perfil, container, false);
        rvListaMascotas= (RecyclerView)v.findViewById(R.id.rvPerfilMascotaList);
        imgPerfil = (ImageView)v.findViewById(R.id.imgPerfil);
        tvNombreMascotaPerfil = (TextView) v.findViewById(R.id.tvNombreMascotaPerfil);
        tvUsernamePerfil = (TextView)v.findViewById(R.id.tvUsernamePerfil);

        obtenerDatosPerfilUsuario();
        return v;
    }

    ///Inicializa el adaptador para el recyclerview
    public void inicializarAdaptador(){
        UsuarioAdapter adapter = new UsuarioAdapter(usuarios, getActivity());
        rvListaMascotas.setAdapter(adapter);
    }



    //--------
    // Obtiene los datos basicos del usuario
    //--------------------------------------------
    public void obtenerDatosPerfilUsuario()
    {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaRecent = restApiAdapter.construyeGsonDeserializadorDatosUsuario();
        EndpointsApi endpointsApi =  restApiAdapter.establecerConexionRestApiInstagram(gsonMediaRecent);
       // final Call<UsuarioResponse> usuarioResponseCall= endpointsApi.getSelfUserInfo();

        final Call<UsuarioResponse> usuarioResponseCall= endpointsApi.getSelfUserMedia();
        usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                UsuarioResponse usuarioResponse = response.body();
                usuarios = usuarioResponse.getUsuarios();

                mostrarDatos();
                inicializarAdaptador(); //conecta los datos
            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {
                Log.i("Falló Conexión", t.getMessage());
            }
        });
    }


    //---------------
    ///Actualiza los datos del usuario o perfil seleccionado. Antes de llenar el listado
    // de medios recientes..
    //----------------------------------
    public void mostrarDatos()
    {
        //Para estas actualizaciones solo necesito un elementod el arreglo
        if(usuarios.size()>0) {
            ///la imagen del perfil
            Picasso.with(getActivity())
                    .load(usuarios.get(0).getUrlFotoPerfil())
                    .placeholder(R.drawable.hamster_3)
                    .into(imgPerfil);
            //el nombre completo del perfil
            tvNombreMascotaPerfil.setText(usuarios.get(0).getNombreCompleto());

            //el username del usuario
            tvUsernamePerfil.setText(usuarios.get(0).getUsername());

            GridLayoutManager grid = new GridLayoutManager(getActivity(),3);
            rvListaMascotas.setLayoutManager(grid);


        }
    }

}
