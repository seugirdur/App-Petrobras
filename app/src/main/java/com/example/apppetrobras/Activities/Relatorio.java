package com.example.apppetrobras.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Navigations.Administrador;
import com.example.Navigations.Drawer;
import com.example.apppetrobras.Adapters.RecyclerViewAdapter;
import com.example.apppetrobras.Adapters.RelatorioAdapter;
import com.example.apppetrobras.Objects.EtapasRelatorioObj;
import com.example.apppetrobras.Objects.ProblemasObj;
import com.example.apppetrobras.Objects.RelatorioObj;
import com.example.apppetrobras.Objects.SolucoesObj;
import com.example.apppetrobras.R;
import com.example.apppetrobras.api.RetroFitClient;
import com.example.apppetrobras.databinding.LayoutPassosBinding;
import com.example.apppetrobras.databinding.LayoutRelatorioBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.example.apppetrobras.fragments.RecyclerViewInteface;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.example.apppetrobras.R;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.*;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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

    // declaring width and height
    // for our PDF file.
    int pageHeight = 1120;
    int pagewidth = 792;

    // creating a bitmap variable
    // for storing our images
  //  Bitmap bmp, scaledbmp;

    // constant code for runtime permissions
    private static final int PERMISSION_REQUEST_CODE = 200;
    private RecyclerView recyclerview;
    private String checking;
    private Context context;
    private RecyclerViewInteface recyclerViewInteface;
    private List<EtapasRelatorioObj> items = new ArrayList<>();
    private boolean funciona = false;
    Document document;
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












        //= sharedPreferences.getString("nome", "");

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
                        secao.setText(cRelatorio.getSecao());
                        problema.setText(cRelatorio.getTitulo());
                        TextView nome = findViewById(R.id.nome_usuario);
                        TextView data = findViewById(R.id.data_atual);
                        textData = cRelatorio.getDataProcesso();
                        nomeRel = cRelatorio.getNome();
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


        }else {
                Toast.makeText(Relatorio.this, "Esse problema já foi solucionado", Toast.LENGTH_LONG).show();

            }
        } else {
            Toast.makeText(Relatorio.this, "Você não é administrador", Toast.LENGTH_LONG).show();

        }

    }


    //declarar a imagem do "se deu certo ou errado"
    public int check(int a){
        if(a==0){
            return R.drawable.ic_cancel_circle;
        }
        else {
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



    // Botão de download presente no FAB
    //   private void makePDF(){


        public void makePdf(View view) {

            if (checarPermissao()) {

            } else {
                pedirPermissao();
            }


            PdfDocument pdfDocument = new PdfDocument();
            Paint title = new Paint();

            // we are adding page info to our PDF file
            // in which we will be passing our pageWidth,
            // pageHeight and number of pages and after that
            // we are calling it to create our PDF.
            Bitmap bmp, scaledbmp;
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.aset_logo);
            scaledbmp = Bitmap.createScaledBitmap(bmp, 140, 140, false);
            PdfDocument.PageInfo mypageInfo = new PdfDocument.PageInfo.Builder(pagewidth, pageHeight, 1).create();

            // below line is used for setting
            // start page for our PDF file.
            PdfDocument.Page myPage = pdfDocument.startPage(mypageInfo);

            // creating a variable for canvas
            // from our page of PDF.
            Canvas canvas = myPage.getCanvas();
            Paint paint = new Paint();

            // below line is used to draw our image on our PDF file.
            // the first parameter of our drawbitmap method is
            // our bitmap
            // second parameter is position from left
            // third parameter is position from top and last
            // one is our variable for paint.
            canvas.drawBitmap(scaledbmp, 56, 40, paint);

            // below line is used for adding typeface for
            // our text which we will be adding in our PDF file.
            title.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));

            // below line is used for setting text size
            // which we will be displaying in our PDF file.
            title.setTextSize(15);

            // below line is sued for setting color
            // of our text inside our PDF file.
            title.setColor(ContextCompat.getColor(this, R.color.black));

            // below line is used to draw text in our PDF file.
            // the first parameter is our text, second parameter
            // is position from start, third parameter is position from top
            // and then we are passing our variable of paint which is title.

            canvas.drawText(nomeRel, 209, 80, title);
            canvas.drawText("Data: " + textData, 209, 100, title);
            if (checking.contains("2")){canvas.drawText("Solucionado ", 209, 120, title);}
            else{canvas.drawText("Não solucionado ", 209, 120, title);}

            // similarly we are creating another text and in this
            // we are aligning this text to center of our PDF file.
            title.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            title.setColor(ContextCompat.getColor(this, R.color.purple_200));
            title.setTextSize(15);

            // below line is used for setting
            // our text to center of PDF.
            title.setTextAlign(Paint.Align.CENTER);
            for(int i=0; items.l)//
            canvas.drawText("This is sample document which we have created.", 396, 560, title);

            // after adding all attributes to our
            // PDF file we will be finishing our page.
            pdfDocument.finishPage(myPage);


            String data = textData.replace("/", "");
            // below line is used to set the name of
            // our PDF file and its path.
            String nomeArquivo = "Relatorio" + data;
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download/"
                     + nomeArquivo + ".pdf");

            try {
                // after creating a file name we will
                // write our PDF file to that location.
                pdfDocument.writeTo(new FileOutputStream(file));

                // below line is to print toast message
                // on completion of PDF generation.
                Toast.makeText(Relatorio.this, "PDF " + nomeArquivo  +  " criado com sucesso. Cheque sua pasta Download", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                // below line is used
                // to handle error
                Toast.makeText(Relatorio.this, "Erro em baixar o arquivo.", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
            // after storing our pdf to that
            // location we are closing our PDF file.
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




    @Override
    public void onItemClick(int position) {

    }
}