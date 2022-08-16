package com.example.apppetrobras;

import androidx.appcompat.app.AppCompatActivity;

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

                new Task().execute();


            }
        });
    }

    class Task extends AsyncTask<Void, Void, Void>{
        String records = "", error="";

        @Override
        protected Void doInBackground(Void... voids) {
            try {

                //busca no bd
                //userbd = SELECT username FROM funcionarios;

                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection("");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT FirstName FROM Persons");

                while(resultSet.next()) {
                    userbd += resultSet.getString(1);
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

            user = et_user.getText().toString();
            pass = et_pass.getText().toString();



            if(user == userbd && pass == passbd) {
                //codigo para trocar de activity
                Toast.makeText(FormLogin.this, "funciona", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(FormLogin.this, error, Toast.LENGTH_SHORT).show();

            }




            super.onPostExecute(unused);
        }
    }

}
            }



}
