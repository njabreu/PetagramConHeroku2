package com.example.nelson.petagram.notificaciones.adapter;

import com.example.nelson.petagram.notificaciones.ConstanteRestAPINotificaciones;
import com.example.nelson.petagram.notificaciones.EndPointsNotificaciones;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Nelson Abreu on 7/9/2016.
 */
public class RestApiAdapterNotificaciones {

    public EndPointsNotificaciones establecerConexionRestApi(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstanteRestAPINotificaciones.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(EndPointsNotificaciones.class);
    }
}
