package com.example.apppetrobras;

import android.widget.TextView;

public class userLogged {
    private String nome;
    private String tel;
    private String email;
    private String chave;
    private String senha;


//    public userLogged(String nome, String tel, String email, String chave, String senha) {
//        super();
//        this.nome = nome;
//        this.tel = tel;
//        this.email = email;
//        this.chave = chave;
//        this.senha = senha;
//    }

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
}



//
//    TextView nome = findViewById(R.id.insertNomeCompleto);
//    TextView tel = findViewById(R.id.insertTelefone);
//    TextView dataNas = findViewById(R.id.insertDataNascimento);
//    TextView email = findViewById(R.id.insertEmail);
//    TextView chave = findViewById(R.id.insertChave);
//    TextView senha = findViewById(R.id.insertSenha);