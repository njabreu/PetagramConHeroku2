package com.example.nelson.petagram.restAPI.adapter;

import com.example.nelson.petagram.restAPI.ConstantesRestApi;
import com.example.nelson.petagram.restAPI.EndpointsApi;
import com.example.nelson.petagram.restAPI.deserializador.UsuarioDeserializador;
import com.example.nelson.petagram.restAPI.model.UsuarioResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Nelson Abreu on 6/26/2016.
 */
public class RestApiAdapter {

    public EndpointsApi establecerConexionRestApiInstagram(Gson gson){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(EndpointsApi.class);
    }


    //-------------------------------------------------------------
    //Asocia el objeto Usuario con json para deserializacion
    //----------------------------------------------------
    public Gson construyeGsonDeserializadorDatosUsuario()
    {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(UsuarioResponse.class, new UsuarioDeserializador());
        return gsonBuilder.create();
    }
}
