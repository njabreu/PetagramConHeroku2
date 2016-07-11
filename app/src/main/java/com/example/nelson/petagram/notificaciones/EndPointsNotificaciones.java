package com.example.nelson.petagram.notificaciones;

import com.example.nelson.petagram.notificaciones.model.LikeResponse;
import com.example.nelson.petagram.notificaciones.model.UsuarioResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Nelson Abreu on 7/9/2016.
 */
public interface EndPointsNotificaciones {
    @FormUrlEncoded
    @POST(ConstanteRestAPINotificaciones.KEY_POST_ID_TOKEN)
    Call<UsuarioResponse> registrarUsuario(@Field("id_dispositivo") String id_dispositivo, @Field("id_usuario_instagram") String id_usuario_instagram);


    @FormUrlEncoded
    @POST(ConstanteRestAPINotificaciones.KEY_REGISTRAR_LIKES)
    Call<LikeResponse> registrarLikes(@Field("id_dispositivo") String id_dispositivo, @Field("id_usuario_instagram") String id_usuario_instagram, @Field("id_foto_instagram") String id_foto_instagram);

    @GET(ConstanteRestAPINotificaciones.KEY_ENVIAR_NOTIFICACION)
    Call <LikeResponse> enviarNotificacion(@Path("id") String id, @Path("id_dispositivo") String id_dispositivo);

}
