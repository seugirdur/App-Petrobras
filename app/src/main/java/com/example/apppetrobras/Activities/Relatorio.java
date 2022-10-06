package com.example.apppetrobras.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

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


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;




public class Relatorio extends Drawer implements RecyclerViewInteface{

    private RecyclerView recyclerview;
    private String checking;
    private Context context;
    private RecyclerViewInteface recyclerViewInteface;
    private List<EtapasRelatorioObj> items = new ArrayList<>();
    private boolean funciona = false;
    FloatingActionButton add_icon, download_icon, observacoes_icon;
    Animation fabOpen, fabClose, rotateForward, rotateBackward;
    Dialog mDialog;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    LayoutRelatorioBinding layoutRelatorioBinding;

    boolean isOpen = false; // by default it is false

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layoutRelatorioBinding = LayoutRelatorioBinding.inflate(getLayoutInflater());
        setContentView(layoutRelatorioBinding.getRoot());
        allocateActivityTitle("Relatório");


        add_icon = (FloatingActionButton) findViewById(R.id.add_icon);
        download_icon = (FloatingActionButton) findViewById(R.id.download_icon);
        observacoes_icon = (FloatingActionButton) findViewById(R.id.observacoes_icon);
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

        // Botão de download presente no FAB
        download_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFab();
                Toast.makeText(Relatorio.this, "download clicked", Toast.LENGTH_SHORT).show();
            }
        });


        // Botão de observações presente no FAB chamando o seu popup de observações
        observacoes_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mDialog.setContentView(R.layout.popup_observacoes);
                mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                mDialog.show();

            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        String chave = "D8X0";
        //= sharedPreferences.getString("nome", "");

        Call<List<RelatorioObj>> call = RetroFitClient
                .getInstance()
                .getAPI()
                .getRelatorio(chave);




        call.enqueue(new Callback<List<RelatorioObj>>() {
            @Override
            public void onResponse(Call<List<RelatorioObj>> call, Response<List<RelatorioObj>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(Relatorio.this, response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }


                //pegar dados da api
                List<RelatorioObj> cRelatorioList = response.body();
                RelatorioObj cRelatorio = cRelatorioList.get(1);

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
                        String textData = cRelatorio.getDataProcesso();
                        //String textData = "310505";
                        textData = textData.substring(0,2) + "/" + textData.substring(2,4) + "/20" + textData.substring(4);
                        data.setText(textData);
                        nome.setText(cRelatorio.getNome());
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

    private void settingTheName() {

        TextView nome_usuario;
        nome_usuario=findViewById(R.id.nome_usuario);


        SharedPreferences sharedPreferences = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        String SayMyName = sharedPreferences.getString("nome", "");

        nome_usuario.setText(SayMyName);

    }


    // Método para o FAB chamar os botões presentes, realizando sua animação de rotação.
    private void animateFab() {
        if (isOpen) {

            add_icon.startAnimation(rotateBackward);
            download_icon.startAnimation(fabClose);
            observacoes_icon.startAnimation(fabClose);
            download_icon.setClickable(false);
            observacoes_icon.setClickable(false);
            isOpen=false;

        }
        else {

            add_icon.startAnimation(rotateForward);
            download_icon.startAnimation(fabOpen);
            observacoes_icon.startAnimation(fabOpen);
            download_icon.setClickable(true);
            observacoes_icon.setClickable(true);
            isOpen=true;


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


    @Override
    public void onItemClick(int position) {

    }
}