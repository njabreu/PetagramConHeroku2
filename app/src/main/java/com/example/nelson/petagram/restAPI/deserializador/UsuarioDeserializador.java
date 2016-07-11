package com.example.nelson.petagram.restAPI.deserializador;

import com.example.nelson.petagram.pojo.Usuario;
import com.example.nelson.petagram.restAPI.JsonKeys;
import com.example.nelson.petagram.restAPI.model.UsuarioResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Nelson Abreu on 6/26/2016.
 */
public class UsuarioDeserializador implements JsonDeserializer<UsuarioResponse> {


    @Override
    public UsuarioResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        UsuarioResponse usuarioResponse=  gson.fromJson(json, UsuarioResponse.class);
        JsonArray usuarioResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);
        usuarioResponse.setUsuarios(deserializarUsuarioJson(usuarioResponseData));
        return usuarioResponse;
    }

    //Totam la data devuelta del json y la asocia con un objeto Contacto
    private ArrayList<Usuario> deserializarUsuarioJson(JsonArray usuarioResponseData)
    {
        ArrayList<Usuario> usuarios = new ArrayList<>();

        for (int i = 0; i < usuarioResponseData.size() ; i++)
        {
         //Objects: User, Images, Likes
            JsonObject usuarioResponseDataObject = usuarioResponseData.get(i).getAsJsonObject();

            //Likes
            JsonObject likesJson = usuarioResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_LIKES);
            int likes = likesJson.get(JsonKeys.MEDIA_LIKES_COUNT).getAsInt();

            //Imabes
            JsonObject imageJson            = usuarioResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_IMAGE);
            JsonObject stdResolutionJson    = imageJson.getAsJsonObject(JsonKeys.MEDIA_STANDARD_RESOLUTION);
            String urlFoto = stdResolutionJson.get(JsonKeys.MEDIA_URL).getAsString();

            //User
            JsonObject userJson     = usuarioResponseDataObject.getAsJsonObject(JsonKeys.USER);
            String id               = userJson.get(JsonKeys.USER_ID).getAsString();
            String nombreCompleto   = userJson.get(JsonKeys.USER_FULLNAME).getAsString();
            String username         = userJson.get(JsonKeys.USERNAME).getAsString();
            String urlFotoPerfil    = userJson.get(JsonKeys.USER_FOTO_PERFIL).getAsString();

            Usuario usuario = new Usuario(id, username, urlFotoPerfil,nombreCompleto, urlFoto, likes);
            usuarios.add(usuario);
        }

        return usuarios;
    }
}
