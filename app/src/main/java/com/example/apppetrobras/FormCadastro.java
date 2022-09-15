package com.example.apppetrobras;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.LinkMovementMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.LinkMovementMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.ClickableSpan;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apppetrobras.api.RetroFitClient;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormCadastro extends AppCompatActivity {

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
    }

    //redirecionamento para ajuda
    public void ajuda(View view){
        Intent intent = new Intent(this, Ajuda.class);
        startActivity(intent);

    }

    //metodo para o botao de Esconder/mostrar senha
    public void mostrarSenha(View view){
        EditText senha = findViewById(R.id.insertSenha);
        ImageButton imgBtn =(ImageButton) findViewById(R.id.imgMostrarSenha);


        if(senha.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
            //mudar imagem
            imgBtn.setImageResource(R.drawable.ic_baseline_remove_red_eye_24);

            //Mostrar senha
            senha.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }
        else{
            //mudar imagem
            imgBtn.setImageResource(R.drawable.ic_baseline_remove_red_eye);

            //Esconder senha
            senha.setTransformationMethod(PasswordTransformationMethod.getInstance());


        }
    }


    public void mostrarSenha2(View view){
        EditText senha = findViewById(R.id.insertConfirmaSenha);
        ImageButton imgBtn =(ImageButton) findViewById(R.id.imgMostrarSenha2);


        if(senha.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
            //mudar imagem
            imgBtn.setImageResource(R.drawable.ic_baseline_remove_red_eye_24);

            //Mostrar senha
            senha.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }
        else{
            //mudar imagem
            imgBtn.setImageResource(R.drawable.ic_baseline_remove_red_eye);

            //Esconder senha
            senha.setTransformationMethod(PasswordTransformationMethod.getInstance());


        }
    }

    //check-in do termos e condições
    public void checarTermos (View view){
        Button cad = findViewById(R.id.btn);
        CheckBox check = findViewById(R.id.aceitoTermos);

        if(check.isChecked())
        {
            cad.setEnabled(true);
        }
        else
        {
            cad.setEnabled(false);
        }

    }

    //cadastro das informações
    public void cadastro(View view){
        TextView aviso = findViewById(R.id.avisaSenha);
        EditText senha1 = (EditText) findViewById(R.id.insertSenha);
        EditText senha2 = (EditText) findViewById(R.id.insertConfirmaSenha);
        TextView nome = findViewById(R.id.insertNomeCompleto);
        TextView tel = findViewById(R.id.insertTelefone);
        TextView dataNas = findViewById(R.id.insertDataNascimento);
        TextView email = findViewById(R.id.insertEmail);
        TextView chave = findViewById(R.id.insertChave);
        TextView senha = findViewById(R.id.insertSenha);
        resgataInfo();
        String Checknome = nome.getText().toString();
        String Checktel = tel.getText().toString();
        String CheckdataNas = dataNas.getText().toString();
        String Checkemail = email.getText().toString();
        String Checkchave =  chave.getText().toString();
        String Checksenha =  senha.getText().toString();



        //metodo para conferir se as senhas sao iguais
        if(!senhaIgual()){
            aviso.setText("As senhas não conferem");
            aviso.setVisibility(View.VISIBLE);
            senha1.setText("");
            senha2.setText("");
        }else if(Checknome.isEmpty() | Checkemail.isEmpty() |Checkchave.isEmpty()|Checksenha.isEmpty()|Checktel.isEmpty()|CheckdataNas.isEmpty()){
          aviso.setText("Preencha as informações");
          aviso.setVisibility(View.VISIBLE);
        }
        else {
            registrate();
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

//metodo para registro
private void registrate(){
    Infos info = new Infos(resgataInfo().nome, resgataInfo().email, resgataInfo().tel, resgataInfo().dataNas, resgataInfo().chave, resgataInfo().senha);
            String nome = info.nome.toString().trim();
            String email = info.email.toString().trim();
            String tel = info.tel.toString().trim();
            String dataNasc = info.dataNas.toString().trim();
            String chave = info.chave.toString().trim();
            String senha = info.senha.toString().trim();

            //call para guardar no banco de dados e conferir se o usuario ja existe
            Call<ResponseBody> call = RetroFitClient
                    .getInstance()
                    .getAPI()
                    .createUser(nome, email, tel, dataNasc, chave, senha);

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        String body = response.body().string();
                        Toast.makeText(FormCadastro.this, body, Toast.LENGTH_LONG).show();
                        Toast.makeText(FormCadastro.this, "eu fiz cadastro s", Toast.LENGTH_SHORT).show();
                    }catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(FormCadastro.this, t.getMessage(), Toast.LENGTH_LONG).show();

                }
            });

    }
}
