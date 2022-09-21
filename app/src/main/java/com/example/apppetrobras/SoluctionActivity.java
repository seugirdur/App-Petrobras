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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SoluctionActivity extends AppCompatActivity {

    // Declaração das variáveis
    int idTitulo, idSolucao, tipoProblema, idPasso;

    Context context;

    TextView numeroPasso, nomeSolucao, descSolucao;
    ImageView imagemSolucao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soluction);

        tipoProblema = getIntent().getIntExtra("TIPO", 1);
        idTitulo = getIntent().getIntExtra("ID_TITULO",1);
        idSolucao = getIntent().getIntExtra("ID_SOLUCAO",1);
        idPasso = getIntent().getIntExtra("PASSO",1);

        Toast.makeText(this, tipoProblema+"-"+idTitulo+"-"+idSolucao+"-"+idPasso, Toast.LENGTH_SHORT).show();

        String concatenar = Integer.toString(idTitulo) + Integer.toString(idSolucao);
        int numerojunto = Integer.parseInt(concatenar);

        //colocar na tela
        numeroPasso = findViewById(R.id.numeroPasso);
        numeroPasso.setText("Passo: "+idPasso);

        context = this;

        Call<List<Soluctions>> call;

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

                List<Soluctions> soluctionsList = response.body();




            }

            @Override
            public void onFailure(Call<List<Soluctions>> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        // pega o item no BD da tabela TextoTipo que tenha: tipo-idTitulo-idSolucao-idPasso(1-1-1-1)
//        call = RetroFitClient
//                .getInstance()
//                .getAPI()
//                .getLentidao(idTitulo);

        // Inserção dos Valores na tela
//        nomeSolucao = findViewById(R.id.nomeSolucao);
//        nomeSolucao.setText("Nome Solucao");

        numeroPasso = findViewById(R.id.numeroPasso);
        numeroPasso.setText("Passo: "+idPasso);

//        descSolucao = findViewById(R.id.descricao_passo);
//        descSolucao.setText("Descrição do passo");
    }

    public void btnCancel(View view){
        if (idPasso > 1) { restartPage(idPasso-1); }
    }

    public void btnCheck(View view){
        // verificar se há mais um passo e executar a seguinte linha:
        restartPage(idPasso+1);
    }

    public void restartPage(int step){
        // Reinicia a activity passando um novo passo

        Intent intent = getIntent();
        finish();

        // Definição de valores que serão redirecionados
        intent.putExtra("TIPO",tipoProblema);
        intent.putExtra("ID_TITULO", idTitulo);
        // position começa em 0, por isso é necessário adicionar 1 a ele
        intent.putExtra("ID_SOLUCAO", idSolucao);
        // A atualização do passo:
        intent.putExtra("PASSO", step);
        startActivity(intent);
    }
}