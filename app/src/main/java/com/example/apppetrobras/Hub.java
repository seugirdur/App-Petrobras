package com.example.apppetrobras;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.Navigations.Tabs;
import com.example.apppetrobras.Activities.Ajuda;
import com.example.apppetrobras.Activities.Cadastro;
import com.example.apppetrobras.Activities.Login;
import com.example.apppetrobras.Activities.Perfil;
import com.example.apppetrobras.Activities.Solucoes;
import com.example.apppetrobras.Activities.Relatorio;
import com.example.apppetrobras.Activities.Passos;
import com.example.apppetrobras.Activities.SplashScreen;
import com.example.apppetrobras.Activities.Inicio;

public class Hub extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_hub);
    }

    public void rediriciona0(View view){
        Intent intent = new Intent(Hub.this, Inicio.class);
        startActivity(intent);
        finish();
    }

    public void rediriciona1(View view){
        Intent intent = new Intent(Hub.this, Login.class);
        startActivity(intent);
        finish();
    }

    public void rediriciona2(View view){
        Intent intent = new Intent(Hub.this, Cadastro.class);
        startActivity(intent);
        finish();
    }

    public void rediriciona3(View view){
        Intent intent = new Intent(Hub.this, Tabs.class);
        startActivity(intent);
        finish();
    }

    public void rediriciona4(View view){
        Intent intent = new Intent(Hub.this, Solucoes.class);
        startActivity(intent);
        finish();
    }

    public void rediriciona5(View view){
        Intent intent = new Intent(Hub.this, Relatorio.class);
        startActivity(intent);
        finish();
    }

    public void rediriciona6(View view){
        Intent intent = new Intent(Hub.this, Ajuda.class);
        startActivity(intent);
        finish();
    }

    //public void rediriciona7(View view){
      //  Intent intent = new Intent(Hub.this, MainActivity.class);
        //startActivity(intent);
      //  finish();
    //}

    public void rediriciona8(View view){
        Intent intent = new Intent(Hub.this, SplashScreen.class);
        startActivity(intent);
        finish();
    }

    public void rediriciona9(View view){
        Intent intent = new Intent(Hub.this, Passos.class);
        startActivity(intent);
        finish();
    }

    public void rediriciona10(View view){
        Intent intent = new Intent(Hub.this, Perfil.class);
        startActivity(intent);
        finish();
    }

    public void rediriciona11(View view){
        Intent intent = new Intent(Hub.this, FormLoginPedro.class);
        startActivity(intent);
        finish();
    }

}