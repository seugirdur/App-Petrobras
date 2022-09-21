package com.example.apppetrobras;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.apppetrobras.api.RetroFitClient;
import com.example.apppetrobras.fragments.RecyclerViewInteface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProblemActivity extends AppCompatActivity implements RecyclerViewInteface {

    // Declaração das variáveis
    int idTitulo, tipoProblema;

    private RecyclerView recyclerview;
    private Context context;
    private RecyclerViewInteface recyclerViewInteface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conteudo);

        tipoProblema = getIntent().getIntExtra("TIPO", 1);
        idTitulo = getIntent().getIntExtra("ID_TITULO",1);

        recyclerview = findViewById(R.id.rv_conteudo);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setHasFixedSize(true);

        context = this;
        recyclerViewInteface = this;

        Call<List<Problems>> call;

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

        call.enqueue(new Callback<List<Problems>>() {
            @Override
            public void onResponse(Call<List<Problems>> call, Response<List<Problems>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(context, response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Problems> problemsList = response.body();
                RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(context,
                        problemsList, recyclerViewInteface, R.layout.item_soluction_list );
                recyclerview.setAdapter(recyclerViewAdapter);
                recyclerViewAdapter.notifyDataSetChanged();
                //Toast.makeText(context, "item: "+idTitulo, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Problems>> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        // Redirecionamento para a tela do problema contendo os títulos das soluções
        Intent intent = new Intent(ProblemActivity.this, SoluctionActivity.class);

        // Definição de valores que serão redirecionados
        intent.putExtra("TIPO",tipoProblema);
        intent.putExtra("ID_TITULO", idTitulo);
        // position começa em 0, por isso é necessário adicionar 1 a ele
        intent.putExtra("ID_SOLUCAO", position+1);
        //todas as soluções começam pelo primeiro passo
        intent.putExtra("PASSO", 1);
        startActivity(intent);
    }



}
