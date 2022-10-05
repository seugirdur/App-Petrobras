package com.example.apppetrobras.Activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Navigations.Drawer;
import com.example.apppetrobras.Objects.CadastroObj;
import com.example.apppetrobras.Objects.LoginObj;
import com.example.apppetrobras.Objects.PerfilObj;
import com.example.apppetrobras.R;
import com.example.apppetrobras.api.RetroFitClient;
import com.example.apppetrobras.databinding.LayoutPassosBinding;
import com.example.apppetrobras.databinding.LayoutPerfilAtualizarBinding;

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
        setContentView(R.layout.layout_perfil_atualizar);

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


//        String emailatualizado = String.valueOf(email1.getText());





        btn_tela_perfil_update = findViewById(R.id.btn_tela_perfil_update);

        btn_tela_perfil_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nomeatualizado = nomecompleto.getText().toString();
                String telatualizado = num_tel.getText().toString();
                String emailatualizado = email1.getText().toString();
//        String telatualizado = String.valueOf(num_tel.getText());



                atualizate(nomeatualizado, telatualizado, emailatualizado, chave);

                btn_tela_perfil_update.setEnabled(false);

            }
        });




    }



    private void atualizate(String nome, String email, String tel, String chave){
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
                Intent intent = new Intent(PerfilAtualizar.this, Perfil.class);
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