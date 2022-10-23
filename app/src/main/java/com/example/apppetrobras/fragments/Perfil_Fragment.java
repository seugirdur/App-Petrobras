package com.example.apppetrobras.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.apppetrobras.Activities.PerfilAtualizar;
import com.example.apppetrobras.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class Perfil_Fragment extends Fragment {
    Button btn;
    TextView nomecompleto,num_tel,email1,num_chave, nome1;
    CircleImageView imagemPerfil;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //LayoutInflater lf = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_perfil, container, false);



        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        String nome = sharedPreferences.getString("nome", "");
        String email = sharedPreferences.getString("email", "");
        String tel = sharedPreferences.getString("tel", "");
        String chave = sharedPreferences.getString("chave", "");
        String imagemUser = sharedPreferences.getString("imagemUser", "");

        imagemPerfil = view.findViewById(R.id.imageView3);

        if (imagemUser != null){
            byte[] imageAsBytes = Base64.decode(imagemUser.getBytes(), Base64.DEFAULT);
            imagemPerfil.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));
        }

        String[] fullNameArray = nome.split("\\s+");
        String firstName = fullNameArray[0];
        nome1 = view.findViewById(R.id.nome_login);
        nome1.setText(firstName);


        nomecompleto = view.findViewById(R.id.nomecompleto);
        nomecompleto.setText(nome);

        num_tel = view.findViewById(R.id.num_tel);
        num_tel.setText(tel);

        email1 = view.findViewById(R.id.email);
        email1.setText(email);

        num_chave = view.findViewById(R.id.num_chave);
        num_chave.setText(chave);


        btn = view.findViewById(R.id.btn_tela_perfil);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), PerfilAtualizar.class);
                startActivity(i);
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}