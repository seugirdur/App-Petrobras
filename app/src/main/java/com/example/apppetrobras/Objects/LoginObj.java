package com.example.apppetrobras.Objects;

public class LoginObj {

    //Classe publica para guardar as informaçoes do usuario que fizer login para ficar disponivel para classes que precisem dessa informaçao
    private String nome, email, tel, chave, senha, dataNasc;
    private Integer id, isAdmin;

    public LoginObj(Integer id, String nome, String email, String tel, String chave, String senha, int isAdmin) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.tel = tel;
        this.dataNasc = dataNasc;
        this.chave = chave;
        this.senha = senha;
        this.isAdmin = isAdmin;
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

    public Integer getIsAdmin() {
        return isAdmin;
    }
}