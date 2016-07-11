package com.example.nelson.petagram.adapter;


import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nelson.petagram.db.ConstructorMascotas;
import com.example.nelson.petagram.notificaciones.EndPointsNotificaciones;
import com.example.nelson.petagram.notificaciones.adapter.RestApiAdapterNotificaciones;
import com.example.nelson.petagram.notificaciones.model.LikeResponse;
import com.example.nelson.petagram.notificaciones.model.UsuarioResponse;
import com.example.nelson.petagram.pojo.Mascota;
import com.example.nelson.petagram.R;
import com.example.nelson.petagram.pojo.Usuario;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Nelson on 5/14/2016.
 */
public class MascotaAdapter extends  RecyclerView.Adapter<MascotaAdapter.MascotaViewHolder> {

    ArrayList<Mascota> mascotas;
    Activity activity;

    /* Constructor de la clase */
    public MascotaAdapter(ArrayList<Mascota> mascotas, Activity activity)
    {
        this.mascotas = mascotas;
        this.activity = activity;
    }


    @Override
    public MascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Le da vida a nuestro layout.. Asocia el layout al recycler view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota, parent, false);
        return new MascotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MascotaViewHolder holder, final int position) {
        final Mascota mascota = mascotas.get(position);
        holder.tvNombreCV.setText(mascota.getNombre());
        holder.tvTotalLikeCV.setText(String.valueOf(mascota.getTotalLikes()));
        holder.imgFotoCV.setImageResource(mascota.getFoto());

        holder.btnLikeCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //int valor = mascota.getTotalLikes()+1;
                //mascota.setTotalLikes(valor);
                Toast.makeText(activity, "Le diste like a la mascota", Toast.LENGTH_SHORT).show();
                //holder.tvTotalLikeCV.setText(String.valueOf(valor));
                ConstructorMascotas constructorMascotas = new ConstructorMascotas(activity);
                constructorMascotas.darLikeMascota(mascota);
                holder.tvTotalLikeCV.setText(String.valueOf(constructorMascotas.obtenerLikesMascota(mascota)));
                registrarLike("123412341234132","njabreuv");
            }
        });
    }

    public void registrarLike(String id_foto_instagram, String id_usuario_instagram){
        String token = FirebaseInstanceId.getInstance().getToken(); //AQUI YA TENGO EL ID DEL DISPOSITIVO
        Log.d("TOKEN==", token);
        RestApiAdapterNotificaciones restApiAdapter = new RestApiAdapterNotificaciones();
        EndPointsNotificaciones endpoint = restApiAdapter.establecerConexionRestApi();
        Call<LikeResponse> likeResponseCall = endpoint.registrarLikes(token, id_usuario_instagram, id_foto_instagram);
        likeResponseCall.enqueue(new Callback<LikeResponse>() {
            @Override
            public void onResponse(Call<LikeResponse> call, Response<LikeResponse> response) {
                LikeResponse likeResponse = response.body();
                Log.d("NELSON==", likeResponse.getId());
                Log.d("NELSON==", likeResponse.getId_dispositivo());
                Log.d("NELSON==", likeResponse.getId_usuario_instagram());
                //Envia la notificacion con los datos obtenidos a partir de este endpoint
                enviarNotificacion(likeResponse.getId(), likeResponse.getId_dispositivo());
            }
            @Override
            public void onFailure(Call<LikeResponse> call, Throwable t) {

            }
        });
    }

    ///Envia la notificaicon al dipositivo dentro del id especificado en el parametro
    public void enviarNotificacion(String id, String id_dispositivo)
    {
        RestApiAdapterNotificaciones restApiAdapterNotificaciones = new RestApiAdapterNotificaciones();
        EndPointsNotificaciones endPointsNotificaciones = restApiAdapterNotificaciones.establecerConexionRestApi();
        Call<LikeResponse> likeResponseCall = endPointsNotificaciones.enviarNotificacion(id,id_dispositivo);
        likeResponseCall.enqueue(new Callback<LikeResponse>() {
            @Override
            public void onResponse(Call<LikeResponse> call, Response<LikeResponse> response) {
                Log.d("NELSON===", "Notificacion Enviada");
            }

            @Override
            public void onFailure(Call<LikeResponse> call, Throwable t) {
                Log.d("NELSON===", t.getMessage());
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.mascotas.size();
    }

    ///La clase view holder
    public static class MascotaViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgFotoCV;
        private TextView tvNombreCV;
        private TextView tvTotalLikeCV;
        private ImageButton btnLikeCV;

        public MascotaViewHolder(View itemView) {
            super(itemView);
            imgFotoCV        = (ImageView) itemView.findViewById(R.id.imgFotoCV);
            tvNombreCV       = (TextView) itemView.findViewById(R.id.tvNombreCV);
            tvTotalLikeCV    = (TextView) itemView.findViewById(R.id.tvTotalLikeCV);
            btnLikeCV        = (ImageButton) itemView.findViewById(R.id.btnLikeCV);
        }
    }

}
