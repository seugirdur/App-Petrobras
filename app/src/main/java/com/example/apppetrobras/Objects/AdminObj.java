package com.example.apppetrobras.Objects;

public class AdminObj {

    int idRelatorio;
    String nome, email, dataProcesso, secao, made_check;

    public AdminObj(int idRelatorio, String nome, String email, String dataProcesso, String secao, String made_check) {
        this.idRelatorio = idRelatorio;
        this.nome = nome;
        this.email = email;
        this.dataProcesso = dataProcesso;
        this.secao = secao;
        this.made_check = made_check;
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

    public String getSecao() {
        return secao;
    }

    public void setSecao(String secao) {
        this.secao = secao;
    }

    public String getMade_check() {
        return made_check;
    }

    public void setMade_check(String made_check) {
        this.made_check = made_check;
    }
}