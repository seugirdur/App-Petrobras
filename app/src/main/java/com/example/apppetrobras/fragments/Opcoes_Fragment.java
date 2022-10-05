package com.example.apppetrobras.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.apppetrobras.R;

public class Opcoes_Fragment extends Fragment implements View.OnClickListener {

    private ImageButton btnab1,btnab2,btnab3;
    View view;


    // Declaração das variáveis

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_opcoes_, container, false);
        View view = inflater.inflate(R.layout.fragment_opcoes_, container, false);


        btnab2 = view.findViewById(R.id.btnab2);

        btnab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Privacidade.class);
                startActivity(intent);

            }
        });

        btnab1 = view.findViewById(R.id.btnab1);
        btnab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Privacidade.class);
                startActivity(intent);
            }
        });

        return view;



//    @Override
//    public void onClick(View view) {
//
//    }
//    @Override
//    public void onClick(View view) {
//
//    }

    }

    @Override
    public void onClick(View view) {

    }
}