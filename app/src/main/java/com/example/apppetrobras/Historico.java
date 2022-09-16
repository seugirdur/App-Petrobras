package com.example.apppetrobras;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.apppetrobras.databinding.ActivityHistoricoBinding;

public class Historico extends DrawerBaseActivity {

    ActivityHistoricoBinding activityHistoricoBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityHistoricoBinding = ActivityHistoricoBinding.inflate(getLayoutInflater());
        setContentView(activityHistoricoBinding.getRoot());
        allocateActivityTitle("Histórico");
    }
}