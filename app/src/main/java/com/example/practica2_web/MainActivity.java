package com.example.practica2_web;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

import WebService.Asynchtask;
import WebService.WebService;

public class MainActivity extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btLogin(View view){
        EditText Usuario = findViewById(R.id.txtUsername);
        EditText Contraseña = findViewById(R.id.txtPassword);
        String Nombre;
        String Password;
        Nombre = Usuario.getText().toString();
        Password = Contraseña.getText().toString();

        //Código para conectarnos a Internet
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService(
                "https://revistas.uteq.edu.ec/ws/login.php?usr="
                        + Nombre+"&pass="+Password, datos,
                MainActivity.this, MainActivity.this);
        ws.execute("GET");

    }

    @Override
    public void processFinish(String result) throws JSONException {

        if(result.equals("Login Correcto!")){
            Intent intent = new Intent(MainActivity.this,MainActivity2.class);
            startActivity(intent);

        }else {
            TextView txtMensaje = (TextView) findViewById(R.id.txtMensaje);
            txtMensaje.setText("Error al iniciar sesión : "+result);
        }


    }
}