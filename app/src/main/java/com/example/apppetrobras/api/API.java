package com.example.apppetrobras.api;

import com.example.apppetrobras.Problems;
import com.example.apppetrobras.models.UserAPI;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface API {

    @FormUrlEncoded
    @POST("/usuarios/cadastro")
    Call<ResponseBody> createUser(
            @Field("nome") String nome,
            @Field("email") String email,
            @Field("tel") String tel,
            @Field("dataNasc") String dataNasc,
            @Field("chave") String chave,
            @Field("senha") String senha
    );

    @FormUrlEncoded
    @POST("/usuarios/login")
    Call<List<UserAPI>> userLogin(
            @Field("chave") String chave,
            @Field("senha") String senha
    );

    @GET("/problems")
    Call<List<Problems>> getProblems();
}
