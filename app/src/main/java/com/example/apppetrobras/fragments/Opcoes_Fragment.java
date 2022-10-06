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
import android.widget.Toast;

import com.example.Navigations.Tabs;
import com.example.apppetrobras.Activities.Cadastro;
import com.example.apppetrobras.Activities.Login;
import com.example.apppetrobras.Activities.Privacidade;
import com.example.apppetrobras.Activities.TelaPrivacidade;
import com.example.apppetrobras.Objects.ProblemasObj;
import com.example.apppetrobras.Activities.Solucoes;
import com.example.apppetrobras.R;
import com.example.apppetrobras.Adapters.RecyclerViewAdapter;

import java.util.ArrayList;

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
                Intent i = new Intent(getActivity(), TelaPrivacidade.class); //abre o termos de uso
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