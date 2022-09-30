package com.example.apppetrobras.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.apppetrobras.R;

public class Inicio extends AppCompatActivity {
    
    Button button2;
    TextView linkTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio_layout);

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
        linkTextView.setMovementMethod(LinkMovementMethod.getInstance());

    }


    //redirecionamento para ajuda
    public void ajuda(View view){
        Intent intent = new Intent(this, Ajuda.class);
        startActivity(intent);

    }

    public void cadastrar (View view){

        Intent intent = new Intent(this, Cadastro.class);
        startActivity(intent);
     }


    public void setCurrentItemPager(int i) {
    }
}