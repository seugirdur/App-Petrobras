package com.example.apppetrobras;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class FormLogin extends AppCompatActivity {

    EditText edit_user, edit_senha;
    Button button_login, esqueceu_senha;
    String user, pass, userbd, passbd,nomebd, emailbd, telbd;
    Dialog mDialog;
    ProgressBar progressbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_login);

        edit_user = findViewById(R.id.edit_user);
        edit_senha = findViewById(R.id.edit_senha);
        button_login = findViewById(R.id.button_login);
        esqueceu_senha = findViewById(R.id.esqueceu_senha);

        mDialog = new Dialog(this);

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressbar=findViewById(R.id.progressbar);

                new Task().execute();
                progressbar.setVisibility(View.VISIBLE);

            }
        });

        esqueceu_senha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mDialog.setContentView(R.layout.popup);
                mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                mDialog.show();

            }
        });
    }

    public void guardaInfo() {
        String guardanome;
        String guardatel;
        String guardaemail;
        String guardachave;
        String guardasenha;

        guardanome = nomebd;
        guardatel = telbd;
        guardaemail = emailbd;
        guardachave = userbd;
        guardasenha = passbd;

        userLogged usuario = new userLogged();
        usuario.setNome(guardanome);
        usuario.setTel(guardatel);
        usuario.setEmail(guardaemail);
        usuario.setChave(guardachave);
        usuario.setSenha(guardasenha);
    }

    class Task extends AsyncTask<Void, Void, Void> {
        String records = "", error="";
        String user = edit_user.getText().toString();
        String pass = edit_senha.getText().toString();

        @Override
        protected Void doInBackground(Void... voids) {
            try {

                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://139.177.199.178/test","backend","agathusia");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM funcionarios");

                while(resultSet.next()) {
                    nomebd = resultSet.getString("nome");
                    telbd = resultSet.getString("tel");
                    emailbd = resultSet.getString("email");
                    userbd = resultSet.getString("chave");
                    passbd = resultSet.getString("senha");

                    if(user.equals(userbd) && pass.equals(passbd)) {
                        break;
                    }
                }

            } catch(Exception e) {
                error = e.toString();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            if (error != "") {
            }

            if(user.equals(userbd) && pass.equals(passbd)) {
                Toast.makeText(FormLogin.this, "Bem vindo "+nomebd , Toast.LENGTH_SHORT).show();
                guardaInfo();

                Intent intent = new Intent(FormLogin.this, TabActivity.class);
                startActivity(intent);
                finish();

            } else {
                Toast.makeText(FormLogin.this, "n foi dnv", Toast.LENGTH_SHORT).show();
            }

            super.onPostExecute(unused);
        }
    }
}
