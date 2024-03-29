package com.example.apppetrobras.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
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

import com.example.Navigations.Tabs;
import com.example.apppetrobras.Objects.CadastroObj;
import com.example.apppetrobras.R;
import com.example.apppetrobras.api.RetroFitClient;

import org.w3c.dom.Text;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Cadastro extends AppCompatActivity {

    EditText senha, confirmar_senha;
    boolean passwordVisible;
    Context context = this;
    static int PERMISSION_CODE = 100;
    private static final int PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_cadastro);


        TextView textView = findViewById(R.id.texto_aceito_termos);
        String text = "Li e concordo com os \nTermos de Uso e \nPolíticas de Privacidade";

        SpannableString ss = new SpannableString(text);


        ClickableSpan clicavel1 = new ClickableSpan() {
            public void onClick(View widget) {
                //link do pdf dos termos de uso
                String url = "https://drive.google.com/file/d/11U4-N8wFcqf6MZF9mwzGNJNrBVx3MbKB/view?usp=sharing";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        };

//       ClickableSpan clicavel15 = new ClickableSpan() {
//            public void onClick(View widget) {
//                //link do pdf dos termos de uso
//                String url = "https://drive.google.com/file/d/1P39Iel7CKZqv8UF3EkSCCzer_GmJ5_BX/view?usp=sharing";
//                Intent i = new Intent(Intent.ACTION_VIEW);
//                i.setData(Uri.parse(url));
//            }
//        };


        ClickableSpan clicavel2 = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                //link do pdf das políticas de privacidade
                String url = "https://drive.google.com/file/d/11U4-N8wFcqf6MZF9mwzGNJNrBVx3MbKB/view?usp=sharing";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        };

        // declarando parte que funcionará como clicável
        ss.setSpan(clicavel1, 21, 29, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //ss.setSpan(clicavel15, 22, 63, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(clicavel2, 22, 63, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());


        EditText dataNasc;

        dataNasc = (EditText) findViewById(R.id.data_nascimento);
        dataNasc.addTextChangedListener(new TextWatcher() {
            boolean flag = true;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = dataNasc.getText().toString();
                int textLength = dataNasc.getText().length();

                String[] dataNascArray = str.split("");
                if (textLength == 3) {

                    if (!str.contains("/")) {
                        dataNasc.setText(new StringBuilder(dataNasc.getText().toString()).insert(str.length() - 1, "/").toString());
                        dataNasc.setSelection(dataNasc.getText().length());
                    }
                }


                if (textLength == 6) {

                    if (dataNascArray[5] != "/" && flag == true) {
                        dataNasc.setText(new StringBuilder(dataNasc.getText().toString()).insert(str.length() - 1, "/").toString());
                        dataNasc.setSelection(dataNasc.getText().length());
                        flag = false;
                    }

                }

                if (flag == false && textLength <= 5) {
                    flag = true;
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        EditText tel;

        tel = (EditText) findViewById(R.id.telefone);

        tel.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = tel.getText().toString();
                int textLength = tel.getText().length();
                if (textLength == 1) {

                    if (!str.contains("(")) {
                        tel.setText(new StringBuilder(tel.getText().toString()).insert(str.length() - 1, "(").toString());
                        tel.setSelection(tel.getText().length());
                    }
                }
                if (textLength == 4) {

                    if (!str.contains(")")) {
                        tel.setText(new StringBuilder(tel.getText().toString()).insert(str.length() - 1, ")").toString());
                        tel.setSelection(tel.getText().length());
                    }
                }

                if (textLength == 5) {

                    if (!str.contains(" ")) {
                        tel.setText(new StringBuilder(tel.getText().toString()).insert(str.length() - 1, " ").toString());
                        tel.setSelection(tel.getText().length());
                    }
                }
                if (textLength == 11) {

                    if (!str.contains("-")) {
                        tel.setText(new StringBuilder(tel.getText().toString()).insert(str.length() - 1, "-").toString());
                        tel.setSelection(tel.getText().length());
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

                final int Right = 2;
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {

                    if (motionEvent.getRawX() >= senha.getRight() - senha.getCompoundDrawables()[Right].getBounds().width()) {

                        int selection = senha.getSelectionEnd();
                        if (passwordVisible) {

                            // set drawable image here
                            senha.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_eye_off, 0);

                            //for hide password
                            senha.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible = false;


                        } else {

                            //set drawable image here
                            senha.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_eye, 0);


                            //for show password
                            senha.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisible = true;


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

                final int Right = 2;
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {

                    if (motionEvent.getRawX() >= confirmar_senha.getRight() - confirmar_senha.getCompoundDrawables()[Right].getBounds().width()) {

                        int selection = confirmar_senha.getSelectionEnd();
                        if (passwordVisible) {

                            // set drawable image here
                            confirmar_senha.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_eye_off, 0);

                            //for hide password
                            confirmar_senha.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible = false;


                        } else {

                            //set drawable image here
                            confirmar_senha.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_eye, 0);


                            //for show password
                            confirmar_senha.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisible = true;


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
    public void ajuda(View view) {
        Intent intent = new Intent(this, Ajuda.class);
        startActivity(intent);

    }

    public void ligacao(View view) {


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                Intent intent = new Intent(Intent.ACTION_DIAL);
//                intent.setData(Uri.parse("tel:0123456789"));
//                startActivity(intent);
                makeCall("+5513991509119");

            }
        }, 100);


    }

    public void makeCall(String s) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + s));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

            requestForCallPermission();

        } else {
            startActivity(intent);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(context, Tabs.class);
                    startActivity(i);
                }
            }, 3000);


        }
    }

    public void requestForCallPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {
        } else {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    makeCall("+551399150-9119");
                }
                break;
        }
    }

    public void email(View view) {
        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.popupcheck), Context.MODE_PRIVATE);
        String nome = sharedPref.getString("nome", "");


        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"suporteaset@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Suporte para uso do App");
