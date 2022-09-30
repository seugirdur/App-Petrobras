package com.example.apppetrobras.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.apppetrobras.R;
import com.example.Navigations.Tabs;
import com.example.apppetrobras.api.RetroFitClient;
import com.example.apppetrobras.Objects.LoginObj;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Login extends AppCompatActivity {

    EditText edit_user, edit_senha;
    Button button_login, esqueceu_senha;
    String user, pass, userbd, passbd,nomebd, emailbd, telbd;
    Dialog mDialog;
    ProgressBar progressbar;
    boolean passwordVisible;
    public static final String meliorism = "meliorism";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);

        edit_user = findViewById(R.id.edit_user);
        edit_senha = findViewById(R.id.edit_senha);
        button_login = findViewById(R.id.button_login);
        esqueceu_senha = findViewById(R.id.esqueceu_senha);

        mDialog = new Dialog(this);

        //botao de login que chama o metodo de login
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //new Task().execute();

                progressbar=findViewById(R.id.progressbar);
                guardate();
                //new Task().execute();
                progressbar.setVisibility(View.VISIBLE);

            }
        });


        // Botao esqueceu senha
        esqueceu_senha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mDialog.setContentView(R.layout.popup);
                mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                mDialog.show();

            }
        });

        // Olho para esconder e mostrar a senha
        edit_senha.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                final int Right=2;
                if(motionEvent.getAction()==MotionEvent.ACTION_UP) {

                    if(motionEvent.getRawX()>=edit_senha.getRight()-edit_senha.getCompoundDrawables() [Right].getBounds().width()) {

                        int selection=edit_senha.getSelectionEnd();
                        if(passwordVisible){

                            // set drawable image here
                            edit_senha.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_eye, 0);

                            //for hide password
                            edit_senha.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible=false;


                        }else {

                            //set drawable image here
                            edit_senha.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_eye_off,0);


                            //for show password
                            edit_senha.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisible=true;


                        }

                        edit_senha.setSelection(selection);
                        return true;

                    }
                }
                return false;
            }
        });


        //aqui é o dionisio
    }

    private void guardate(){

        String chave = edit_user.getText().toString().trim();
        String senha = edit_senha.getText().toString().trim();


        Call<List<LoginObj>> call = RetroFitClient
                .getInstance()
                .getAPI()
                .userLogin(chave, senha);

        call.enqueue(new Callback<List<LoginObj>>() {
            @Override
            public void onResponse(Call<List<LoginObj>> call, Response<List<LoginObj>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(Login.this, response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<LoginObj> loginObjList = response.body();
                LoginObj loginObj = loginObjList.get(0);

                int id = loginObj.getId();
                String nome = loginObj.getNome();
                String email = loginObj.getEmail();
                String tel = loginObj.getTel();
                String dataNasc = loginObj.getDataNasc();
                String chave = loginObj.getChave();
                Toast.makeText(Login.this, "Bem vindo "+nome, Toast.LENGTH_SHORT).show();

                SharedPreferences sharedPreferences = getSharedPreferences(
                        getString(R.string.preference_file_key), Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("id", id);
                editor.putString("nome", nome);
                editor.putString("email", email);
                editor.putString("tel", tel);
                editor.putString("dataNasc", dataNasc);
                editor.putString("chave", chave);
                editor.apply();


                Intent intent = new Intent(Login.this, Tabs.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<List<LoginObj>> call, Throwable t) {
                Toast.makeText(Login.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
