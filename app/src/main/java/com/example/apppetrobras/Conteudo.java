package com.example.apppetrobras;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apppetrobras.api.RetroFitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Conteudo extends AppCompatActivity {

    private TextView tv_result;
    private RecyclerView rv_conteudo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conteudo);


        rv_conteudo = findViewById(R.id.rv_conteudo);
        rv_conteudo.setHasFixedSize(true);
        rv_conteudo.setLayoutManager(new LinearLayoutManager(this));



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
                ProblemsAdapter problemsAdapter = new ProblemsAdapter(Conteudo.this, problemsList);
                rv_conteudo.setAdapter(problemsAdapter);

                }



            @Override
            public void onFailure(Call<List<Problems>> call, Throwable t) {
                Toast.makeText(Conteudo.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}