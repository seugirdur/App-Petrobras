package com.example.apppetrobras;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class FormLogin extends AppCompatActivity {

    EditText edit_user, edit_senha;
    Button button_login, esqueceu_senha;
    String user, pass, userbd, passbd;
    Dialog mDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_login);

        //fazendo o elo entre os itens da classe java e do XML
        edit_user = findViewById(R.id.edit_user);
        edit_senha = findViewById(R.id.edit_senha);
        button_login = findViewById(R.id.button_login);
        esqueceu_senha = findViewById(R.id.esqueceu_senha);

        mDialog = new Dialog(this);


        //criaçao do evento do botao
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new Task().execute();


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

    class Task extends AsyncTask<Void, Void, Void> {
        String records = "", error="";

        @Override
        protected Void doInBackground(Void... voids) {
            try {

                //busca no bd
                //userbd = SELECT username FROM funcionarios;

                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://139.177.199.178/test","backend","agathusia");                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT FirstName FROM Persons");

                while(resultSet.next()) {
                    userbd = resultSet.getString(1);
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

            user = edit_user.getText().toString();
            pass = edit_senha.getText().toString();



            if(user.equals(userbd)) {
                //codigo para trocar de activity
                Toast.makeText(FormLogin.this, "funciona", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(FormLogin.this, "n foi dnv", Toast.LENGTH_SHORT).show();

            }

            super.onPostExecute(unused);
        }
    }

}
