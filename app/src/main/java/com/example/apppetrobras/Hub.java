package com.example.apppetrobras;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Hub extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hub);
    }

    public void rediriciona0(View view){
        Intent intent = new Intent(Hub.this, Tela_de_escolha.class);
        startActivity(intent);
        finish();
    }

    public void rediriciona1(View view){
        Intent intent = new Intent(Hub.this, FormLogin.class);
        startActivity(intent);
        finish();
    }

    public void rediriciona2(View view){
        Intent intent = new Intent(Hub.this, FormCadastro.class);
        startActivity(intent);
        finish();
    }

    public void rediriciona3(View view){
        Intent intent = new Intent(Hub.this, TabActivity.class);
        startActivity(intent);
        finish();
    }

    public void rediriciona4(View view){
        Intent intent = new Intent(Hub.this, ProblemActivity.class);
        startActivity(intent);
        finish();
    }

    public void rediriciona5(View view){
        Intent intent = new Intent(Hub.this, RelatorioProcesso.class);
        startActivity(intent);
        finish();
    }

    public void rediriciona6(View view){
        Intent intent = new Intent(Hub.this, Ajuda.class);
        startActivity(intent);
        finish();
    }

    public void rediriciona7(View view){
        Intent intent = new Intent(Hub.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void rediriciona8(View view){
        Intent intent = new Intent(Hub.this, SplashScreenActivity.class);
        startActivity(intent);
        finish();
    }

    public void rediriciona9(View view){
        Intent intent = new Intent(Hub.this, Conteudo.class);
        startActivity(intent);
        finish();
    }

}