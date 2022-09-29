package com.example.apppetrobras;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.Navigations.TabActivity;
import com.example.apppetrobras.Activitys.AjudaeSuporte;
import com.example.apppetrobras.Activitys.FormCadastro;
import com.example.apppetrobras.Activitys.FormLogin;
import com.example.apppetrobras.Activitys.ProblemActivity;
import com.example.apppetrobras.Activitys.RelatorioProcesso;
import com.example.apppetrobras.Activitys.SoluctionActivity;
import com.example.apppetrobras.Activitys.Tela_de_escolha;

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
        Intent intent = new Intent(Hub.this, AjudaeSuporte.class);
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
        Intent intent = new Intent(Hub.this, SoluctionActivity.class);
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