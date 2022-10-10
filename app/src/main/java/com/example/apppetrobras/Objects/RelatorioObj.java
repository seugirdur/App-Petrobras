package com.example.apppetrobras.Objects;

public class RelatorioObj {
    String nome, chave, dataProcesso, secao, titulo, made_check;
    int idRelatorio, idSecao, idTitulo;

    public RelatorioObj(String nome, String chave, String dataProcesso, String secao, String titulo, String made_check, int idRelatorio, int idSecao, int idTitulo) {
        this.nome = nome;
        this.chave = chave;
        this.dataProcesso = dataProcesso;
        this.secao = secao;
        this.titulo = titulo;
        this.made_check = made_check;
        this.idRelatorio = idRelatorio;
        this.idSecao = idSecao;
        this.idTitulo = idTitulo;
    }

    public String getNome() {
        return nome;
    }

    public String getChave() {
        return chave;
    }

    public String getDataProcesso() {
        return dataProcesso;
    }

    public String getSecao() {
        return secao;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getIdRelatorio() {
        return idRelatorio;
    }

    public int getIdSecao() {
        return idSecao;
    }

    public int getIdTitulo() {
        return idTitulo;
    }

    public String getMade_check() {
        return made_check;
    }
}

