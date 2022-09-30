package com.example.apppetrobras.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.Navigations.Tabs;
import com.example.apppetrobras.Activities.Cadastro;
import com.example.apppetrobras.Activities.Login;
import com.example.apppetrobras.Objects.ProblemasObj;
import com.example.apppetrobras.Activities.Solucoes;
import com.example.apppetrobras.R;
import com.example.apppetrobras.Adapters.RecyclerViewAdapter;

import java.util.ArrayList;

public class Opcoes_Fragment extends Fragment  {

    Dialog nDialog;
    ImageButton btnab2;
    View view;

    // Declaração das variáveis

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_opcoes_, container, false);

        public void abrir_popup (View view){

            Intent intent = new Intent(this, .class);
            startActivity(intent);
        }

    }
//        btnab5.setOnClickListener(new View.OnClickListener(){
//        @Override
//        public void onClick(View){
//
//        nDialog.setContentView(R.layout.popup_termos);
//        nDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        nDialog.show();
//
//    }

//    public void pop_termos() {
//
        Intent intent = new Intent(Login.this, Tabs.class);
        //startActivity(intent);
//    }

    }
//}