package com.example.apppetrobras;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

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