package com.example.apppetrobras.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.apppetrobras.R;

public class Ajuda extends AppCompatActivity {

    private TextView solUsar, solChamar, solBuscar, solVer, solChamado, solEscolha;
    private ImageButton btnUsar, btnChamar, btnBuscar, btnVer, btnEscolha, btnChamado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajuda);
        solUsar = findViewById(R.id.solUsar);
        solChamar = findViewById(R.id.solChamar);
        solBuscar = findViewById(R.id.solBuscar);
        solVer = findViewById(R.id.solVer);
        solChamado = findViewById(R.id.solChamado);


         btnUsar = findViewById(R.id.btnUsar);
         btnChamar = findViewById(R.id.btnChamar);
         btnBuscar = findViewById(R.id.btnBuscar);
         btnChamado = findViewById(R.id.btnChamado);

         btnVer = findViewById(R.id.btnVer);



        btnUsar = findViewById(R.id.btnUsar);
        btnChamar = findViewById(R.id.btnChamar);
        btnBuscar = findViewById(R.id.btnBuscar);
        btnChamado = findViewById(R.id.btnChamado);

        btnVer = findViewById(R.id.btnVer);


    }
    public void btnUsar(View view){
        if (solUsar.getVisibility() == View.GONE) {
            solUsar.setVisibility(View.VISIBLE);
            btnUsar.setRotation(90);

        }else{
            solUsar.setVisibility(View.GONE);
            btnUsar.setRotation(270);
        }
    }

    public void btnChamar(View view){
        if (solChamar.getVisibility() == View.GONE) {
            solChamar.setVisibility(View.VISIBLE);
            btnChamar.setRotation(90);

        }else{
            solChamar.setVisibility(View.GONE);
            btnChamar.setRotation(270);
        }
    }

    public void btnBuscar(View view){
        if (solBuscar.getVisibility() == View.GONE) {
            solBuscar.setVisibility(View.VISIBLE);
            btnBuscar.setRotation(90);

        }else{
            solBuscar.setVisibility(View.GONE);
            btnBuscar.setRotation(270);
        }
    }

    public void btnVer(View view){
        if (solVer.getVisibility() == View.GONE) {
            solVer.setVisibility(View.VISIBLE);
            btnVer.setRotation(90);

        }else{
            solVer.setVisibility(View.GONE);
            btnVer.setRotation(270);
        }
    }

    public void btnChamado(View view){
        if (solChamado.getVisibility() == View.GONE) {
            solChamado.setVisibility(View.VISIBLE);
            btnChamado.setRotation(90);

        }else{
            solChamado.setVisibility(View.GONE);
            btnChamado.setRotation(270);
        }
    }




}