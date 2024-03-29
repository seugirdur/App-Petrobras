package com.example.apppetrobras.Activities;

import static com.example.apppetrobras.R.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.res.ResourcesCompat;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Base64;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apppetrobras.R;
import com.example.Navigations.Tabs;
import com.example.apppetrobras.api.RetroFitClient;
import com.example.apppetrobras.Objects.LoginObj;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Login extends AppCompatActivity {

    EditText edit_user, edit_senha;
    Button button_login, esqueceu_senha;
    String user, pass, userbd, passbd, nomebd, emailbd, telbd;
    Dialog mDialog;
    ProgressBar progressbar;
    FirebaseStorage storage;
    boolean passwordVisible;
    public static final String meliorism = "meliorism";

    static int PERMISSION_CODE = 100;
    private static final int PERMISSION_REQUEST_CODE = 1;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.layout_login);

        context = this;

        edit_user = findViewById(id.edit_user);
        edit_senha = findViewById(id.edit_senha);
        button_login = findViewById(id.button_login);
        esqueceu_senha = findViewById(id.esqueceu_senha);
        storage = FirebaseStorage.getInstance();


        mDialog = new Dialog(this);
        checkconn();

        //botao de login que chama o metodo de login
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //new Task().execute();

                progressbar = findViewById(id.progressbar);
                guardate();
                //new Task().execute();
                progressbar.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressbar.setVisibility(View.INVISIBLE);
                    }
                }, 5000);

            }
        });


        // Botao esqueceu senha
        esqueceu_senha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mDialog.setContentView(layout.popup);
                mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                mDialog.show();

            }
        });

        // Olho para esconder e mostrar a senha
        edit_senha.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                final int Right = 2;
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {

                    if (motionEvent.getRawX() >= edit_senha.getRight() - edit_senha.getCompoundDrawables()[Right].getBounds().width()) {

                        int selection = edit_senha.getSelectionEnd();
                        if (passwordVisible) {

                            // set drawable image here
                            edit_senha.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, drawable.ic_eye_off, 0);

                            //for hide password
                            edit_senha.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible = false;


                        } else {

                            //set drawable image here
                            edit_senha.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, drawable.ic_eye, 0);


                            //for show password
                            edit_senha.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisible = true;


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

    public void storecheckconn(View view) {
        CheckBox check_connected = findViewById(id.check_connected);
        Boolean checktorf = check_connected.isChecked();
        SharedPreferences sharedPreferences = getSharedPreferences(
                getString(string.preference_file_key), Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("checktorf", checktorf);
        editor.apply();

    }

    private void checkconn() {
        CheckBox check_connected = findViewById(id.check_connected);

        SharedPreferences sharedPreferences = getSharedPreferences(
                getString(string.preference_file_key), Context.MODE_PRIVATE);

        Boolean checktorf = sharedPreferences.getBoolean("checktorf", false);
        edit_user = findViewById(id.edit_user);
        if (checktorf) {
            check_connected.setChecked(true);
            SharedPreferences sharedPreferences1 = getSharedPreferences(
                    getString(string.preference_file_key), Context.MODE_PRIVATE);

            String sayMyChave = sharedPreferences1.getString("chave", "");

            edit_user.setText(sayMyChave);
        } else {
            check_connected.setChecked(false);
            edit_user.setText("");

        }
    }

    private void pegarImagem() {
        SharedPreferences sharedPreferences = getSharedPreferences(
                getString(string.preference_file_key), Context.MODE_PRIVATE);

        String chave = sharedPreferences.getString("chave", "");

        StorageReference storageReference = storage.getReference("images/" + chave);
        try {
            File localfile = File.createTempFile("tempfile", ".jpg");
            storageReference.getFile(localfile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {

                            Bitmap bitmap = BitmapFactory.decodeFile(localfile.getAbsolutePath());

                            //Converter bitmap to string
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos); //bitmap is the bitmap object
                            byte[] b = baos.toByteArray();

                            String encoded = Base64.encodeToString(b, Base64.DEFAULT);

                            SharedPreferences sharedPreferences1 = getSharedPreferences(
                                    getString(string.preference_file_key), Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences1.edit();
                            editor.putString("encoded", encoded);
                            editor.apply();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            SharedPreferences sharedPreferences = getSharedPreferences(
                                    getString(string.preference_file_key), Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();


                            Bitmap bitmap = ((BitmapDrawable) ResourcesCompat.getDrawable(context.getResources(), drawable.default_imagem_user, null)).getBitmap();

                            //Converter bitmap to string
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos); //bitmap is the bitmap object
                            byte[] b = baos.toByteArray();

                            String encoded = Base64.encodeToString(b, Base64.DEFAULT);


                            editor.putString("encoded", encoded);
                            editor.apply();

//                            Toast.makeText(Login.this, "falha ao pegar", Toast.LENGTH_SHORT).show();
                        }
                    });

        } catch (IOException e) {
            e.printStackTrace();

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("imagemUser", "");
            editor.apply();
        }
    }


    private void guardate() {

        String chave = edit_user.getText().toString().trim();
        String senha = edit_senha.getText().toString().trim();


        Call<List<LoginObj>> call = RetroFitClient
                .getInstance()
                .getAPI()
                .userLogin(chave, senha);

        call.enqueue(new Callback<List<LoginObj>>() {
            @Override
            public void onResponse(Call<List<LoginObj>> call, Response<List<LoginObj>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(Login.this, "Cheque sua conexão", Toast.LENGTH_SHORT).show();
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
                int isAdmin = loginObj.getIsAdmin();

                progressbar = findViewById(R.id.progressbar);
                //new Task().execute();
                progressbar.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressbar.setVisibility(View.INVISIBLE);
                    }
                }, 5000);

                SharedPreferences sharedPreferences = getSharedPreferences(
                        getString(string.preference_file_key), Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("id", id);
                editor.putInt("isAdmin", isAdmin);
                editor.putString("nome", nome);
                editor.putString("email", email);
                editor.putString("tel", tel);
                editor.putString("dataNasc", dataNasc);
                editor.putString("chave", chave);
                editor.apply();

                pegarImagem();


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Login.this, "Bem vindo " + nome, Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(Login.this, Tabs.class);

                        startActivity(intent);
                        finish();
                    }
                }, 2000);

            }


            @Override
            public void onFailure(Call<List<LoginObj>> call, Throwable t) {
                Toast.makeText(Login.this, "O dados estão incorretos", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressbar.setVisibility(View.INVISIBLE);
                    }
                }, 1000);


            }
        });
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

    public void ligacao(View view) {


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

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

}
