package com.example.nelson.petagram.pojo;

import java.util.ArrayList;

/**
 * Created by Nelson Abreu on 6/26/2016.
 */
public class Usuario {

    private String userID;
    private String username;
    private String urlFotoPerfil;
    private String nombreCompleto;
    private String urlImagen;
    private int likes;

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public Usuario(String userID, String username, String urlFotoPerfil, String nombreCompleto, String urlImagen, int likes) {
        this.userID = userID;
        this.username = username;
        this.urlFotoPerfil = urlFotoPerfil;
        this.nombreCompleto = nombreCompleto;
        this.urlImagen = urlImagen;
        this.likes = likes;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUrlFotoPerfil() {
        return urlFotoPerfil;
    }

    public void setUrlFotoPerfil(String urlFotoPerfil) {
        this.urlFotoPerfil = urlFotoPerfil;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
}
