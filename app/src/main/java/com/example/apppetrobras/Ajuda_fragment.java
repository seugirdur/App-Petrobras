package com.example.apppetrobras;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

//public class Ajuda_fragment extends AppCompatActivity {
//
//    ImageButton botaoAbrir, botaoOutro, botaotres, botaoquatro, botaocinco, botaoseis;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_ajuda);
//
//        botaoAbrir = findViewById(R.id.btn);
//        botaoOutro = findViewById(R.id.btn2);
//        botaotres = findViewById(R.id.btn3);
//        botaoquatro = findViewById(R.id.btn4);
//        botaocinco = findViewById(R.id.btn5);
//        botaoseis = findViewById(R.id.btn6);
//
//
//
//        botaoAbrir.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//                Intent intent = new Intent(getApplicationContext(),Tela_de_escolha.class);
//                startActivity(intent);
//
//                finish();
//            }
//        });
//
//        botaoOutro.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent i = new Intent(getApplicationContext(),Tela_de_escolha.class);
//                startActivity(i);
//
//            }
//        });
//
//        botaotres.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent dnv = new Intent(getApplicationContext(),Tela_de_escolha.class);
//                startActivity(dnv);
//            }
//        });
//
//        botaoquatro.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent abc = new Intent(getApplicationContext(),Tela_de_escolha.class);
//                startActivity(abc);
//            }
//        });
//
//        botaocinco.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent defg = new Intent(getApplicationContext(),Tela_de_escolha.class);
//                startActivity(defg);
//            }
//        });
//
//        botaoseis.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent hijk = new Intent(getApplicationContext(),Tela_de_escolha.class);
//                startActivity(hijk);
//            }
//        });
//
//
//
//
//    }
//}

public class Ajuda_fragment extends Fragment {

//        ImageButton botaoAbrir, botaoOutro, botaotres, botaoquatro, botaocinco, botaoseis;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_ajuda_fragment);
//
//        botaoAbrir = findViewById(R.id.btn);
//        botaoOutro = findViewById(R.id.btn2);
//        botaotres = findViewById(R.id.btn3);
//        botaoquatro = findViewById(R.id.btn4);
//        botaocinco = findViewById(R.id.btn5);
//        botaoseis = findViewById(R.id.btn6);
//
//
//
//        botaoAbrir.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//                Intent intent = new Intent(getApplicationContext(),Tela_de_escolha.class);
//                startActivity(intent);
//
//                finish();
//            }
//        });
//
//        botaoOutro.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent i = new Intent(getApplicationContext(),Tela_de_escolha.class);
//                startActivity(i);
//
//            }
//        });
//
//        botaotres.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent dnv = new Intent(getApplicationContext(),Tela_de_escolha.class);
//                startActivity(dnv);
//            }
//        });
//
//        botaoquatro.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent abc = new Intent(getApplicationContext(),Tela_de_escolha.class);
//                startActivity(abc);
//            }
//        });
//
//        botaocinco.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent defg = new Intent(getApplicationContext(),Tela_de_escolha.class);
//                startActivity(defg);
//            }
//        });
//
//        botaoseis.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent hijk = new Intent(getApplicationContext(),Tela_de_escolha.class);
//                startActivity(hijk);
//            }
//        });
//
//
//
//
//    }
//}

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public Ajuda_fragment() {
    }

    public static Ajuda_fragment newInstance(String param1, String param2) {
        Ajuda_fragment fragment = new Ajuda_fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ajuda_fragment, container, false);
    }
}