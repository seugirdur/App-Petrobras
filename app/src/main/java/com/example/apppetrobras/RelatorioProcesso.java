package com.example.apppetrobras;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apppetrobras.api.RetroFitClient;
import com.example.apppetrobras.models.UserAPI;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RelatorioProcesso extends AppCompatActivity {

    FloatingActionButton add_icon, download_icon, observacoes_icon;
    Animation fabOpen, fabClose, rotateForward, rotateBackward;
    Dialog mDialog;
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    boolean isOpen = false; // by default it is false

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio_processo);

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
                Toast.makeText(RelatorioProcesso.this, "download clicked", Toast.LENGTH_SHORT).show();
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

        Call<List<CRelatorio>> call = RetroFitClient
                .getInstance()
                .getAPI()
                .getRelatorio(chave);

        call.enqueue(new Callback<List<CRelatorio>>() {
            @Override
            public void onResponse(Call<List<CRelatorio>> call, Response<List<CRelatorio>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(RelatorioProcesso.this, response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<CRelatorio> cRelatorioList = response.body();
                CRelatorio cRelatorio = cRelatorioList.get(0);
                String secao = cRelatorio.getSecao();

                Toast.makeText(RelatorioProcesso.this, secao, Toast.LENGTH_SHORT).show();
                Toast.makeText(RelatorioProcesso.this, "secao", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<List<CRelatorio>> call, Throwable t) {

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