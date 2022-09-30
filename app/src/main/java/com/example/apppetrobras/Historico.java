package com.example.apppetrobras;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.Navigations.Drawer;
import com.example.apppetrobras.databinding.ActivityHistoricoBinding;

public class Historico extends Fragment {

    ActivityHistoricoBinding activityHistoricoBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_historico_, container, false);
    }
}