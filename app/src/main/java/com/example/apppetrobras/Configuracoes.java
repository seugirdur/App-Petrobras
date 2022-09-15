package com.example.apppetrobras;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.apppetrobras.databinding.ActivityConfiguracoesBinding;

public class Configuracoes extends DrawerBaseActivity {

    ActivityConfiguracoesBinding activityConfiguracoesBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityConfiguracoesBinding = ActivityConfiguracoesBinding.inflate(getLayoutInflater());
        setContentView(activityConfiguracoesBinding.getRoot());
        allocateActivityTitle("Configurações");

    }
}