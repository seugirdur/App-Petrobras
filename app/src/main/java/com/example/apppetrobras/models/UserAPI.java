package com.example.apppetrobras.models;

public class UserAPI {

    //Classe publica para guardar as informaçoes do usuario que fizer login para ficar disponivel para classes que precisem dessa informaçao
    private String nome, email, tel, dataNasc, chave, senha;
    private Integer id;

    public UserAPI(Integer id, String nome, String email, String tel, String dataNasc, String chave, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.tel = tel;
        this.dataNasc = dataNasc;
        this.chave = chave;
        this.senha = senha;
    }

    public Integer getId() {
        return id;
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