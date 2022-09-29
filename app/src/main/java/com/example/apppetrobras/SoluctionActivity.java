package com.example.apppetrobras;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.OnClickAction;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apppetrobras.api.RetroFitClient;
import com.example.apppetrobras.databinding.ActivityConfiguracoesBinding;
import com.example.apppetrobras.databinding.ActivitySoluctionBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SoluctionActivity extends AppCompatActivity {

    // Declaração das variáveis
    int idTitulo, idSolucao, tipoProblema, idPasso, qtdPassos;
    String tituloSolucao;

    Context context;

    TextView numeroPasso, nomeSolucao, descSolucao;
    ImageView imagemSolucao;

    List<Soluctions> soluctionsList;

    Call<List<Soluctions>> call;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soluction);

        tipoProblema = getIntent().getIntExtra("TIPO", 1);
        idTitulo = getIntent().getIntExtra("ID_TITULO",1);
        idSolucao = getIntent().getIntExtra("ID_SOLUCAO",1);
        idPasso = getIntent().getIntExtra("PASSO",1);
        tituloSolucao = getIntent().getStringExtra("TITULO_SOLUCAO");

        String concatenar = Integer.toString(idTitulo) + Integer.toString(idSolucao);
        int numerojunto = Integer.parseInt(concatenar);

        context = this;

        switch (tipoProblema){
            case 1:
            default:
                call = RetroFitClient
                        .getInstance()
                        .getAPI()
                        .getTextoLentidao(numerojunto, idPasso);
                break;
            case 2:
                call = RetroFitClient
                        .getInstance()
                        .getAPI()
                        .getTextoInternet(numerojunto, idPasso);
                break;
            case 3:
                call = RetroFitClient
                        .getInstance()
                        .getAPI()
                        .getTextoEquipamento(numerojunto, idPasso);
                break;
            case 4:
                call = RetroFitClient
                        .getInstance()
                        .getAPI()
                        .getTextoOutros(numerojunto, idPasso);
                break;
        }

        call.enqueue(new Callback<List<Soluctions>>() {
            @Override
            public void onResponse(Call<List<Soluctions>> call, Response<List<Soluctions>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(context, response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                soluctionsList = response.body();
                // Armazena o total de passos dessa solução
                qtdPassos = soluctionsList.size();
                inserirNaTela();
            }

            @Override
            public void onFailure(Call<List<Soluctions>> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void proxPasso(View view){
        if (idPasso > 1) {
            idPasso-=1;
            inserirNaTela();
        }
    }

    public void rtrnPasso(View view){
        // verifica se há mais um passo então atualiza as informações na tela:
        if (idPasso < qtdPassos) {
            idPasso+=1;
            inserirNaTela();
        }
    }

    public void inserirNaTela(){
        // Inserção dos Valores na tela
        nomeSolucao = findViewById(R.id.nomeSolucao);
        nomeSolucao.setText(tituloSolucao);

        numeroPasso = findViewById(R.id.numeroPasso);
        numeroPasso.setText("Passo: "+idPasso);

        descSolucao = findViewById(R.id.descricaoPasso);
        // idPasso começa em 0, preciso somar 1 a ele para se adequar ao BD
        String descricaoBD = soluctionsList.get(idPasso-1).getTexto();
        descSolucao.setText(descricaoBD);

//        //Imagens ainda não selecionadas
//        imagemSolucao = findViewById(R.id.imagemSolucao);
//        imagemSolucao.setImageResource();
    }
}