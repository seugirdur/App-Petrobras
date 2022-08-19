package com.example.apppetrobras;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import android.os.Bundle;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class FormCadastro extends AppCompatActivity {

    String userbd="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_cadastro);

        CheckBox textView = findViewById(R.id.aceitoTermos);
        String text = "Li e concordo com os TERMOS DE USO e POLÍTICAS DE PRIVACIDADE";

        SpannableString ss = new SpannableString(text);


        ClickableSpan clicavel1 = new ClickableSpan() {
            public void onClick(View widget) {
                //link do pdf dos termos de uso
                String url = "https://www.hostinger.com.br/tutoriais/tutorial-do-git-basics-introducao#:~:text=Instalar%20o%20GIT%20no%20Windows%3A&text=Acesse%20o%20site%20oficial%20e,concluir%20com%20%C3%AAxito%20a%20instala%C3%A7%C3%A3o.";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        };

        ClickableSpan clicavel2 = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                //link do pdf das políticas de privacidade
                String url = "https://github.com/seugirdur/App-petrobras";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        };

        // declarando parte que funcionará como clicável
        ss.setSpan(clicavel1, 21, 34, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(clicavel2, 37, 61, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());







        //android:visibility="invisible"
    }

    public void ajuda(View view){
        Intent intent = new Intent(this, minhaPagina.class);
        startActivity(intent);

    }

    public void checarTermos (View view){
        Button cad = findViewById(R.id.btn);
        CheckBox check = findViewById(R.id.aceitoTermos);

        if(check.isChecked())
        {
            cad.setEnabled(true);
//            cad.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//
//
//                }
//            });
        }
        else
        {
            cad.setEnabled(false);
        }



    }

    public void cadastro(View view){
        TextView aviso = findViewById(R.id.avisaSenha);
        EditText senha1 = (EditText) findViewById(R.id.insertSenha);
        EditText senha2 = (EditText) findViewById(R.id.insertConfirmaSenha);
        resgataInfo();

        //AQUI FUNCIONA CACETEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
        //new Insert().execute();



        if(!senhaIgual()){
            aviso.setVisibility(View.VISIBLE);
            senha1.setText("");
            senha2.setText("");
        }else{
            Infos cadastro = resgataInfo();

        }


    }

    public Infos resgataInfo() {
        TextView nome = findViewById(R.id.insertNomeCompleto);
        TextView tel = findViewById(R.id.insertTelefone);
        TextView dataNas = findViewById(R.id.insertDataNascimento);
        TextView email = findViewById(R.id.insertEmail);
        TextView chave = findViewById(R.id.insertChave);
        TextView senha = findViewById(R.id.insertSenha);

        String _nome = nome.getText() + "";
        String _tel = tel.getText() + "";
        String _dataNas = dataNas.getText() + "";
        String _email = email.getText() + "";
        String _chave = chave.getText() + "";
        String _senha = senha.getText() + "";

        Infos cliente = new Infos(_nome, _email, _tel, _dataNas, _chave, _senha);

        new Insert().execute();

        return cliente;


    }



    //checar se senhas combinam
    private boolean senhaIgual(){
        boolean checking = false;
        EditText senha1;
        EditText senha2;

        senha1 = (EditText) findViewById(R.id.insertSenha);
        senha2 = (EditText) findViewById(R.id.insertConfirmaSenha);

        String senhaTrans1 = senha1.getText() + "";
        String senhaTrans2 = senha2.getText() + "";


        if (senhaTrans1.equals(senhaTrans2)) {
            checking = true;
        }
        return checking;
    }






    class Insert extends AsyncTask<Void, Void, Void> {
        //String records="teste7";
        String checkchave1="";
        @Override
        protected Void doInBackground(Void... voids) {
            Infos info = new Infos(resgataInfo().nome, resgataInfo().email, resgataInfo().tel, resgataInfo().dataNas, resgataInfo().chave, resgataInfo().senha);
            String nome = info.nome;
            String email = info.email;
            String telefone = info.tel;
            String datanasc = info.dataNas;
            String chave = info.chave;
            String senha = info.senha;


            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://139.177.199.178/test","backend","agathusia");
                Statement statement = connection.createStatement();


                ResultSet resultSet = statement.executeQuery("SELECT chave FROM funcionarios");

                while(resultSet.next()) {
                    checkchave1 = resultSet.getString(1);

                    if (checkchave1.equals(chave)) {
                        break;

                    } else{
                        statement.executeUpdate("INSERT INTO funcionarios (nome, email, chave) values (\"" + nome + "\",\"" + email + "\",\"" + chave + "\");");
                        break;
                    }
                }


            } catch(Exception e) {
             //   error = e.toString();
            }

            return null;
        }





    }


}
