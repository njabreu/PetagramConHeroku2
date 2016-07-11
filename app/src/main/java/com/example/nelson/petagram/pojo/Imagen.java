package com.example.nelson.petagram.pojo;

/**
 * Created by Nelson Abreu on 6/26/2016.
 */
public class Imagen {
    public String urlImagen;
    public int likes;

    public Imagen(String urlImagen, int likes) {
        this.urlImagen = urlImagen;
        this.likes = likes;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
