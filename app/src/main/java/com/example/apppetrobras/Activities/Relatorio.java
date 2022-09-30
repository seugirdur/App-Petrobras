package com.example.apppetrobras.Activities;

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

import com.example.apppetrobras.Objects.RelatorioObj;
import com.example.Navigations.Drawer;
import com.example.apppetrobras.R;
import com.example.apppetrobras.api.RetroFitClient;
import com.example.apppetrobras.databinding.LayoutRelatorioBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Relatorio extends Drawer {

    FloatingActionButton add_icon, download_icon, observacoes_icon;
    Animation fabOpen, fabClose, rotateForward, rotateBackward;
    Dialog mDialog;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    LayoutRelatorioBinding relatorioLayoutBinding;

    boolean isOpen = false; // by default it is false

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        relatorioLayoutBinding = LayoutRelatorioBinding.inflate(getLayoutInflater());
        setContentView(relatorioLayoutBinding.getRoot());
        allocateActivityTitle("Histórico");

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

        String chave = sharedPreferences.getString("nome", "");

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

                List<RelatorioObj> relatorioObjList = response.body();
                RelatorioObj relatorioObj = relatorioObjList.get(0);

                int idRelatorio = relatorioObj.getIdRelatorio();
                String Nome = relatorioObj.getNome();
                String Chave = relatorioObj.getChave();
                String DataProcesso = relatorioObj.getDataProcesso();
                String secao = relatorioObj.getSecao();
                String Titulo = relatorioObj.getTitulo();
                int SolucaoStop = relatorioObj.getSolucaostop();
                int Funcionou = relatorioObj.getFuncionou();
                String Made_check = relatorioObj.getMade_check();


                Toast.makeText(Relatorio.this, secao, Toast.LENGTH_SHORT).show();
                Toast.makeText(Relatorio.this, "secao", Toast.LENGTH_SHORT).show();

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


}