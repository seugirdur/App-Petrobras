package com.example.apppetrobras.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.LinkMovementMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.ClickableSpan;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.apppetrobras.Objects.CadastroObj;
import com.example.apppetrobras.R;
import com.example.apppetrobras.api.RetroFitClient;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Cadastro extends AppCompatActivity {

    EditText senha, confirmar_senha;
    boolean passwordVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_cadastro);


        CheckBox textView = findViewById(R.id.aceitoTermos);
        String text = "Li e concordo com os Termos de Uso e Políticas de Privacidade";

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

        ClickableSpan clicavel15 = new ClickableSpan() {
            public void onClick(View widget) {
                //link do pdf dos termos de uso
                String url = "https://www.hostinger.com.br/tutoriais/tutorial-do-git-basics-introducao#:~:text=Instalar%20o%20GIT%20no%20Windows%3A&text=Acesse%20o%20site%20oficial%20e,concluir%20com%20%C3%AAxito%20a%20instala%C3%A7%C3%A3o.";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
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
        ss.setSpan(clicavel1, 21, 27, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(clicavel15, 21, 34, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(clicavel2, 37, 61, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());


        EditText dataNasc;

        dataNasc = (EditText)findViewById(R.id.data_nascimento);
        dataNasc .addTextChangedListener(new TextWatcher() {
            boolean flag= true;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str=dataNasc .getText().toString();
                int textLength=dataNasc .getText().length();

                String[] dataNascArray = str.split("");
                if (textLength == 3) {

                    if (!str.contains("/")) {
                        dataNasc .setText(new StringBuilder(dataNasc .getText().toString()).insert(str.length() - 1, "/").toString());
                        dataNasc .setSelection(dataNasc .getText().length());
                    }
                }


                if (textLength == 6) {

                    if (dataNascArray[5] != "/" && flag == true) {
                        dataNasc.setText(new StringBuilder(dataNasc.getText().toString()).insert(str.length() - 1, "/").toString());
                        dataNasc.setSelection(dataNasc.getText().length());
                        flag = false;
                    }

                }

                if (flag==false && textLength <= 5) {
                    flag = true;
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        EditText tel;

        tel = (EditText)findViewById(R.id.telefone);

        tel .addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str=tel .getText().toString();
                int textLength=tel .getText().length();
                if (textLength == 1) {

                    if (!str.contains("(")) {
                        tel .setText(new StringBuilder(tel.getText().toString()).insert(str.length() - 1, "(").toString());
                        tel.setSelection(tel .getText().length());
                    }
                }
                if (textLength == 4) {

                    if (!str.contains(")")) {
                        tel .setText(new StringBuilder(tel.getText().toString()).insert(str.length() - 1, ")").toString());
                        tel.setSelection(tel .getText().length());
                    }
                }

                if (textLength == 5) {

                    if (!str.contains(" ")) {
                        tel .setText(new StringBuilder(tel.getText().toString()).insert(str.length() - 1, " ").toString());
                        tel.setSelection(tel .getText().length());
                    }
                }
                if (textLength == 11) {

                    if (!str.contains("-")) {
                        tel .setText(new StringBuilder(tel.getText().toString()).insert(str.length() - 1, "-").toString());
                        tel.setSelection(tel .getText().length());
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        // Esconder e mostrar senha

        senha = findViewById(R.id.senha);
        confirmar_senha = findViewById(R.id.confirmar_senha);

        senha.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                final int Right=2;
                if(motionEvent.getAction()==MotionEvent.ACTION_UP) {

                    if(motionEvent.getRawX()>=senha.getRight()-senha.getCompoundDrawables() [Right].getBounds().width()) {

                        int selection=senha.getSelectionEnd();
                        if(passwordVisible){

                            // set drawable image here
                            senha.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_eye_off, 0);

                            //for hide password
                            senha.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible=false;


                        }else {

                            //set drawable image here
                            senha.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_eye,0);


                            //for show password
                            senha.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisible=true;


                        }

                        senha.setSelection(selection);
                        return true;

                    }
                }
                return false;
            }
        });

        confirmar_senha.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                final int Right=2;
                if(motionEvent.getAction()==MotionEvent.ACTION_UP) {

                    if(motionEvent.getRawX()>=confirmar_senha.getRight()-confirmar_senha.getCompoundDrawables() [Right].getBounds().width()) {

                        int selection=confirmar_senha.getSelectionEnd();
                        if(passwordVisible){

                            // set drawable image here
                            confirmar_senha.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_eye_off, 0);

                            //for hide password
                            confirmar_senha.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible=false;


                        }else {

                            //set drawable image here
                            confirmar_senha.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_eye,0);


                            //for show password
                            confirmar_senha.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisible=true;


                        }

                        confirmar_senha.setSelection(selection);
                        return true;

                    }
                }
                return false;
            }
        });


    }

    //redirecionamento para ajuda
    public void ajuda(View view){
        Intent intent = new Intent(this, Ajuda.class);
        startActivity(intent);

    }


    //check-in do termos e condições
    public void checarTermos (View view){
        ImageButton cad = findViewById(R.id.button_cadastro);
        CheckBox check = findViewById(R.id.aceitoTermos);
        ImageButton cad1 = findViewById(R.id.button_cadastro1);

        if(check.isChecked())
        {
            cad.setEnabled(true);
            cad1.setVisibility(View.INVISIBLE);
        }
        else
        {
            cad.setEnabled(false);
            cad1.setVisibility(View.VISIBLE);
        }

    }

    //cadastro das informações
    public void cadastro(View view){
        EditText senha1 = (EditText) findViewById(R.id.senha);
        EditText senha2 = (EditText) findViewById(R.id.confirmar_senha);
        TextView nome = findViewById(R.id.nome_completo);
        TextView tel = findViewById(R.id.telefone);
        TextView dataNasc = findViewById(R.id.data_nascimento);
        TextView email = findViewById(R.id.email);
        TextView chave = findViewById(R.id.chave_acesso);
        TextView senha = findViewById(R.id.senha);
        resgataInfo();
        String Checknome = nome.getText().toString();
        String Checktel = tel.getText().toString();
        String CheckdataNasc = dataNasc.getText().toString();
        String Checkemail = email.getText().toString();
        String Checkchave =  chave.getText().toString();
        String Checksenha =  senha.getText().toString();


        if(!senhaIgual()){
            Toast.makeText(this, "As senhas não coincidem", Toast.LENGTH_SHORT).show();
            senha1.setText("");
            senha2.setText("");
        }else if(Checknome.isEmpty() | Checkemail.isEmpty() |Checkchave.isEmpty()|Checksenha.isEmpty()|Checktel.isEmpty()|CheckdataNasc.isEmpty()){
            Toast.makeText(this, "Preencha as informações", Toast.LENGTH_SHORT).show();
        }
        else {
            //new Insert().execute();
            registrate();
            CadastroObj cadastro = resgataInfo();
        }


        }


        private void vamoprologin() {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    Intent myIntent = new Intent(Cadastro.this, Login.class);
                    startActivity(myIntent);
                }
            }, 1500);
        }


    public CadastroObj resgataInfo() {
        TextView nome = findViewById(R.id.nome_completo);
        TextView tel = findViewById(R.id.telefone);
        TextView dataNasc = findViewById(R.id.data_nascimento);
        TextView email = findViewById(R.id.email);
        TextView chave = findViewById(R.id.chave_acesso);
        TextView senha = findViewById(R.id.senha);

        String _nome = nome.getText() + "";
        String _tel = tel.getText() + "";
        String _dataNasc = dataNasc.getText() + "";
        String _email = email.getText() + "";
        String _chave = chave.getText() + "";
        String _senha = senha.getText() + "";

        CadastroObj cliente = new CadastroObj(_nome, _email, _tel, _dataNasc , _chave, _senha,0 );

        return cliente;
    }

    //checar se senhas combinam
    private boolean senhaIgual(){
        boolean checking = false;
        EditText senha1;
        EditText senha2;

        senha1 = (EditText) findViewById(R.id.senha);
        senha2 = (EditText) findViewById(R.id.confirmar_senha);

        String senhaTrans1 = senha1.getText() + "";
        String senhaTrans2 = senha2.getText() + "";


        if (senhaTrans1.equals(senhaTrans2)) {
            checking = true;
        }
        return checking;
    }

    private void registrate(){
        CadastroObj info = new CadastroObj(resgataInfo().nome, resgataInfo().email, resgataInfo().tel,resgataInfo().dataNasc, resgataInfo().chave, resgataInfo().senha, resgataInfo().isAdmin);
        String nome = info.nome.toString().trim();
        String email = info.email.toString().trim();
        String tel = info.tel.toString().trim();
        String dataNasc = info.dataNasc.toString().trim();
        String chave = info.chave.toString().trim();
        String senha = info.senha.toString().trim();
        int isAdmin = 0;

        Call<ResponseBody> call = RetroFitClient
                .getInstance()
                .getAPI()
                .createUser(nome, email, tel, dataNasc, 0 , chave, senha);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    int code = response.code();
                    if (code == 201){
                        Toast.makeText(Cadastro.this, "Usuario já cadastrado, selecione outra chave", Toast.LENGTH_LONG).show();
                    } else {
                        String body = response.body().string();
                    Toast.makeText(Cadastro.this, "Cadastrado com sucesso", Toast.LENGTH_LONG).show();
                        vamoprologin();

                    }
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(Cadastro.this, "Cadastro não realizado", Toast.LENGTH_LONG).show();

            }
        });

    }



