package com.example.apppetrobras;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class Perfil extends AppCompatActivity {


    TextView nomecompleto,num_tel,email1,num_chave, nome1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        SharedPreferences sharedPreferences = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        String nome = sharedPreferences.getString("nome", "");
        String tel = sharedPreferences.getString("tel", "");
        String email = sharedPreferences.getString("email", "");
        String chave = sharedPreferences.getString("chave", "");


        String[] fullNameArray = nome.split("\\s+");
        String firstName = fullNameArray[0];
        nome1=findViewById(R.id.nome_login);
        nome1.setText(firstName);

        nomecompleto = findViewById(R.id.nomecompleto);
        nomecompleto.setText(nome);

        num_tel = findViewById(R.id.num_tel);
        num_tel.setText(tel);

        email1 = findViewById(R.id.email);
        email1.setText(email);

        num_chave = findViewById(R.id.num_chave);
        num_chave.setText(chave);




    }
}