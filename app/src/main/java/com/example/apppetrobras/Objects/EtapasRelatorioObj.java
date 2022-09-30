package com.example.apppetrobras.Objects;

public class EtapasRelatorioObj {
    private String titulo, subtitulo;
    private int imagem;



    public EtapasRelatorioObj(String titulo, String subtitulo, int imagem){
        this.titulo = titulo;
        this.subtitulo = subtitulo;
        this.imagem = imagem;
    }
    public String getTitulo() {
        return titulo;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public int getImagem() {
        return imagem;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }

}
