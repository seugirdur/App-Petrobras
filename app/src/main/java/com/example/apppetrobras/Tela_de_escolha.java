package com.example.apppetrobras;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Tela_de_escolha extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_de_escolha);
    }
    public void cadastrar (View view){

        Intent intent = new Intent(this, FormCadastro.class);
        startActivity(intent);
     }
}