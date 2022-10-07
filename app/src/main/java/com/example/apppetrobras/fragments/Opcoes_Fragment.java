package com.example.apppetrobras.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.apppetrobras.Activities.Privacidade;
import com.example.apppetrobras.R;

public class Opcoes_Fragment extends Fragment implements View.OnClickListener {

    Dialog nDialog;
    ImageButton btnab1,btnab2;
    View view;

    // Declaração das variáveis

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_opcoes_, container, false);


        btnab2 = view.findViewById(R.id.btnab2);
        btnab1 = view.findViewById(R.id.btnab1);

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
                String url = "https://drive.google.com/file/d/1P39Iel7CKZqv8UF3EkSCCzer_GmJ5_BX/view?usp=sharing";
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