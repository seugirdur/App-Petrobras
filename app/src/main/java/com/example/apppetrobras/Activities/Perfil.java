package com.example.apppetrobras.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.Navigations.Drawer;
import com.example.apppetrobras.R;
import com.example.apppetrobras.databinding.LayoutPassosBinding;
import com.example.apppetrobras.databinding.LayoutPerfilBinding;

public class Perfil extends Drawer {

    LayoutPerfilBinding layoutPerfilBinding;

    TextView nomecompleto, num_tel, email1, num_chave, nome1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layoutPerfilBinding = LayoutPerfilBinding.inflate(getLayoutInflater());
        setContentView(layoutPerfilBinding.getRoot());
        allocateActivityTitle("Perfil");


        SharedPreferences sharedPreferences = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        String nome = sharedPreferences.getString("nome", "");
        String tel = sharedPreferences.getString("tel", "");
        String email = sharedPreferences.getString("email", "");
        String chave = sharedPreferences.getString("chave", "");


        String[] fullNameArray = nome.split("\\s+");
        String firstName = fullNameArray[0];
        nome1 = findViewById(R.id.nome_login);
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

    public void mudame(View view) {
        Intent intent = new Intent(Perfil.this, PerfilAtualizar.class);
        startActivity(intent);
    }


}