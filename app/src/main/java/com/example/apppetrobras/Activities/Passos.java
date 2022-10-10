package com.example.apppetrobras.Activities;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

import com.bumptech.glide.Glide;
import com.example.Navigations.Drawer;
import com.example.Navigations.Tabs;
import com.example.apppetrobras.R;
import com.example.apppetrobras.Objects.PassosObj;
import com.example.apppetrobras.api.RetroFitClient;
import com.example.apppetrobras.databinding.LayoutPassosBinding;
import com.github.chrisbanes.photoview.PhotoView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.Date;
import java.util.List;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Passos extends Drawer {

    // Declaração das variáveis

    int idTitulo, idSolucao, idPasso, tipoProblema, qtdPassos;
    String tituloSolucao, titulo, check, titulosProblemas;
    FloatingActionButton callbtn;
    static int PERMISSION_CODE= 100;
    private static final int PERMISSION_REQUEST_CODE = 1;
    ProgressBar progressobar;
    Boolean itsNotFirstTime = false;


    Context context;

    TextView numeroPasso, nomeSolucao, descSolucao;
    ImageView btnSemAcesso;
    PhotoView imagemSolucao;

    List<PassosObj> passosObjList;
    Dialog mDialog;
    Call<List<PassosObj>> call;

    LayoutPassosBinding layoutPassosBinding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Boolean itsNotFirstTime = false;


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
        btnSemAcesso = findViewById(R.id.btnSemAcesso);

        // Requisição dos dados passados durante o intent
        tipoProblema = getIntent().getIntExtra("TIPO", 1);
        idTitulo = getIntent().getIntExtra("ID_TITULO",1);
        idSolucao = getIntent().getIntExtra("ID_SOLUCAO",1);
        tituloSolucao = getIntent().getStringExtra("TITULO_SOLUCAO");
        titulo = getIntent().getStringExtra("titulo");
        titulosProblemas = getIntent().getStringExtra("titulosProblemas");



        // Instanciação de variáveis chave
        context = this;
        idPasso = 1;
        String concatenar = Integer.toString(idTitulo) + Integer.toString(idSolucao);
        int numerojunto = Integer.parseInt(concatenar);

        // Resgata o check

        // Trata o check para o caso do usuário retornar às soluções inadequadamente
        trataCheck(3);

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

                thereIsNoPasso();
            }

            @Override
            public void onFailure(Call<List<PassosObj>> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void progressbarshow(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() { progressobar.setVisibility(View.VISIBLE); }
        }, 0);    }

    private void progressobarhide(){

        progressobar=findViewById(R.id.progressbar);

        if (itsNotFirstTime) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() { progressobar.setVisibility(View.INVISIBLE); }
            }, 3000);
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() { progressobar.setVisibility(View.INVISIBLE); }
            }, 300);

            itsNotFirstTime = true;
        }
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
            thereIsNoPasso();

        }
    }

    //vendo que chegou no ultimo passo e revelando os botoes de conseguiu e nao conseguiu
    public void thereIsNoPasso(){
        if (idPasso == qtdPassos) {
            ImageView right = findViewById(R.id.btnResolveu);
            ImageView wrong = findViewById(R.id.btnNaoResolveu);

            right.setVisibility(View.VISIBLE);
            wrong.setVisibility(View.VISIBLE);
        }
    }
    //função do botao de X
    public void dontGetMeWrong(View view){
        trataCheck(1);
        mudarTela();
    }

    //função do botao de correto
    public void dontGetMeRight(View view){
        trataCheck(2);
        finalizarSolucao();
    }

    //função do botao sem acesso
    public void dontGetMeLost(View view){

        trataCheck(3);
        mudarTela();

    }

    //função para abrir o popup
    public void abrirpopup(View view){
        mDialog = new Dialog(this);

        // Defini o click dentro do popup
        mDialog.setContentView(R.layout.popup_cadeado_solucoes);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDialog.show();

//        btnSemAcesso.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                mDialog.setContentView(R.layout.popup_cadeado_solucoes);
//                mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                mDialog.show();
//
//            }
//        });
    }

    public void inserirNaTela(){
        // Inserção dos Valores na tela
        progressbarshow();

        nomeSolucao.setText(tituloSolucao);

        numeroPasso.setText("Passo: "+idPasso);

        // idPasso começa em 0, preciso somar 1 a ele para se adequar ao BD
        String descricaoBD = passosObjList.get(idPasso-1).getTexto();
        descSolucao.setText(descricaoBD);

        // idPasso começa em 0, preciso somar 1 a ele para se adequar ao BD
//        String imagemBD = soluctionsList.get(idPasso-1).getUrl();
        String imagemBD = passosObjList.get(idPasso-1).getUrl();
        Glide.with(this)
                .load(imagemBD)
                .error(R.drawable.ic_error)
                .into(imagemSolucao);
        progressobarhide();

    }


    //função que guarda no bd o relatório
    public void iAmWhoKnocks(){


        SharedPreferences sharedPreferences = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        String nome = sharedPreferences.getString("nome", "");
        String chave = sharedPreferences.getString("chave", "");

        Call<ResponseBody> call = RetroFitClient
                .getInstance()
                .getAPI().postRelatorio(nome, chave, getTodaysDate(), tipoProblema, titulo,idTitulo,titulosProblemas,check);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(Passos.this, "Relatorio criado", Toast.LENGTH_LONG).show();

            }


            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(Passos.this, t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

        // Zera o check (Só pra garantir)
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("check", "");
        editor.apply();
    }



    public void trataCheck(int _estadoCheck){
        // Resgata check
        SharedPreferences sharedPreferences = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        check = sharedPreferences.getString("check","");

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

        // Atualização do check
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("check", check);
        editor.apply();
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
        Dialog mdialog;
        mdialog = new Dialog(this);
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
        check = holder;

        // Atualização do check
        SharedPreferences sharedPreferences = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("check", check);
        editor.apply();

        // Adição de um relatório no Banco de Dados
        iAmWhoKnocks();

        if (check.contains("2")) {
            // Redirecionamento para a tela de "parabéns"
            mdialog.setContentView(R.layout.popupparabens);
            mdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            mdialog.show();

            //temporario:
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(Passos.this, Tabs.class);
                    startActivity(intent);
                    finish();
                }
            }, 3500);
        }else {
            mdialog.setContentView(R.layout.layout_ligacao);
            mdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            mdialog.show();




        }

    }

    public String getTodaysDate() {
        return new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
    }


    public void retornarParaSolucoes(){
        // Redirecionamento para a tela do problema contendo os títulos das soluções
        Intent intent = new Intent(Passos.this, Solucoes.class);

        intent.putExtra("TIPO",tipoProblema);
        intent.putExtra("ID_TITULO",idTitulo);
        intent.putExtra("titulo", titulo);
        intent.putExtra("titulosProblemas", titulosProblemas);

        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        mudarTela();
    }

    public void ligacao(View view){


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                Intent intent = new Intent(Intent.ACTION_DIAL);
//                intent.setData(Uri.parse("tel:0123456789"));
//                startActivity(intent);
                makeCall("+5513991509119");

            }
        }, 100);


    }

    public void makeCall(String s)
    {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + s));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){

            requestForCallPermission();

        } else {
            startActivity(intent);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(context, Tabs.class);
                    startActivity(i);
                }
            }, 3000);


        }
    }
    public void requestForCallPermission()
    {

        if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.CALL_PHONE))
        {
        }
        else {

            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE},PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    makeCall("+551399150-9119");
                }
                break;
        }
    }


    public void fecharPopup(View view){
        mDialog.dismiss();
    }
}