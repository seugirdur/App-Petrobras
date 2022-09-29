package com.example.apppetrobras.Objects;

public class DadosLista {

    String text;
    int image, id;

    public DadosLista(String text){
        this.text = text;
    }

    public DadosLista(String text, int id, int image){
        this.text = text;
        this.image = image;
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getImage() { return image; }

    public void setImage(int image) { this.image = image; }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

}
