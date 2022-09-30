package com.example.apppetrobras.Activities;

import android.os.Bundle;

import com.example.Navigations.Drawer;
import com.example.apppetrobras.databinding.ActivityAjudaBinding;

public class Ajuda extends Drawer {

    ActivityAjudaBinding activityAjudaBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAjudaBinding = ActivityAjudaBinding.inflate(getLayoutInflater());
        setContentView(activityAjudaBinding.getRoot());
        allocateActivityTitle("Ajuda e Suporte");
    }
}