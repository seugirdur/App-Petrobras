package com.example.apppetrobras.Activities;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Navigations.Administrador;
import com.example.Navigations.Drawer;
import com.example.apppetrobras.Adapters.RelatorioAdapter;
import com.example.apppetrobras.Objects.EtapasRelatorioObj;
import com.example.apppetrobras.Objects.RelatorioObj;
import com.example.apppetrobras.Objects.SolucoesObj;
import com.example.apppetrobras.R;
import com.example.apppetrobras.api.RetroFitClient;

import com.example.apppetrobras.databinding.LayoutRelatorioBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.example.apppetrobras.fragments.RecyclerViewInteface;

import android.graphics.Bitmap;
import android.os.Environment;

import java.io.FileOutputStream;

import android.content.pm.PackageManager;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;



import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;

import java.io.IOException;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;



public class Relatorio extends Drawer implements RecyclerViewInteface{

    int pageHeight = 1120;
    int pagewidth = 792;
    private static final int PERMISSION_REQUEST_CODE = 200;

    private RecyclerView recyclerview;
    private String checking;
    private Context context;
    private RecyclerViewInteface recyclerViewInteface;
    private List<EtapasRelatorioObj> items = new ArrayList<>();
    private boolean funciona = false;
    private String problemaApi;
    private String secaoApi;

    FloatingActionButton add_icon, download_icon, concludeicon;
    Animation fabOpen, fabClose, rotateForward, rotateBackward;
    int idRelatorio, notnotlmao;
    Dialog mDialog;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    LayoutRelatorioBinding layoutRelatorioBinding;
    private String textData, nomeRel;



    boolean isOpen = false; // by default it is false

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layoutRelatorioBinding = LayoutRelatorioBinding.inflate(getLayoutInflater());
        setContentView(layoutRelatorioBinding.getRoot());
        allocateActivityTitle("Relatório");
        idRelatorio = getIntent().getIntExtra("idRelatorio",6);
        notnotlmao = getIntent().getIntExtra("notnotlmao",0);


        add_icon = (FloatingActionButton) findViewById(R.id.add_icon);
        download_icon = (FloatingActionButton) findViewById(R.id.download_icon);
        concludeicon = (FloatingActionButton) findViewById(R.id.concludeicon);
        mDialog = new Dialog(this);
        settingTheName();




