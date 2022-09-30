package com.example.apppetrobras;

import android.os.Bundle;

import com.example.Navigations.Drawer;
import com.example.apppetrobras.databinding.ActivityHistoricoBinding;

public class Historico extends Drawer {

    ActivityHistoricoBinding activityHistoricoBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityHistoricoBinding = ActivityHistoricoBinding.inflate(getLayoutInflater());
        setContentView(activityHistoricoBinding.getRoot());
        allocateActivityTitle("Histórico");
    }
}