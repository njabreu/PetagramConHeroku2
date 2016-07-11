package com.example.nelson.petagram.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nelson.petagram.R;
import com.example.nelson.petagram.pojo.Mascota;
import com.example.nelson.petagram.pojo.Usuario;

import java.util.ArrayList;

/**
 * Created by Nelson on 5/19/2016.
 */
public  class PerfilMascotaAdapter  extends RecyclerView.Adapter<PerfilMascotaAdapter.PerfilMascotaViewHolder> {

    ArrayList<Mascota> mascotas;
    Activity activity;

    public PerfilMascotaAdapter(ArrayList<Mascota> mascotas, Activity activity)
    {
        this.mascotas = mascotas;
        this.activity = activity;
    }

    @Override
    public PerfilMascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_fotos_mascota, parent, false);
        return new PerfilMascotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PerfilMascotaViewHolder holder, final int position) {
        final Mascota mascota = mascotas.get(position);
        holder.tvTotalLikePerfil.setText(String.valueOf(mascota.getTotalLikes()));
        holder.imgFotoMascota.setImageResource(mascota.getFoto());
    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class PerfilMascotaViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgFotoMascota;
        private TextView tvNombreMascotaPerfil;
        private TextView tvTotalLikePerfil;

        public PerfilMascotaViewHolder(View itemView) {
            super(itemView);
            imgFotoMascota        = (ImageView)itemView.findViewById(R.id.imgFotoMascota);
            tvNombreMascotaPerfil = (TextView) itemView.findViewById(R.id.tvNombreMascotaPerfil);
            tvTotalLikePerfil     = (TextView) itemView.findViewById(R.id.tvTotalLikePerfil);
        }
    }

}
