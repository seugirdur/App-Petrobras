package com.example.apppetrobras.Activities;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.Navigations.Drawer;
import com.example.apppetrobras.Adapters.RVAdapterSolucoes;
import com.example.apppetrobras.Objects.SolucoesObj;
import com.example.apppetrobras.R;
import com.example.apppetrobras.Adapters.RecyclerViewAdapter;
import com.example.apppetrobras.api.RetroFitClient;
import com.example.apppetrobras.databinding.LayoutSolucoesBinding;
import com.example.apppetrobras.databinding.LayoutTabBinding;
import com.example.apppetrobras.fragments.RecyclerViewInteface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Solucoes extends Drawer implements RecyclerViewInteface {

    // Declaração das variáveis
    int idTitulo, tipoProblema, qtdSolucoes;
    String titulo, titulosProblemas, check;
    private RecyclerView recyclerview;
    private Context context;
    private RecyclerViewInteface recyclerViewInteface;
    LayoutSolucoesBinding layoutSolucoesBinding;

    List<SolucoesObj> solucoesObjList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = this;
        recyclerViewInteface = this;

        layoutSolucoesBinding = LayoutSolucoesBinding.inflate(getLayoutInflater());
        setContentView(layoutSolucoesBinding.getRoot());
        allocateActivityTitle("Menu Principal");

        // Resgata as informações da teka anterior
        tipoProblema = getIntent().getIntExtra("TIPO", 1);
        idTitulo = getIntent().getIntExtra("ID_TITULO",1);
        titulo = getIntent().getStringExtra("titulo");
        titulosProblemas = getIntent().getStringExtra("titulosProblemas");

        // Resgata o check
        SharedPreferences sharedPreferences = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        check = sharedPreferences.getString("check","");

        // RecyclerView instanciação
        recyclerview = findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setHasFixedSize(true);

        Call<List<SolucoesObj>> call;

        switch (tipoProblema){
            case 1:
            default:
                call = RetroFitClient
                        .getInstance()
                        .getAPI()
                        .getLentidao(idTitulo);
                break;
            case 2:
                call = RetroFitClient
                        .getInstance()
                        .getAPI()
                        .getInternet(idTitulo);
                break;
            case 3:
                call = RetroFitClient
                        .getInstance()
                        .getAPI()
                        .getEquipamentos(idTitulo);
                break;
            case 4:
                call = RetroFitClient
                        .getInstance()
                        .getAPI()
                        .getOutros(idTitulo);
                break;
        }

        call.enqueue(new Callback<List<SolucoesObj>>() {
            @Override
            public void onResponse(Call<List<SolucoesObj>> call, Response<List<SolucoesObj>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(context, response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                solucoesObjList = response.body();

                qtdSolucoes = solucoesObjList.size();

                if(check.isEmpty()){

                    check = "";
                    for (int i = 0; i < qtdSolucoes; i++) {
                        check += "0";
                    }

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("check", check);
                    editor.apply();
                }

                RVAdapterSolucoes recyclerViewAdapter = new RVAdapterSolucoes(context,
                        solucoesObjList, recyclerViewInteface, R.layout.item_soluction_list, check);
                recyclerview.setAdapter(recyclerViewAdapter);
                recyclerViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<SolucoesObj>> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onItemClick(int position) {
        // Redirecionamento para a tela do problema contendo os títulos das soluções
        Intent intent = new Intent(Solucoes.this, Passos.class);

        // Insere na variável o titulo da solução clickada
        String tituloSolucao = solucoesObjList.get(position).getTituloSolucao();

        // Definição de valores que serão redirecionados
        intent.putExtra("TIPO",tipoProblema);
        intent.putExtra("ID_TITULO", idTitulo);
        intent.putExtra("titulo", titulo);
        tituloSolucao = solucoesObjList.get(position).getTituloSolucao();
        intent.putExtra("TITULO_SOLUCAO", tituloSolucao);
        // position começa em 0, para condizer ao BD é necessário adicionar 1 a ele
        intent.putExtra("ID_SOLUCAO", position+1);
        intent.putExtra("titulosProblemas",titulosProblemas);
        startActivity(intent);
        finish();
    }


    @Override
    public void onBackPressed() {
        // Resgata o check
        SharedPreferences sharedPreferences = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("check", "");
        editor.apply();

        finish();
    }

}