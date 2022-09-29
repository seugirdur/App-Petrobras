package com.example.apppetrobras.Objects;

import android.os.Parcel;
import android.os.Parcelable;

public class userLogged implements Parcelable {
    public String nome;
    private String tel;
    private String email;
    private String chave;
    private String senha;


    public userLogged(String nome, String tel, String email, String chave, String senha) {
        super();
        this.nome = nome;
        this.tel = tel;
        this.email = email;
        this.chave = chave;
        this.senha = senha;
    }

    protected userLogged(Parcel in) {
        nome = in.readString();
        tel = in.readString();
        email = in.readString();
        chave = in.readString();
        senha = in.readString();
    }

    public static final Creator<userLogged> CREATOR = new Creator<userLogged>() {
        @Override
        public userLogged createFromParcel(Parcel in) {
            return new userLogged(in);
        }

        @Override
        public userLogged[] newArray(int size) {
            return new userLogged[size];
        }
    };

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nome);
        parcel.writeString(tel);
        parcel.writeString(email);
        parcel.writeString(chave);
        parcel.writeString(senha);
    }
};