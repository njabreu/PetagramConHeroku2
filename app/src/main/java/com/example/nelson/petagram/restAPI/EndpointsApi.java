package com.example.nelson.petagram.restAPI;

import com.example.nelson.petagram.pojo.Mascota;
import com.example.nelson.petagram.restAPI.model.MascotaResponse;
import com.example.nelson.petagram.restAPI.model.UsuarioResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Nelson Abreu on 6/26/2016.
 */
public interface EndpointsApi {

    @GET("https://api.instagram.com/v1/users/self/?access_token=1602197993.5e37335.328fbd261fdd4765ad3c74f8759724a7")
    Call<UsuarioResponse> getSelfUserInfo();

    //La data de mi propio usuario
    @GET("https://api.instagram.com/v1/users/self/media/recent/?access_token=3461966779.0fac6d8.9012e5ec09b64326996b0778bddb5fab")
    Call<UsuarioResponse> getSelfUserMedia();

    @GET("https://api.instagram.com/v1/users/search?access_token=1602197993.5e37335.328fbd261fdd4765ad3c74f8759724a7")
    Call<UsuarioResponse> getUserInfo(@Query("q") String username);

    @GET("https://api.instagram.com/v1/users/{user-id}/media/recent/?access_token=ACCESS-TOKEN\n")
    Call<UsuarioResponse> getUserMedia(@Path("user-id") String userID);


}
