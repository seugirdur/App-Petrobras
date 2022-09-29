package com.example.apppetrobras;

import android.os.Bundle;

import com.example.Navigations.DrawerBaseActivity;
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