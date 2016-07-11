package com.example.nelson.petagram.restAPI.model;

import com.example.nelson.petagram.pojo.Usuario;

import java.util.ArrayList;

/**
 * Created by Nelson Abreu on 6/26/2016.
 */
public class UsuarioResponse {

    ArrayList<Usuario> usuarios;

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
