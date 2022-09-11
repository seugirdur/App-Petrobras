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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conteudo);

        tv_result = findViewById(R.id.tv_result);



        Call<List<Problems>> call = RetroFitClient
                .getInstance()
                .getAPI()
                .getProblems();

        call.enqueue(new Callback<List<Problems>>() {
            @Override
            public void onResponse(Call<List<Problems>> call, Response<List<Problems>> response) {
                if (!response.isSuccessful()){
                    tv_result.setText("Code: "+response.code());
                    return;
                }
                List<Problems> problemsList = response.body();
                for (Problems problems : problemsList) {
                    String content ="";
                    content += "idTitulo :" + problems.getIdTitulo() + "\n";
                    content += "idProblema :" + problems.getIdProblema() + "\n";
                    content += "tituloSolucao :" + problems.getTituloSolucao() + "\n\n";

                    tv_result.append(content);

                }

            }

            @Override
            public void onFailure(Call<List<Problems>> call, Throwable t) {
                tv_result.setText(t.getMessage());

            }
        });

    }
}