package com.example.apppetrobras;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FormLogin extends AppCompatActivity {

    EditText et_user, et_pass;
    Button btn_login;
    String user, pass, userbd, passbd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_login);

        //fazendo o elo entre os itens da classe java e do XML
        et_user = findViewById(R.id.et_user);
        et_pass = findViewById(R.id.et_pass);
        btn_login = findViewById(R.id.bt_login);


        //criaçao do evento do botao
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = et_user.getText().toString();
                pass = et_pass.getText().toString();

                //busca no bd
                //userbd = SELECT username FROM funcionarios;

                if(user == userbd && pass == passbd) {
                    //codigo para trocar de activity
                } else {
                    Toast.makeText(FormLogin.this, "Usurio ou senha incorretos", Toast.LENGTH_SHORT).show();

                }

            }
        });


    }
}
