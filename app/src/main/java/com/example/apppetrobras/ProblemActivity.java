package com.example.apppetrobras;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.example.apppetrobras.api.RetroFitClient;
import com.example.apppetrobras.fragments.RecyclerViewInteface;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProblemActivity extends AppCompatActivity implements RecyclerViewInteface {
    int tipoProblema, idTitulo;

    private ArrayList<DadosLista> dataArrayList;
    private String[] titulosProblemas;
    private RecyclerView recyclerview;
    private Context context;
    private RecyclerViewInteface recyclerViewInteface;

    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem);

        tipoProblema = getIntent().getIntExtra("TIPO",0);
        idTitulo = getIntent().getIntExtra("ID",0);


        dataInitialize();

        recyclerview = findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setHasFixedSize(true);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this,
                dataArrayList, this, R.layout.item_soluction_list);
        recyclerview.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.notifyDataSetChanged();
    };

        private void dataInitialize() {


        dataArrayList = new ArrayList<>();

        titulosProblemas = new String[]{
                "começo",
                "meio",
                "fim"
        };

        for(int i = 0; i < titulosProblemas.length; i++){
            DadosLista data = new DadosLista(titulosProblemas[i]);
            dataArrayList.add(data);
        }

    }
    */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conteudo);

        tipoProblema = getIntent().getIntExtra("TIPO",0);
        idTitulo = getIntent().getIntExtra("ID",0);

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
                        .getLentidao();
            case 2:
                call = RetroFitClient
                        .getInstance()
                        .getAPI()
                        .getInternet();
            case 3:
                call = RetroFitClient
                        .getInstance()
                        .getAPI()
                        .getEquipamentos();
            case 4:
                call = RetroFitClient
                        .getInstance()
                        .getAPI()
                        .getOutros();
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

            }



            @Override
            public void onFailure(Call<List<Problems>> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }



    @Override
    public void onItemClick(int position) {

    }


}
