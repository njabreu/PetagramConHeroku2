package com.example.nelson.petagram;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.nelson.petagram.adapter.MascotaAdapter;
import com.example.nelson.petagram.adapter.PageAdapter;
import com.example.nelson.petagram.fragment.PerfilFragment;
import com.example.nelson.petagram.fragment.RecyclerViewFragment;
import com.example.nelson.petagram.notificaciones.EndPointsNotificaciones;
import com.example.nelson.petagram.notificaciones.adapter.RestApiAdapterNotificaciones;
import com.example.nelson.petagram.notificaciones.model.LikeResponse;
import com.example.nelson.petagram.notificaciones.model.UsuarioResponse;
import com.example.nelson.petagram.pojo.Mascota;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    ArrayList<Mascota> mascotas;
    RecyclerView listaMascotas;

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        toolbar = (Toolbar)findViewById(R.id.toolbar);
        tabLayout= (TabLayout)findViewById(R.id.tabLayout);
        viewPager =(ViewPager)findViewById(R.id.viewPager);

        setUpViewPager(); //activa el view pager

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        if(toolbar!=null)
        {
            setSupportActionBar(toolbar);
        }
        agregarFAB();

    }

    //para agregar el floating action button
    public void agregarFAB()
    {
        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fabMiActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"Abrir la Cámara", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.mAcercaDe:
                Intent intent = new Intent(this,AcercaDeActivity.class);
                startActivity(intent);
                break;
            case R.id.mContacto:
                Intent i = new Intent(this, ContactoActivity.class);
                startActivity(i);
                break;
            case R.id.mConfigurarCuenta:
                Intent i3 = new Intent(this, ConfigurarCuenta.class);
                startActivity(i3);
                break;
            case R.id.mRecibirNotificaciones:
                lanzarDeviceID();
                break;
            case R.id.menu_action_top5:
                Intent i2= new Intent(getApplicationContext(), ListaMascotas.class);
                startActivity(i2);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_opciones, menu);
        return true;
    }


    private ArrayList<Fragment> agregarFragments()
    {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new RecyclerViewFragment());
        fragments.add(new PerfilFragment());


        return fragments;
    }
    private void setUpViewPager()
    {
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(),agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.home);
        tabLayout.getTabAt(1).setIcon(R.drawable.animal_face);
    }


    public void lanzarDeviceID(){
        String token = FirebaseInstanceId.getInstance().getToken(); //AQUI YA TENGO EL ID DEL DISPOSITIVO
        Log.d("TOKEN==", token);
        RestApiAdapterNotificaciones restApiAdapter = new RestApiAdapterNotificaciones();
        EndPointsNotificaciones endpoint = restApiAdapter.establecerConexionRestApi();
        Call<UsuarioResponse> usuarioResponseCall = endpoint.registrarUsuario(token,"njabreuv");
        usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                UsuarioResponse usuarioResponse = response.body();
                Log.d("NELSON==", usuarioResponse.getId());
                Log.d("NELSON==", usuarioResponse.getId_dispositivo());
                Log.d("NELSON==", usuarioResponse.getId_usuario_instagram());
              }
            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {

            }
        });
    }
}
