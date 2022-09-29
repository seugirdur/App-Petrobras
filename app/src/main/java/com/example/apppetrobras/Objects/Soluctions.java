package com.example.apppetrobras.Objects;

public class Soluctions {
    private int idSolucao, idTexto;
    private String texto;

    Soluctions(int idSolucao, int idTexto, String texto){
        this.idSolucao = idSolucao;
        this.idTexto = idTexto;
        this.texto = texto;
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
}