        // animations
        fabOpen = AnimationUtils.loadAnimation(this, R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(this, R.anim.fab_close);

        rotateForward = AnimationUtils.loadAnimation(this, R.anim.rotate_forward);
        rotateBackward = AnimationUtils.loadAnimation(this, R.anim.rotate_backward);

        // FAB chamando a função "animateFab()"
        add_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                animateFab();


            }
        });




        Call<List<RelatorioObj>> call = RetroFitClient
                .getInstance()
                .getAPI()
                .getRelatorioUnico(idRelatorio);




        call.enqueue(new Callback<List<RelatorioObj>>() {
            @Override
            public void onResponse(Call<List<RelatorioObj>> call, Response<List<RelatorioObj>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(Relatorio.this, response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }


                //pegar dados da api
                List<RelatorioObj> cRelatorioList = response.body();
                RelatorioObj cRelatorio = cRelatorioList.get(0);

                //pegar a quantidade de soluções testadas
                //pegar o size do Problems da Api
                //string para desfragmentar em vários int
                checking = cRelatorio.getMade_check();
                int quant = checking.length();



                int idTitulo = cRelatorio.getIdTitulo();
                int idSecao = cRelatorio.getIdSecao();

                Call<List<SolucoesObj>> call2;

                switch (idSecao){
                    case 1:
                    default:
                        call2 = RetroFitClient
                                .getInstance()
                                .getAPI()
                                .getLentidao(idTitulo);
                        break;
                    case 2:
                        call2 = RetroFitClient
                                .getInstance()
                                .getAPI()
                                .getInternet(idTitulo);
                        break;
                    case 3:
                        call2 = RetroFitClient
                                .getInstance()
                                .getAPI()
                                .getEquipamentos(idTitulo);
                        break;
                    case 4:
                        call2 = RetroFitClient
                                .getInstance()
                                .getAPI()
                                .getOutros(idTitulo);
                        break;
                }



                call2.enqueue(new Callback<List<SolucoesObj>>() {
                    @Override
                    public void onResponse(Call<List<SolucoesObj>> call, Response<List<SolucoesObj>> response) {
                        if (!response.isSuccessful()){
                            Toast.makeText(Relatorio.this, response.code(), Toast.LENGTH_SHORT).show();
                            return;
                        }

                        List<SolucoesObj> problemsList;
                        problemsList = response.body();
                        checking = cRelatorio.getMade_check();
                        String realizado;



                        //for para adicionar cada solução testada numa lista
                        for(int i=0; i<quant; i++){

                            int substring;

                            if (i==quant){substring = Integer.parseInt(checking.substring(i));}else {substring = Integer.parseInt(checking.substring(i, i + 1));}
                            SolucoesObj solucao = problemsList.get(i);
                            funciona = checkResolvido(substring);

                            //vai enviar o nome da solução, se foi feito ou não e imagem de reforço
                            EtapasRelatorioObj itemRel = new EtapasRelatorioObj(solucao.getTituloSolucao(), feito(substring), check(substring));
                            items.add(itemRel);
                            if(funciona){break;}
                        }

                        //configuração da recyclerview
                        recyclerview = findViewById(R.id.recicle);
                        RelatorioAdapter adapter = new RelatorioAdapter(items);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                        recyclerview.setLayoutManager(layoutManager);
                        recyclerview.setAdapter(adapter);
                        recyclerview.setHasFixedSize(true);

                        //alterando o texto da barra fixa com informações da API
                        TextView solucionado = findViewById(R.id.resultado_processo);
                        TextView secao = findViewById(R.id.txtSecao);
                        TextView problema = findViewById(R.id.txtProblema);
                        problemaApi = cRelatorio.getTitulo();
                        secaoApi = cRelatorio.getSecao();
                        secao.setText(secaoApi);
                        problema.setText(problemaApi);;
                        TextView nome = findViewById(R.id.nome_usuario);
                        TextView data = findViewById(R.id.data_atual);
                        textData = cRelatorio.getDataProcesso();
                        nomeRel = cRelatorio.getNome();
                        //String textData = "310505";
                        data.setText(textData);
                        nome.setText(nomeRel);
                        if(funciona){solucionado.setText("Solucionado");}else{solucionado.setText("Não solucionado");}



                    }

                    @Override
                    public void onFailure(Call<List<SolucoesObj>> call, Throwable t) {

                    }
                });




            }

            @Override
            public void onFailure(Call<List<RelatorioObj>> call, Throwable t) {

            }
        });


    }

    private int areYouAdmin() {
        SharedPreferences sharedPreferences = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        int LordVader = sharedPreferences.getInt("isAdmin", 0);

        return LordVader;
    }


    private void settingTheName() {

        TextView nome_usuario;
        nome_usuario=findViewById(R.id.nome_usuario);


        SharedPreferences sharedPreferences = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        String SayMyName = sharedPreferences.getString("nome", "");

        nome_usuario.setText(SayMyName);

    }

    public String sayMyName() {

        SharedPreferences sharedPreferences = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        String SayMyName = sharedPreferences.getString("nome", "");

        return SayMyName;

    }


    // Método para o FAB chamar os botões presentes, realizando sua animação de rotação.
    private void animateFab() {
        if (isOpen) {

            add_icon.startAnimation(rotateBackward);
            download_icon.startAnimation(fabClose);
            concludeicon.startAnimation(fabClose);
            download_icon.setClickable(false);
            concludeicon.setClickable(false);
            isOpen=false;
//
        }
        else {

            add_icon.startAnimation(rotateForward);
            download_icon.startAnimation(fabOpen);
            concludeicon.startAnimation(fabOpen);
            download_icon.setClickable(true);
            concludeicon.setClickable(true);
            isOpen=true;


        }

    }

    public void concludeRelatorio(View view){
        SharedPreferences sharedPreferences = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        String SayMyName = sharedPreferences.getString("nome", "").toUpperCase(Locale.ROOT);
        int isAdmin = sharedPreferences.getInt("isAdmin", 0);


        if (isAdmin==1) {

            if (notnotlmao==1){

            Call<ResponseBody> call = RetroFitClient
                    .getInstance()
                    .getAPI()
                    .finishRelatorio(SayMyName, idRelatorio);

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        String body = response.body().string();
                        Toast.makeText(Relatorio.this, "Finalizado com sucesso", Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(Relatorio.this, "Não foi possível finalizar", Toast.LENGTH_LONG).show();

                }
            });
            Intent intent = new Intent(this, Administrador.class);
            startActivity(intent);


        } else if(notnotlmao==2) {
                Toast.makeText(Relatorio.this, "Só é possível finalizar relatórios na tela de Administrador.", Toast.LENGTH_LONG).show();

            }
            else {
                Toast.makeText(Relatorio.this, "Esse problema já foi solucionado", Toast.LENGTH_LONG).show();

            }
        } else {
            Toast.makeText(Relatorio.this, "Você não é administrador", Toast.LENGTH_LONG).show();

        }

    }


    //declarar a imagem do "se deu certo ou errado"
    public int check(int check){
        switch(check){
            case 0:
            default:
                return R.drawable.ic_cancel_circle;
            case 1:
                return R.drawable.ic_asterisco;
            case 2:
                return R.drawable.ic_check_circle;
        }
    }

    //declarar mensagem se não foi realizado
    public String feito(int a){
        if(a==0){
            return "Não realizado";
        }
        else {
            return "Realizado";
        }
    }


    //declarar se o problem foi resolvido
    public boolean checkResolvido(int a){
        if(a == 2){
            return true;
        }
        return false;
    }

    public void makePdf(View view) {

        if (checarPermissao()) {

        } else {
            pedirPermissao();
        }


        PdfDocument pdfDocument = new PdfDocument();
        Paint title = new Paint();

        //declaracoes dos recursos que serão utilizados no PDF
        Bitmap bmp, scaledbmp;

        //declarando a imagem do bpm
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.aset_logo);
        scaledbmp = Bitmap.createScaledBitmap(bmp, 140, 140, false);
        PdfDocument.PageInfo mypageInfo = new PdfDocument.PageInfo.Builder(pagewidth, pageHeight, 1).create();
        PdfDocument.Page myPage = pdfDocument.startPage(mypageInfo);
        Canvas canvas = myPage.getCanvas();
        Paint paint = new Paint();


        //criação da imagem
        canvas.drawBitmap(scaledbmp, 56, 40, paint);

        //dando a configurações do texto
        title.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
        title.setTextSize(15);
        title.setColor(ContextCompat.getColor(this, R.color.black));

        //escrevendo o texto de informações gerais no pdf
        // texto, posicção em x, posição em y, var com configurações do texto
        canvas.drawText(nomeRel, 209, 80, title);
        canvas.drawText("Data: " + textData, 209, 100, title);
        if (checking.contains("2")){canvas.drawText("Solucionado ", 209, 120, title);}
        else{canvas.drawText("Não solucionado ", 209, 120, title);}


        title.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        title.setColor(ContextCompat.getColor(this, R.color.black));
        title.setTextSize(17);

        canvas.drawText("Problema: " + problemaApi, 56, 220, title);
        title.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        title.setColor(ContextCompat.getColor(this, R.color.black));
        title.setTextSize(20);
        title.setTextAlign(Paint.Align.LEFT);


        //for com cada etapa do relatório
        for(int i=0; i< items.size();i++){
            EtapasRelatorioObj etapa = items.get(i);
            title.setColor(ContextCompat.getColor(this, R.color.black));
            canvas.drawText(etapa.getTitulo(), 56, 300+(i*50), title);
            switch(etapa.getImagem()) {
                case R.drawable.ic_cancel_circle:
                    title.setColor(ContextCompat.getColor(this, R.color.red));
                    break;
                  case R.drawable.ic_asterisco :
                     title.setColor(ContextCompat.getColor(this, R.color.Amarelo_Petrobras));
                        break;
                case R.drawable.ic_check_circle:
                    title.setColor(ContextCompat.getColor(this, R.color.Verde_Petrobras));
                    break;
                default:
                    break;
            }
            canvas.drawText(etapa.getSubtitulo(), 56, 320+(i*50), title);
        }


        // colocando todas as alterações no pdf
        pdfDocument.finishPage(myPage);

        //declarando um título do pdf
        String data = textData.replace("/", "");
        String horario = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
        horario = horario.replace(":", "");

        //criando arquivo pdf
        String nomeArquivo = "Relatorio_" + data + "_" + horario;
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download/"
                + nomeArquivo + ".pdf");

        //escrevendo o pdf criando dentro do arquivo
        try {

            pdfDocument.writeTo(new FileOutputStream(file));


            Toast.makeText(Relatorio.this, nomeArquivo  +  " criado. Cheque sua pasta Downloads.", Toast.LENGTH_LONG).show();
        } catch (IOException e) {

            Toast.makeText(Relatorio.this, "Erro em baixar o arquivo.", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        pdfDocument.close();
    }

    private boolean checarPermissao() {
        // checando as permissões
        int p1 = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int p2 = ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);
        return p1 == PackageManager.PERMISSION_GRANTED && p2 == PackageManager.PERMISSION_GRANTED;
    }

    private void pedirPermissao() {
        // pedir permissão
        ActivityCompat.requestPermissions(this, new String[]{WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
    }

    private boolean checarVeracidade(int a){
        switch(a){
            case 0:
                return false;
            case 1:
                return false;
            case 2:
                return true;
            default:
                return false;
        }
    }


    @Override
    public void onItemClick(int position) {

    }
}