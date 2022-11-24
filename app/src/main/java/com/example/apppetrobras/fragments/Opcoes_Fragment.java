package com.example.apppetrobras.fragments;

import android.app.Dialog;
import android.content.ClipDescription;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;

import com.example.apppetrobras.Activities.Privacidade;
import com.example.apppetrobras.R;

public class Opcoes_Fragment extends Fragment implements View.OnClickListener {

    Dialog nDialog;
    ImageButton btnab1, btnab2, btnab3, btnManual;
    View view;

    // Declaração das variáveis

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_opcoes_, container, false);


        btnab2 = view.findViewById(R.id.btnab2);
        btnab1 = view.findViewById(R.id.btnab1);
        btnab3 = view.findViewById(R.id.btnab3);
        btnManual = view.findViewById(R.id.btn_manual);

        btnab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Privacidade.class); //abre o privacidade e segurança
                startActivity(intent);
            }
        });

        btnab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://drive.google.com/file/d/13x7qNiF3JogETBmRLnJ-ZnHv-oLCOcNA/view?usp=sharing";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        btnab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String url = "https://is.gd/suporteaset";
//                Intent i = new Intent(Intent.ACTION_VIEW);
//                i.setData(Uri.parse(url));

                SharedPreferences sharedPreferences = getActivity().getSharedPreferences(
                        getString(R.string.preference_file_key), Context.MODE_PRIVATE);
                String nome = sharedPreferences.getString("nome", "");


                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"suporteaset@gmail.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "Avaliação de " + nome);
//                intent.putExtra(Intent.EXTRA_TEXT, "");
                startActivity(Intent.createChooser(intent, "Escolha o aplicativo de email"));
            }
        });

        btnManual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //link do manual
                String url = "https://drive.google.com/file/d/1i9v_hsjVKj9h4qRWnj-KO0TXSCDSY-Y5/view?usp=sharing";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        return view;

//        btnab1 = view.findViewById(R.id.btnab1);
//        btnab1.setOnClickListener(new View.OnClickListener() {
//           Intent i = new Intent(getActivity(), Cadastro.class);

    }

    @Override
    public void onClick(View view) {

    }
}