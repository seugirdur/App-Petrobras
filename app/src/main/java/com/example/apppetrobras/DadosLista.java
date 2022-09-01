package com.example.apppetrobras;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DadosLista {

    String text;
    int image, id;

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

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

}
