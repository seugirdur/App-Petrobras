package com.example.apppetrobras.Activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Navigations.Configuracoes;
import com.example.Navigations.Drawer;
import com.example.apppetrobras.Objects.CadastroObj;
import com.example.apppetrobras.Objects.LoginObj;
import com.example.apppetrobras.Objects.PerfilObj;
import com.example.apppetrobras.R;
import com.example.apppetrobras.api.RetroFitClient;
import com.example.apppetrobras.databinding.LayoutPassosBinding;
import com.example.apppetrobras.databinding.LayoutPerfilAtualizarBinding;
import com.example.apppetrobras.fragments.Ajuda_fragment;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilAtualizar extends Drawer {

    TextView nome1,num_chave;
    EditText nomecompleto, num_tel, email1;
    Button btn_tela_perfil_update;
    LayoutPerfilAtualizarBinding layoutPerfilAtualizarBinding;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layoutPerfilAtualizarBinding = LayoutPerfilAtualizarBinding.inflate(getLayoutInflater());
        setContentView(layoutPerfilAtualizarBinding.getRoot());
        allocateActivityTitle("Editar Perfil");


        SharedPreferences sharedPreferences = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        String nome = sharedPreferences.getString("nome", "");
        String tel = sharedPreferences.getString("tel", "");
        String email = sharedPreferences.getString("email", "");
        String chave = sharedPreferences.getString("chave", "");


        String[] fullNameArray = nome.split("\\s+");
        String firstName = fullNameArray[0];
        nome1=findViewById(R.id.nome_login);
        nome1.setText(firstName);

        nomecompleto = findViewById(R.id.nomecompletoupdate);
        nomecompleto.setHint(nome);

        num_tel = findViewById(R.id.num_telupdate);
        num_tel.setHint(tel);

        email1 = findViewById(R.id.emailupdate);
        email1.setHint(email);

        num_chave = findViewById(R.id.num_chave);
        num_chave.setHint(chave);

        num_tel .addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str=num_tel .getText().toString();
                int textLength=num_tel .getText().length();
                if (textLength == 1) {

                    if (!str.contains("(")) {
                        num_tel .setText(new StringBuilder(num_tel.getText().toString()).insert(str.length() - 1, "(").toString());
                        num_tel.setSelection(num_tel .getText().length());
                    }
                }
                if (textLength == 4) {

                    if (!str.contains(")")) {
                        num_tel .setText(new StringBuilder(num_tel.getText().toString()).insert(str.length() - 1, ")").toString());
                        num_tel.setSelection(num_tel .getText().length());
                    }
                }

                if (textLength == 5) {

                    if (!str.contains(" ")) {
                        num_tel .setText(new StringBuilder(num_tel.getText().toString()).insert(str.length() - 1, " ").toString());
                        num_tel.setSelection(num_tel .getText().length());
                    }
                }
                if (textLength == 11) {

                    if (!str.contains("-")) {
                        num_tel .setText(new StringBuilder(num_tel.getText().toString()).insert(str.length() - 1, "-").toString());
                        num_tel.setSelection(num_tel .getText().length());
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

//        String emailatualizado = String.valueOf(email1.getText());





        btn_tela_perfil_update = findViewById(R.id.btn_tela_perfil_update);

        btn_tela_perfil_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nomeatualizado = nomecompleto.getText().toString();
                String telatualizado = num_tel.getText().toString();
                String emailatualizado = email1.getText().toString();
//        String telatualizado = String.valueOf(num_tel.getText());





                if(nomeatualizado.isEmpty()){
                    atualizate(nome, telatualizado, emailatualizado,chave);

                }
                if(telatualizado.isEmpty()){
                    atualizate(nomeatualizado,tel,emailatualizado,chave);
                }

                if(emailatualizado.isEmpty()){
                    atualizate(nomeatualizado,telatualizado,email,chave);
                }
                if(emailatualizado.isEmpty() && telatualizado.isEmpty()){
                    atualizate(nomeatualizado,tel,email,chave);
                }

                if(emailatualizado.isEmpty() && nomeatualizado.isEmpty()){
                    atualizate(nome,telatualizado,email,chave);
                }
                if(telatualizado.isEmpty() && nomeatualizado.isEmpty()){
                    atualizate(nome,tel,emailatualizado,chave);
                }
                if(nomeatualizado.isEmpty() && emailatualizado.isEmpty() && telatualizado.isEmpty()){
                    atualizate(nome,tel,email,chave);
                }

                if(!nomeatualizado.isEmpty() && !emailatualizado.isEmpty() && !telatualizado.isEmpty()){
                    atualizate(nomeatualizado, telatualizado, emailatualizado, chave);}


                btn_tela_perfil_update.setEnabled(false);

            }
        });




    }



    private void atualizate(String nome, String tel,String email, String chave){
//        PerfilObj perfilObj = new PerfilObj();
//
//        perfilObj.setNome(nome);
//        perfilObj.setEmail(email);
//        perfilObj.setTelefone(tel);


        Call<List<PerfilObj>> call = RetroFitClient
                .getInstance()
                .getAPI()
                .updateUser(nome, tel, email, chave);

        call.enqueue(new Callback<List<PerfilObj>>() {
            @Override
            public void onResponse(Call<List<PerfilObj>> call, Response<List<PerfilObj>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(PerfilAtualizar.this, response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<PerfilObj> perfilObjList = response.body();
                PerfilObj perfilObj = perfilObjList.get(0);

                String nome = perfilObj.getNome();
                String email = perfilObj.getEmail();
                String tel = perfilObj.getTelefone();
                Toast.makeText(PerfilAtualizar.this, "Informações alteradas com sucesso!", Toast.LENGTH_SHORT).show();

                SharedPreferences sharedPreferences = getSharedPreferences(
                        getString(R.string.preference_file_key), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("nome", nome);
                editor.putString("email", email);
                editor.putString("tel", tel);
                editor.apply();

            }

            @Override
            public void onFailure(Call<List<PerfilObj>> call, Throwable t) {
                Toast.makeText(PerfilAtualizar.this, "Cadastro atualizado com sucesso!", Toast.LENGTH_SHORT).show();
                SharedPreferences sharedPreferences = getSharedPreferences(
                        getString(R.string.preference_file_key), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("nome", nome);
                editor.putString("email", email);
                editor.putString("tel", tel);
                editor.apply();
                Intent intent = new Intent(PerfilAtualizar.this, Configuracoes.class);
                startActivity(intent);
            }
        });





//        Call<ResponseBody> call = RetroFitClient
//                .getInstance()
//                .getAPI()
//                .createUser(nome, email, tel, dataNasc, chave, senha);
//
//        call.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                try {
//                    String body = response.body().string();
//                    Toast.makeText(Cadastro.this, body, Toast.LENGTH_LONG).show();
//                }catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Toast.makeText(Cadastro.this, t.getMessage(), Toast.LENGTH_LONG).show();
//
//            }
//        });

    }
}