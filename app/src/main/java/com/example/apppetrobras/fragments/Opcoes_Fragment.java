package com.example.apppetrobras.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.apppetrobras.Objects.ProblemasObj;
import com.example.apppetrobras.Activities.Solucoes;
import com.example.apppetrobras.R;
import com.example.apppetrobras.Adapters.RecyclerViewAdapter;

import java.util.ArrayList;

public class Opcoes_Fragment extends Fragment {

    // Declaração das variáveis

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_opcoes_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

}