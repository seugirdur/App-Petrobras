package com.example.apppetrobras.Activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.Navigations.Configuracoes;
import com.example.Navigations.Drawer;
import com.example.Navigations.Tabs;
import com.example.apppetrobras.Objects.CadastroObj;
import com.example.apppetrobras.Objects.LoginObj;
import com.example.apppetrobras.Objects.PerfilObj;
import com.example.apppetrobras.R;
import com.example.apppetrobras.api.RetroFitClient;
import com.example.apppetrobras.databinding.LayoutPassosBinding;
import com.example.apppetrobras.databinding.LayoutPerfilAtualizarBinding;
import com.example.apppetrobras.fragments.Ajuda_fragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilAtualizar extends Drawer {

    String chave;
    TextView nome1,num_chave;
    EditText nomecompleto, num_tel, email1;
    Button btn_tela_perfil_update;
    ImageButton btn_add_imagem;
    LayoutPerfilAtualizarBinding layoutPerfilAtualizarBinding;
    CircleImageView imagemUser;
    FirebaseStorage storage;
    Uri imageUri;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layoutPerfilAtualizarBinding = LayoutPerfilAtualizarBinding.inflate(getLayoutInflater());
        setContentView(layoutPerfilAtualizarBinding.getRoot());
        allocateActivityTitle("Editar Perfil");
        storage = FirebaseStorage.getInstance();

        imagemUser = findViewById(R.id.imagemPerfil);
        imagemparatodos();

        SharedPreferences sharedPreferences = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        String nome = sharedPreferences.getString("nome", "");
        String tel = sharedPreferences.getString("tel", "");
        String email = sharedPreferences.getString("email", "");
        chave = sharedPreferences.getString("chave", "");

        String[] fullNameArray = nome.split("\\s+");
        String firstName = fullNameArray[0];
        nome1=findViewById(R.id.nome_login);
        nome1.setText(firstName);

        nomecompleto = findViewById(R.id.nomecompletoupdate);
        nomecompleto.setHint(nome);

        num_tel = findViewById(R.id.num_telupdate);
        num_tel.setHint(tel);

        email1 = findViewById(R.id.emailupdate);
        email1.setHint(email);

        num_chave = findViewById(R.id.num_chave);
        num_chave.setHint(chave);

        num_tel .addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str= num_tel.getText().toString();
                int textLength= num_tel.getText().length();
                if (textLength == 1) {
                    if (!str.contains("(")) {
                        num_tel.setText(new StringBuilder(num_tel.getText().toString()).insert(str.length() - 1, "(").toString());
                        num_tel.setSelection(num_tel.getText().length());
                    }
                }
                if (textLength == 4) {

                    if (!str.contains(")")) {
                        num_tel.setText(new StringBuilder(num_tel.getText().toString()).insert(str.length() - 1, ")").toString());
                        num_tel.setSelection(num_tel.getText().length());
                    }
                }

                if (textLength == 5) {

                    if (!str.contains(" ")) {
                        num_tel.setText(new StringBuilder(num_tel.getText().toString()).insert(str.length() - 1, " ").toString());
                        num_tel.setSelection(num_tel.getText().length());
                    }
                }
                if (textLength == 11) {

                    if (!str.contains("-")) {
                        num_tel.setText(new StringBuilder(num_tel.getText().toString()).insert(str.length() - 1, "-").toString());
                        num_tel.setSelection(num_tel.getText().length());
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

//        String emailatualizado = String.valueOf(email1.getText());





        btn_tela_perfil_update = findViewById(R.id.btn_tela_perfil_update);

        btn_tela_perfil_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nomeatualizado = nomecompleto.getText().toString();
                String telatualizado = num_tel.getText().toString();
                String emailatualizado = email1.getText().toString();
//              String telatualizado = String.valueOf(num_tel.getText());

                if(nomeatualizado.isEmpty()) { nomeatualizado = nome;}
                if(telatualizado.isEmpty()) { telatualizado = tel;}
                if(emailatualizado.isEmpty()) { emailatualizado = email;}

                atualizate(nomeatualizado, emailatualizado, telatualizado, chave);

                btn_tela_perfil_update.setEnabled(false);
            }
        });

        btn_add_imagem = findViewById(R.id.btn_add_imagem);

        btn_add_imagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGetContent.launch("image/*");
            }
        });

    }

    private void uploadImage(Uri imageUri){

        if (imageUri != null) {
            StorageReference reference = storage.getReference().child("images/"+chave);

            reference.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {

                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    if (task.isSuccessful()){
                        pegarImagem();

                        Intent intent = new Intent(PerfilAtualizar.this, Configuracoes.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(PerfilAtualizar.this,"Não foi salva",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    ActivityResultLauncher<String> mGetContent = registerForActivityResult(
            new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri result){
                    if (result != null) {
                        imagemUser.setImageURI(result);
                        imageUri = result;
                    }
                }
            }
    );



    private void atualizate(String nome, String email,String tel, String chave){
//        PerfilObj perfilObj = new PerfilObj();
//
//        perfilObj.setNome(nome);
//        perfilObj.setEmail(email);
//        perfilObj.setTelefone(tel);

        if (imageUri!=null) {
            uploadImage(imageUri);
        }

        Call<List<PerfilObj>> call = RetroFitClient
                .getInstance()
                .getAPI()
                .updateUser(nome, email, tel, chave);

        call.enqueue(new Callback<List<PerfilObj>>() {
            @Override
            public void onResponse(Call<List<PerfilObj>> call, Response<List<PerfilObj>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(PerfilAtualizar.this, response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<PerfilObj> perfilObjList = response.body();
                PerfilObj perfilObj = perfilObjList.get(0);

                String nome = perfilObj.getNome();
                String email = perfilObj.getEmail();
                String tel = perfilObj.getTelefone();
                Toast.makeText(PerfilAtualizar.this, "Informações alteradas com sucesso!", Toast.LENGTH_SHORT).show();

                SharedPreferences sharedPreferences = getSharedPreferences(
                        getString(R.string.preference_file_key), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("nome", nome);
                editor.putString("email", email);
                editor.putString("tel", tel);
                editor.apply();

            }

            @Override
            public void onFailure(Call<List<PerfilObj>> call, Throwable t) {
                Toast.makeText(PerfilAtualizar.this, "Cadastro atualizado com sucesso!", Toast.LENGTH_SHORT).show();
                SharedPreferences sharedPreferences = getSharedPreferences(
                        getString(R.string.preference_file_key), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("nome", nome);
                editor.putString("email", email);
                editor.putString("tel", tel);
                editor.apply();

                if (imageUri!=null) {
                    uploadImage(imageUri);
                } else {
                    Intent intent = new Intent(PerfilAtualizar.this, Configuracoes.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void imagemparatodos() {
        SharedPreferences sharedPreferences = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        String encoded = sharedPreferences.getString("encoded", "");


        byte[] imageAsBytes = Base64.decode(encoded.getBytes(), Base64.DEFAULT);
        imagemUser.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));

    }

    private void pegarImagem() {
        StorageReference storageReference = storage.getReference("images/"+chave);
        try {
            File localfile = File.createTempFile("tempfile",".jpg");
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
                                    getString(R.string.preference_file_key), Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences1.edit();
                            editor.putString("encoded", encoded);
                            editor.apply();

                            // setar imagem a partir do bitmap
//                            imagemUser.setImageBitmap(bitmap);

                            imagemparatodos();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(PerfilAtualizar.this, "falha ao pegar", Toast.LENGTH_SHORT).show();
                        }
                    });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}