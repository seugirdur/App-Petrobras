package com.example.apppetrobras.Objects;

public class UserRelatorioObj {

    int idRelatorio;
    String nome, email, dataProcesso, Setor;


    public UserRelatorioObj(int idRelatorio, String nome, String email, String dataProcesso, String Setor) {
        this.idRelatorio = idRelatorio;
        this.nome = nome;
        this.email = email;
        this.dataProcesso = dataProcesso;
        this.Setor = Setor;
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

    public String getSetor() {
        return Setor;
    }

    public void setsecao(String secao) {
        secao = secao;
    }






}
