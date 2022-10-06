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

    private TextView solUsar, Usar, Chamar, Buscar, Ver;
    private ImageButton btnUsar, btnChamar, btnBuscar, btnVer;
    private ConstraintLayout scroll;
    private ConstraintSet mConstraintSet = new ConstraintSet();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajuda);
        solUsar = findViewById(R.id.solUsar);
        //TextView solChamar = findViewById(R.id.solChamar);
        //TextView solBuscar = findViewById(R.id.solBuscar);
        //TextView solVer = findViewById(R.id.solVer);

         Usar = findViewById(R.id.txtUsar);
         Chamar = findViewById(R.id.txtChamar);
         Buscar = findViewById(R.id.txtBuscar);
         Ver = findViewById(R.id.txtVer);
         btnUsar = findViewById(R.id.btnUsar);
         btnChamar = findViewById(R.id.btnChamar);
         btnBuscar = findViewById(R.id.btnBuscar);
         btnVer = findViewById(R.id.btnVer);
         scroll = findViewById(R.id.layoutScroll);
         //usar
        //chamar
        //buscar
        //ver
    }
    public void btnUsar(View view){
        if (solUsar.getVisibility() == View.GONE) {
            solUsar.setVisibility(View.VISIBLE);
            mConstraintSet.clone(scroll);
            mConstraintSet.connect(R.id.txtChamar,ConstraintSet.TOP,R.id.solUsar,ConstraintSet.TOP,25);
            mConstraintSet.connect(R.id.btnChamar,ConstraintSet.TOP,R.id.solUsar,ConstraintSet.TOP,15);
            mConstraintSet.connect(R.id.txtChamar,ConstraintSet.TOP,R.id.solUsar,ConstraintSet.TOP,25);
            mConstraintSet.connect(R.id.btnChamar,ConstraintSet.TOP,R.id.solUsar,ConstraintSet.TOP,15);
            mConstraintSet.applyTo(scroll);
        }else{
            solUsar.setVisibility(View.GONE);
            mConstraintSet.clone(scroll);
            mConstraintSet.connect(R.id.txtChamar,ConstraintSet.TOP,R.id.txtUsar,ConstraintSet.TOP,25);
            mConstraintSet.connect(R.id.btnChamar,ConstraintSet.TOP,R.id.txtUsar,ConstraintSet.TOP,15);
            mConstraintSet.applyTo(scroll);
        }

    }

}