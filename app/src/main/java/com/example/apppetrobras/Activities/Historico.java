package com.example.apppetrobras.Activities;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.Navigations.Drawer;
import com.example.apppetrobras.Adapters.RVAdapterUserRelatorio;
import com.example.apppetrobras.Adapters.RVAdapterSolucoes;
import com.example.apppetrobras.Adapters.RVAdapterUserRelatorio;
import com.example.apppetrobras.Objects.AdminObj;
import com.example.apppetrobras.Objects.LoginObj;
import com.example.apppetrobras.Objects.ProblemasObj;
import com.example.apppetrobras.Objects.SolucoesObj;
import com.example.apppetrobras.Objects.UserRelatorioObj;
import com.example.apppetrobras.R;
import com.example.apppetrobras.Adapters.RecyclerViewAdapter;
import com.example.apppetrobras.api.RetroFitClient;
import com.example.apppetrobras.databinding.LayoutHistoricoBinding;
import com.example.apppetrobras.databinding.LayoutSolucoesBinding;
import com.example.apppetrobras.databinding.LayoutTabBinding;
import com.example.apppetrobras.fragments.RecyclerViewInteface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Historico extends Drawer implements RecyclerViewInteface {

    // Declaração das variáveis
    int idRelatorio, iddacerteza, tipoProblema, qtdSolucoes;
    String titulo, titulosProblemas, check;
    LayoutHistoricoBinding layoutHistoricoBinding;
    private ArrayList<ProblemasObj> dataArrayList;
    List<AdminObj> AdminObjList;
    List<UserRelatorioObj> userRelatorioObjList;

    private RecyclerView recyclerview;
    private Context context;
    private RecyclerViewInteface recyclerViewInteface;


    List<SolucoesObj> solucoesObjList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        layoutHistoricoBinding = LayoutHistoricoBinding.inflate(getLayoutInflater());
        setContentView(layoutHistoricoBinding.getRoot());
        allocateActivityTitle("Histórico");


        context = this;
        recyclerViewInteface = this;

        recyclerview = findViewById(R.id.recyclerviewadmin);
        recyclerview.setLayoutManager(new LinearLayoutManager(context));
        recyclerview.setHasFixedSize(true);
        idRelatorio = getIntent().getIntExtra("ID_TITULO",1);

        listen();

        sayMyName();

    }

    @Override
    public void onItemClick(int position) {
        // Redirecionamento para a tela do problema contendo os títulos das soluções
        Intent intent = new Intent(this, Relatorio.class);

        // Definição de valores que serão redirecionados
        iddacerteza = userRelatorioObjList.get(position).getIdRelatorio();
        intent.putExtra("idRelatorio", userRelatorioObjList.get(position).getIdRelatorio());
        startActivity(intent);
    }


    public void listen(){

        Call<List<UserRelatorioObj>> callme = RetroFitClient
                .getInstance()
                .getAPI()
                .getRelatorio(sayMyName());

        callme.enqueue(new Callback<List<UserRelatorioObj>>() {
            @Override
            public void onResponse(Call<List<UserRelatorioObj>> call, Response<List<UserRelatorioObj>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(context, "wassup", Toast.LENGTH_SHORT).show();
                    return;
                }

                //tentando guardar num objeto para que seja depois visivel no item_list_admin do dionisio
                userRelatorioObjList = response.body();
                UserRelatorioObj UserRelatorioObj = userRelatorioObjList.get(0);

                int id = UserRelatorioObj.getIdRelatorio();
                RVAdapterUserRelatorio recyclerViewAdapter = new RVAdapterUserRelatorio(context,
                        userRelatorioObjList, recyclerViewInteface, R.layout.item_list_admin);
                recyclerview.setAdapter(recyclerViewAdapter);
                recyclerViewAdapter.notifyDataSetChanged();


            }

            @Override
            public void onFailure(Call<List<UserRelatorioObj>> call, Throwable t) {

            }
        });

    }


    private String sayMyName(){
        SharedPreferences sharedPreferences = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        String chave = sharedPreferences.getString("chave", "");

        return chave;
    }

}