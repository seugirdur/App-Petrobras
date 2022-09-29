package com.example.apppetrobras.Activitys;

import android.os.Bundle;

import com.example.Navigations.DrawerBaseActivity;
import com.example.apppetrobras.databinding.ActivityAjudaBinding;

public class AjudaeSuporte extends DrawerBaseActivity {

    ActivityAjudaBinding activityAjudaBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAjudaBinding = ActivityAjudaBinding.inflate(getLayoutInflater());
        setContentView(activityAjudaBinding.getRoot());
        allocateActivityTitle("Ajuda e Suporte");
    }
}