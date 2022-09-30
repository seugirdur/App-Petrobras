package com.example.apppetrobras.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.Navigations.Drawer;
import com.example.apppetrobras.R;
import com.example.apppetrobras.Objects.PassosObj;
import com.example.apppetrobras.api.RetroFitClient;
import com.example.apppetrobras.databinding.LayoutPassosBinding;


import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Passos extends Drawer {

    // Declaração das variáveis
    int idTitulo, idSolucao, idPasso, tipoProblema, qtdPassos;
    String tituloSolucao, titulo, check, titulosProblemas;

    Context context;

    TextView numeroPasso, nomeSolucao, descSolucao;
    ImageView imagemSolucao;

    List<PassosObj> passosObjList;

    Call<List<PassosObj>> call;

    LayoutPassosBinding layoutPassosBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        layoutPassosBinding = LayoutPassosBinding.inflate(getLayoutInflater());
        setContentView(layoutPassosBinding.getRoot());
        allocateActivityTitle("Menu Principal");

        // Criação de variáveis para se referenciar aos intens do layout
        nomeSolucao = findViewById(R.id.nomeSolucao);
        nomeSolucao.setSelected(true);
        nomeSolucao.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        nomeSolucao.setSingleLine(true);
        numeroPasso = findViewById(R.id.numeroPasso);
        descSolucao = findViewById(R.id.descricaoPasso);
        imagemSolucao = findViewById(R.id.imagemSolucao);

        // Requisição dos dados passados durante o intent
        tipoProblema = getIntent().getIntExtra("TIPO", 1);
        idTitulo = getIntent().getIntExtra("ID_TITULO",1);
        idSolucao = getIntent().getIntExtra("ID_SOLUCAO",1);
        tituloSolucao = getIntent().getStringExtra("TITULO_SOLUCAO");
        titulo = getIntent().getStringExtra("titulo");
        check = getIntent().getStringExtra("CHECK");
        titulosProblemas = getIntent().getStringExtra("titulosProblemas");

        // Instanciação de variáveis chave
        context = this;
        idPasso = 1;
        String concatenar = Integer.toString(idTitulo) + Integer.toString(idSolucao);
        int numerojunto = Integer.parseInt(concatenar);



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



        call.enqueue(new Callback<List<PassosObj>>() {
            @Override
            public void onResponse(Call<List<PassosObj>> call, Response<List<PassosObj>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(context, response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                passosObjList = response.body();
                // Armazena o total de passos dessa solução
                qtdPassos = passosObjList.size();
                inserirNaTela();

            }

            @Override
            public void onFailure(Call<List<PassosObj>> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void rtrnPasso(View view){
        if (idPasso > 1) {
            idPasso-=1;
            inserirNaTela();
        }
    }

    public void proxPasso(View view){
        // verifica se há mais um passo então atualiza as informações na tela:
        if (idPasso < qtdPassos) {
            idPasso+=1;
            inserirNaTela();
        }
    }

    public void inserirNaTela(){
        // Inserção dos Valores na tela

        nomeSolucao.setText(tituloSolucao);

        numeroPasso.setText("Passo: "+idPasso);

        // idPasso começa em 0, preciso somar 1 a ele para se adequar ao BD
        String descricaoBD = passosObjList.get(idPasso-1).getTexto();
        descSolucao.setText(descricaoBD);

        // idPasso começa em 0, preciso somar 1 a ele para se adequar ao BD
//        String imagemBD = soluctionsList.get(idPasso-1).getUrl();
        String imagemBD = "";
        Glide.with(this)
                .load(imagemBD)
                .error(R.drawable.ic_error)
                .into(imagemSolucao);
    }

    public void solucionado(View v){
       trataCheck(2);
       Toast.makeText(context, ""+check, Toast.LENGTH_SHORT).show();
       mudarTela();
    }

    public void naoSolucionado(View v){
        trataCheck(1);
        Toast.makeText(context, ""+check , Toast.LENGTH_SHORT).show();
        mudarTela();
    }

    public void semAcesso(View v) {
        trataCheck(3);
        Toast.makeText(context, ""+check, Toast.LENGTH_SHORT).show();
        mudarTela();
    }



    public void trataCheck(int _estadoCheck){
        String holder = "";

        for (int i = 0; i < check.length(); i++){
            if (i != idSolucao-1){
                holder += check.charAt(i);
            }
            else{
                holder += _estadoCheck;
            }
        }

        check = holder;
    }

    public void mudarTela(){
        if (check.contains("0")){
            retornarParaSolucoes();
        }
        else {
            finalizarSolucao();
        }
    }

    private void finalizarSolucao() {

        // Trata a checagem interna para ser enviada ao Banco de Dados (substitui o 3 por 0)
        String holder = "";
        for (int i = 0; i < check.length(); i++){
            if (check.charAt(i) == '3'){
                holder += "0";
            }
            else {
                holder += check.charAt(i);
            }
        }

        // Adição de um relatório no Banco de Dados




        // Redirecionamento para a tela de "parabéns"
        //temporario:
        Intent intent = new Intent(Passos.this, Inicio.class);

    }

    public void retornarParaSolucoes(){
        // Redirecionamento para a tela do problema contendo os títulos das soluções
        Intent intent = new Intent(Passos.this, Solucoes.class);

        // Definição de valores que serão redirecionados
        intent.putExtra("TIPO",tipoProblema);
        intent.putExtra("ID_TITULO", idTitulo);
        intent.putExtra("titulo", titulo);
        intent.putExtra("CHECK",check);
        intent.putExtra("titulosProblemas",titulosProblemas);
        startActivity(intent);
    }
}