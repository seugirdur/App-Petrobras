package com.example.apppetrobras.Objects;

public class AdminObj {

    int id;
    String nome, email, dataProcesso, Setor;


    public AdminObj(int id, String nome, String email, String dataProcesso, String setor) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.dataProcesso = dataProcesso;
        Setor = setor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDataProcesso() {
        return dataProcesso;
    }

    public void setDataProcesso(String dataProcesso) {
        this.dataProcesso = dataProcesso;
    }

    public String getSetor() {
        return Setor;
    }

    public void setSetor(String setor) {
        Setor = setor;
    }






}
