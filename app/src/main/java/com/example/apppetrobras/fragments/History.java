package com.example.apppetrobras.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.widget.Toast;

import com.example.apppetrobras.Activities.Relatorio;
import com.example.apppetrobras.Adapters.RVAdapterUserRelatorio;
import com.example.apppetrobras.Objects.RelatorioObj;
import com.example.apppetrobras.Objects.UserRelatorioObj;
import com.example.apppetrobras.R;
import com.example.apppetrobras.api.RetroFitClient;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class History extends Fragment implements RecyclerViewInteface {

    List<RelatorioObj> relatorioObjList;

    private RecyclerView recyclerview;
    private Context context;
    private RecyclerViewInteface recyclerViewInteface;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_historico_, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = getContext();
        recyclerViewInteface = this;

        // Essa variável recebe (por meio do id) a reciclerView no xml dessa tela
        recyclerview = view.findViewById(R.id.recyclerviewhst);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview.setHasFixedSize(true);

        listen();
        sayMyChave();
    }

    public void listen(){
        Call<List<RelatorioObj>> callme = RetroFitClient
                .getInstance()
                .getAPI()
                .getRelatorio(sayMyChave());

        callme.enqueue(new Callback<List<RelatorioObj>>() {
            @Override
            public void onResponse(Call<List<RelatorioObj>> call, Response<List<RelatorioObj>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(context, "wassup", Toast.LENGTH_SHORT).show();
                    return;
                }
                //tentando guardar num objeto para que seja depois visivel no item_list_admin do dionisio
                relatorioObjList = response.body();
                RelatorioObj relatorioObj = relatorioObjList.get(0);
                int id = relatorioObj.getIdRelatorio();
                RVAdapterUserRelatorio recyclerViewAdapter = new RVAdapterUserRelatorio(context,
                        relatorioObjList, recyclerViewInteface, R.layout.item_historico);
                recyclerview.setAdapter(recyclerViewAdapter);
                recyclerViewAdapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<List<RelatorioObj>> call, Throwable t) {
            }
        });
    }

    private String sayMyChave(){
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        String chave = sharedPreferences.getString("chave", "");

        return chave;
    }

    @Override
    public void onItemClick(int position) {

        // Redirecionamento para a tela do problema contendo os títulos das soluções
        Intent intent = new Intent(getActivity(), Relatorio.class);

        // Definição de valores que serão redirecionados
        int idRelatorio = relatorioObjList.get(position).getIdRelatorio();
        intent.putExtra("idRelatorio", idRelatorio);
        startActivity(intent);

//        // Redirecionamento para a tela do problema contendo os títulos das soluções
//        Intent intent = new Intent(getActivity(), Inicio.class);
//
//        // Definição de valores que serão redirecionados
//        intent.putExtra("TIPO",3);
//        intent.putExtra("ID_TITULO", dataArrayList.get(position).getId());
//        startActivity(intent);
    }

}