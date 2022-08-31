package com.example.apppetrobras;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Tela_de_escolha extends AppCompatActivity {
    
    Button button2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_de_escolha);

        button2 = findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Tela_de_escolha.this, FormLogin.class);
                startActivity(i);
            }
        });
    }

    public void cadastrar (View view){

        Intent intent = new Intent(this, FormCadastro.class);
        startActivity(intent);
     }
     

}