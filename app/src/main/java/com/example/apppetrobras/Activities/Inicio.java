package com.example.apppetrobras.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
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
        setContentView(R.layout.layout_inicio);

        button2 = findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Inicio.this, Login.class);
                startActivity(i);
            }
        });


        linkTextView=findViewById(R.id.textView12);

        String text = getString(R.string.texto_tela_escolha);
        SpannableString ss = new SpannableString(text);

        ClickableSpan clicavel1 = new ClickableSpan() {
            public void onClick(View widget) {
                Intent i = new Intent(Inicio.this, Ajuda.class);
                startActivity(i);
            }
        };

        // declarando parte que funcionará como clicável
        ss.setSpan(clicavel1, 47, 60, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        linkTextView.setText(ss);
        linkTextView.setMovementMethod(LinkMovementMethod.getInstance());
        //link no botão 'clique aqui' o texto e o link estão no @strings

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