//    class Insert extends AsyncTask<Void, Void, Void> {
//
//        String checkchave1= "";
//        String error="";
//        boolean flag=false;
//
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//            Infos info = new Infos(resgataInfo().nome, resgataInfo().email, resgataInfo().tel, resgataInfo().dataNas, resgataInfo().chave, resgataInfo().senha);
//            String nome = info.nome;
//            String email = info.email;
//            String tel = info.tel;
//            String datanasc = "";
//            String chave = info.chave;
//            String senha = info.senha;
//            String datanasc_br = info.dataNas;
//            char ch;
//
//            for (int i=0; i<datanasc_br.length(); i++)
//            {
//                ch= datanasc_br.charAt(i); //extracts each character
//                datanasc= ch+datanasc; //adds each character in front of the existing string
//            }
//            try {
//                Class.forName("com.mysql.jdbc.Driver");
//                Connection connection = DriverManager.getConnection("jdbc:mysql://139.177.199.178/test","backend","agathusia");
//
//               Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
//               ResultSet resultSet = statement.executeQuery("SELECT * FROM funcionarios");
//
//                    while (resultSet.next()) {
//                        checkchave1 = resultSet.getString("chave");
//
//                        if (checkchave1.equals(chave)) {
//                            flag=false;
//                            Toast.makeText(FormCadastro.this, "Usuário já cadastrado", Toast.LENGTH_SHORT).show();
//                            break;
//
//                        } else {
//                            flag=true;
//                        }
//                    }
//
//                    if (flag) {
//
//                        //statement.executeUpdate("INSERT INTO funcionarios (nome, email, chave) values (\"" + nome + "\",\"" + email + "\",\"" + chave + "\");");
//
//                    resultSet.last();
//                    //int id = resultSet.getInt("id") + 1;
//                    resultSet.moveToInsertRow();
//                    //resultSet.updateInt("id", id);
//                    resultSet.updateString("nome", nome);
//                    resultSet.updateString("email", email);
//                    resultSet.updateString("tel", tel);
//                    //resultSet.updateString("dataNasc", datanasc);
//                    resultSet.updateString("chave", chave);
//                    resultSet.updateString("senha", senha);
//                    resultSet.insertRow();
//                    resultSet.beforeFirst();
//                    }
//            } catch(Exception e) {
//                    error = e.toString();
//            }
//            return null;
//        }
//    }
}