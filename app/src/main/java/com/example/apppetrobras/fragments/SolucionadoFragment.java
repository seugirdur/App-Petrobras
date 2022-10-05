package com.example.apppetrobras.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apppetrobras.Activities.Inicio;
import com.example.apppetrobras.Activities.Solucoes;
import com.example.apppetrobras.Adapters.RVAdapterEmAberto;
import com.example.apppetrobras.Adapters.RecyclerViewAdapter;
import com.example.apppetrobras.Adapters.RelatorioAdapter;
import com.example.apppetrobras.Objects.ProblemasObj;
import com.example.apppetrobras.Objects.AdminObj;
import com.example.apppetrobras.Objects.SolucoesObj;
import com.example.apppetrobras.R;
import com.example.apppetrobras.api.RetroFitClient;
import com.example.apppetrobras.databinding.LayoutSolucoesBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SolucionadoFragment extends Fragment implements RecyclerViewInteface{

    // Declaração das variáveis
    private ArrayList<ProblemasObj> dataArrayList;
    List<AdminObj> AdminObjList;

    private RecyclerView recyclerview;
    private Context context;
    private RecyclerViewInteface recyclerViewInteface;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.layout_tela_admin_chamadas, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = getContext();
        recyclerViewInteface = this;

        recyclerview = view.findViewById(R.id.recyclerviewadmin);
        recyclerview.setLayoutManager(new LinearLayoutManager(context));
        recyclerview.setHasFixedSize(true);

        listen();
    }

    @Override
    public void onItemClick(int position) {
        // Redirecionamento para a tela do problema contendo os títulos das soluções
        Intent intent = new Intent(getActivity(), Inicio.class);

        // Definição de valores que serão redirecionados
        intent.putExtra("TIPO",3);
        intent.putExtra("ID_TITULO", dataArrayList.get(position).getId());
        startActivity(intent);
    }

    private void listen(){

        Call<List<AdminObj>> callme = RetroFitClient
                .getInstance()
                .getAPI()
                .getAllRelatoriosClosed();

        callme.enqueue(new Callback<List<AdminObj>>() {
            @Override
            public void onResponse(Call<List<AdminObj>> call, Response<List<AdminObj>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(getContext(), "wassup", Toast.LENGTH_SHORT).show();
                    return;
                }

                //tentando guardar num objeto para que seja depois visivel no item_list_admin do dionisio
                AdminObjList = response.body();

                RVAdapterEmAberto recyclerViewAdapter = new RVAdapterEmAberto(context,
                        AdminObjList, recyclerViewInteface, R.layout.item_list_admin);
                recyclerview.setAdapter(recyclerViewAdapter);
                recyclerViewAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<AdminObj>> call, Throwable t) {

            }
        });

    }



}