package com.example.apppetrobras.Objects;

public class ResponseBodyObj {
    String mensagem, situation;

    public ResponseBodyObj(String mensagem, String situation) {
        this.mensagem = mensagem;
        this.situation = situation;
    }

    public String getMensagem() {
        return mensagem;
    }

    public String getSituation() {
        return situation;
    }
}
