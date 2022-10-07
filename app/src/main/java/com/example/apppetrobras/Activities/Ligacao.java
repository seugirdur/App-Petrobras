package com.example.apppetrobras.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

//import com.bumptech.glide.Glide;
import com.example.Navigations.Drawer;
import com.example.apppetrobras.R;
import com.example.apppetrobras.Objects.PassosObj;
import com.example.apppetrobras.api.RetroFitClient;
import com.example.apppetrobras.databinding.LayoutLigacaoBinding;
import com.example.apppetrobras.databinding.LayoutPassosBinding;


import java.util.Date;
import java.util.List;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Ligacao extends Drawer {

    LayoutLigacaoBinding layoutLigacaoBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layoutLigacaoBinding = LayoutLigacaoBinding.inflate(getLayoutInflater());
        setContentView(layoutLigacaoBinding.getRoot());
        allocateActivityTitle("Ligação");
    }

    public void ligacao(View view){}

}