//                intent.putExtra(Intent.EXTRA_TEXT, "");
        startActivity(Intent.createChooser(intent, "Escolha o aplicativo de email"));
    }


    //check-in do termos e condições
    public void checarTermos(View view) {
        ImageButton cad = findViewById(R.id.button_cadastro);
        CheckBox check = findViewById(R.id.aceitoTermos);
        ImageButton cad1 = findViewById(R.id.button_cadastro1);

        if (check.isChecked()) {
            cad.setEnabled(true);
            cad1.setVisibility(View.INVISIBLE);
        } else {
            cad.setEnabled(false);
            cad1.setVisibility(View.VISIBLE);
        }

    }

    //cadastro das informações
    public void cadastro(View view) {
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
        String Checkchave = chave.getText().toString();
        String Checksenha = senha.getText().toString();
        String str = chave.getText().toString();
        int textLength = chave.getText().length();
        int textLength1 = tel.getText().length();

        if (textLength1 < 15) {
            Toast.makeText(this, "Telefone incorreto", Toast.LENGTH_SHORT).show();
            tel.setText("");
        }
        if (textLength < 4) {
            Toast.makeText(this, "Chave de Acesso incorreta", Toast.LENGTH_SHORT).show();
            chave.setText("");
        } else if (!senhaIgual()) {
            Toast.makeText(this, "As senhas não coincidem", Toast.LENGTH_SHORT).show();
            senha1.setText("");
            senha2.setText("");
        } else if (Checknome.isEmpty() | Checkemail.isEmpty() | Checkchave.isEmpty() | Checksenha.isEmpty() | Checktel.isEmpty() | CheckdataNasc.isEmpty()) {
            Toast.makeText(this, "Preencha as informações", Toast.LENGTH_SHORT).show();
        } else {
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

        CadastroObj cliente = new CadastroObj(_nome, _email, _tel, _dataNasc, _chave, _senha, 0);

        return cliente;
    }

    //checar se senhas combinam
    private boolean senhaIgual() {
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

    private void registrate() {
        CadastroObj info = new CadastroObj(resgataInfo().nome, resgataInfo().email, resgataInfo().tel, resgataInfo().dataNasc, resgataInfo().chave, resgataInfo().senha, resgataInfo().isAdmin);
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
                .createUser(nome, email, tel, dataNasc, 0, chave, senha);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    int code = response.code();
                    if (code == 201) {
                        Toast.makeText(Cadastro.this, "Usuario já cadastrado, selecione outra chave", Toast.LENGTH_LONG).show();
                    } else {
                        String body = response.body().string();
                        Toast.makeText(Cadastro.this, "Cadastrado com sucesso", Toast.LENGTH_LONG).show();
                        vamoprologin();

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(Cadastro.this, "Cadastro não realizado", Toast.LENGTH_LONG).show();

            }
        });

    }


}