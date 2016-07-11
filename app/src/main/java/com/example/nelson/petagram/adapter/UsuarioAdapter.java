package com.example.nelson.petagram.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nelson.petagram.R;
import com.example.nelson.petagram.pojo.Mascota;
import com.example.nelson.petagram.pojo.Usuario;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Nelson Abreu on 6/26/2016.
 */
public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioAdapter.UsuarioViewHolder> {

    ArrayList<Usuario> usuarios;
    Activity activity;

    public UsuarioAdapter(ArrayList<Usuario> usuarios, Activity activity)
    {
        this.usuarios = usuarios;
        this.activity = activity;
    }



    @Override
    public UsuarioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_fotos_mascota, parent, false);
        return new UsuarioViewHolder(v);
    }

    @Override
    public void onBindViewHolder(UsuarioViewHolder holder, int position) {
        final Usuario usuario = usuarios.get(position);
        holder.tvTotalLikePerfil.setText(String.valueOf(usuario.getLikes()));
        Picasso.with(activity)
                .load(usuario.getUrlImagen())
                .placeholder(R.drawable.hamster_3)
                .into(holder.imgFotoMascota);
    }

    @Override
    public int getItemCount() {
        return usuarios.size();
    }

    public static class UsuarioViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgFotoMascota;
        private TextView tvTotalLikePerfil;

        public UsuarioViewHolder(View itemView) {
            super(itemView);

            imgFotoMascota        = (ImageView)itemView.findViewById(R.id.imgFotoMascota);
            tvTotalLikePerfil     = (TextView) itemView.findViewById(R.id.tvTotalLikePerfil);
        }
    }
}
