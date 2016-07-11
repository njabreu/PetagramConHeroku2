package com.example.nelson.petagram;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.nelson.petagram.pojo.Usuario;
import com.example.nelson.petagram.restAPI.EndpointsApi;
import com.example.nelson.petagram.restAPI.adapter.RestApiAdapter;
import com.example.nelson.petagram.restAPI.model.UsuarioResponse;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfigurarCuenta extends AppCompatActivity {


    Button btnGuardar;
    EditText etCuentaUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar_cuenta);
        btnGuardar = (Button)findViewById(R.id.btnGuardarCuenta);
        etCuentaUsuario = (EditText)findViewById(R.id.etCuentaUsuario);

       btnGuardar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               actualizarCuenta();
           }
       });

    }


    //esto deberia cargar los datos del nuevo usuario, pero no pude hacer un endpoint con parametros a tiempo

    void actualizarCuenta()
    {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaRecent = restApiAdapter.construyeGsonDeserializadorDatosUsuario();
        EndpointsApi endpointsApi =  restApiAdapter.establecerConexionRestApiInstagram(gsonMediaRecent);
        String username = etCuentaUsuario.getText().toString();
        final Call<UsuarioResponse> usuarioResponseCall= endpointsApi.getUserInfo(username);
         usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {

                Log.i("Nelson", "Entre");
                /*UsuarioResponse usuarioResponse = response.body();
                ArrayList<Usuario> usuarios = usuarioResponse.getUsuarios();
                Log.i("NelsonUser", usuarios.get(0).getUserID());
                Log.i("NelsonFotoPerfil", usuarios.get(0).getUrlFotoPerfil());
                */

            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {
                //Log.i("Nelson", t.getMessage());
                Log.i("Nelson", "Fallo");

            }
        });

    }
    void getUserRecentMedia()
    {

    }
}
