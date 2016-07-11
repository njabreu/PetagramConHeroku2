package com.example.nelson.petagram.pojo;

/**
 * Created by Nelson on 5/14/2016.
 */
public class Mascota {

    private int id;
    private String nombre;
    private int foto;
    private int totalLikes;

    //constructor vacio
    public Mascota(){}

    public Mascota(String nombre, int foto, int totalLikes) {
        this.nombre = nombre;
        this.foto = foto;
        this.totalLikes = totalLikes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public int getTotalLikes() {
        return totalLikes;
    }

    public void setTotalLikes(int totalLikes) {
        this.totalLikes = totalLikes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
