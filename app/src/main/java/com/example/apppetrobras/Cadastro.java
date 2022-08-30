package com.example.apppetrobras;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
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
import android.widget.ImageButton;
import android.widget.TextView;

import android.os.Bundle;

public class Cadastro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro);








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
        Intent intent = new Intent(this, minhaPagina.class);
        startActivity(intent);

    }

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
        resgataInfo();

        if(!senhaIgual()){
           aviso.setVisibility(View.VISIBLE);
           senha1.setText("");
           senha2.setText("");
       }else{
            Infos cadastro = resgataInfo();
        }


    }

    //transformar num objeto
    public Infos resgataInfo(){
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

        Infos cliente = new Infos( _nome,  _email,  _tel,  _dataNas,  _chave,  _senha);

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

}
/*
private boolean nomoeCerto(){
    return false;
} */