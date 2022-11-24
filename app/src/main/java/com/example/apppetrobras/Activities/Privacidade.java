package com.example.apppetrobras.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;

import com.example.Navigations.Drawer;
import com.example.apppetrobras.R;
import com.example.apppetrobras.databinding.ActivityPrivacidadeBinding;
import com.example.apppetrobras.databinding.LayoutPerfilAtualizarBinding;

public class Privacidade extends Drawer {

    ActivityPrivacidadeBinding activityPrivacidadeBinding;
    ImageView lula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityPrivacidadeBinding = ActivityPrivacidadeBinding.inflate(getLayoutInflater());
        setContentView(activityPrivacidadeBinding.getRoot());
        allocateActivityTitle("Sobre Nós");
    }

}