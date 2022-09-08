package com.example.apppetrobras.models;

public class UserAPI {
    private String nome, email, tel, dataNasc, chave, senha;

    public UserAPI(String nome, String email, String tel, String dataNasc, String chave, String senha) {
        this.nome = nome;
        this.email = email;
        this.tel = tel;
        this.dataNasc = dataNasc;
        this.chave = chave;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTel() {
        return tel;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public String getChave() {
        return chave;
    }

    public String getSenha() {
        return senha;
    }
}
