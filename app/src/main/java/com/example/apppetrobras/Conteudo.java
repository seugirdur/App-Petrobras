package com.example.apppetrobras;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apppetrobras.api.RetroFitClient;
import com.example.apppetrobras.fragments.RecyclerViewInteface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Conteudo extends AppCompatActivity implements RecyclerViewInteface{

    private RecyclerView recyclerview;
    private Context context;
    private RecyclerViewInteface recyclerViewInteface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conteudo);

        recyclerview = findViewById(R.id.rv_conteudo);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setHasFixedSize(true);

        context = this;
        recyclerViewInteface = this;

        Call<List<Problems>> call = RetroFitClient
                .getInstance()
                .getAPI()
                .getProblems();

        call.enqueue(new Callback<List<Problems>>() {
            @Override
            public void onResponse(Call<List<Problems>> call, Response<List<Problems>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(Conteudo.this, response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Problems> problemsList = response.body();
                RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(context,
                        problemsList, recyclerViewInteface, R.layout.cont_list );
                recyclerview.setAdapter(recyclerViewAdapter);
                recyclerViewAdapter.notifyDataSetChanged();

                }



            @Override
            public void onFailure(Call<List<Problems>> call, Throwable t) {
                Toast.makeText(Conteudo.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public void onItemClick(int position) {

    }
}