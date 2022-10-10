package com.example.apppetrobras.Objects;

public class PassosObj {
    private int idSolucao, idTexto;
    private String texto, url;

    PassosObj(int idSolucao, int idTexto, String texto, String url) {
        this.idSolucao = idSolucao;
        this.idTexto = idTexto;
        this.texto = texto;
        this.url = url;
    }

    public int getIdSolucao() {
        return idSolucao;
    }

    public int getIdtexto() {
        return idTexto;
    }

    public String getTexto() {
        return texto;
    }

    public String getUrl() {
        return url;
    }
}
