package com.example.apppetrobras.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.apppetrobras.R;

public class Inicio extends AppCompatActivity {

    Button button2;
    TextView linkTextView;
    Dialog mDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_inicio);
        mDialog = new Dialog(this);



        button2 = findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Inicio.this, Login.class);
                startActivity(i);
            }
        });


        //link no botão 'clique aqui' o texto e o link estão no @strings
        linkTextView=findViewById(R.id.textView12);
        linkTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mDialog.setContentView(R.layout.popup);
                mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                mDialog.show();
            }
        });
        linkTextView.setMovementMethod(LinkMovementMethod.getInstance());

    }


    //redirecionamento para ajuda
    public void ajuda(View view){

        mDialog.setContentView(R.layout.popup);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDialog.show();
    }

    public void cadastrar (View view){

        Intent intent = new Intent(this, Cadastro.class);
        startActivity(intent);
    }


    public void setCurrentItemPager(int i) {
    }
}