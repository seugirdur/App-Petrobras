package com.example.apppetrobras.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroFitClient {

    //Classe de conexao da library do Retrofit para chamar da API

    //Criando as variaveis para uso do Retrofit
    private static final String BASE_URL = "http://api-heroku-petrobras.herokuapp.com";
    private static RetroFitClient mInstance;
    private Retrofit retrofit;

    //Codigo padrao para instancia do Retrofit com o GSON
    private RetroFitClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    //Metodo para instanciar novamente o Retrofit caso ele seja cancelado por algum motivo
    public static synchronized RetroFitClient getInstance() {
        if (mInstance == null) {
            mInstance = new RetroFitClient();
        }
        return mInstance;
    }

    //metodo que faz possivel retornar o retrofit para qualquer classe em vez de ter que escrever o codigo toda vez
    public API getAPI() {
        return retrofit.create(API.class);
    }

}
