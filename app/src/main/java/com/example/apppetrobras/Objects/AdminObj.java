package com.example.apppetrobras.Objects;

public class AdminObj {

    int idRelatorio;
    String nome, email, dataProcesso, secao;


    public AdminObj(int idRelatorio, String nome, String email, String dataProcesso, String secao) {
        this.idRelatorio = idRelatorio;
        this.nome = nome;
        this.email = email;
        this.dataProcesso = dataProcesso;
        secao = secao;
    }

    public int getIdRelatorio() {
        return idRelatorio;
    }

    public void setIdRelatorio(int idRelatorio) {
        this.idRelatorio = idRelatorio;
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

    public String getsecao() {
        return secao;
    }

    public void setsecao(String secao) {
        secao = secao;
    }






}
