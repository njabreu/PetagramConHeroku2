package com.example.nelson.petagram;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.nelson.petagram.email.SendEmail;

public class ContactoActivity extends AppCompatActivity {

    Button btnEnviar;
    EditText etEmail;
    EditText etMensaje;
    EditText etNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);


        btnEnviar = (Button)findViewById(R.id.btnEnviar);
        etMensaje = (EditText)findViewById(R.id.etMensaje);
        etNombre = (EditText)findViewById(R.id.etNombre);
        etEmail = (EditText)findViewById(R.id.etCorreo);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });


    }

    void sendEmail()
    {
        //Getting content for email
        String email = etEmail.getText().toString().trim();
        String mensaje = etMensaje.getText().toString().trim();
        String nombre = etNombre.getText().toString().trim();
        String asunto = "Prueba desde Petagram";

        //Creating SendMail object
        SendEmail sm = new SendEmail(this, email, asunto, mensaje);

        //Executing sendmail to send email
        sm.execute();
    }

}
