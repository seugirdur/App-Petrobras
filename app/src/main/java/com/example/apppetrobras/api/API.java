package com.example.apppetrobras.api;

import com.example.apppetrobras.models.LoginResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface API {

    @FormUrlEncoded
    @POST("/usuarios")
    Call<ResponseBody> createUser(
            @Field("nome") String nome,
            @Field("email") String email,
            @Field("tel") String tel,
            @Field("dataNasc") String dataNasc,
            @Field("chave") String chave,
            @Field("senha") String senha
    );

    @FormUrlEncoded
    @GET("/usuarios")
    Call<LoginResponse> userLogin();
}
