package com.example.apppetrobras.api;

import com.example.apppetrobras.Objects.CRelatorio;
import com.example.apppetrobras.Objects.Problems;
import com.example.apppetrobras.Objects.Soluctions;
import com.example.apppetrobras.Objects.UserAPI;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface  API {

    //Classe com todos os metodos para as diferentes rotas da nossa API


    //Rota POST para cadastro
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

    //Rota POST para login do usuario
    @FormUrlEncoded
    @POST("/usuarios/login")
    Call<List<UserAPI>> userLogin(
            @Field("chave") String chave,
            @Field("senha") String senha
    );

    //Rota GET para receber o JSON com a lista teste de todos os problemas
    @FormUrlEncoded
    @POST("/problems/internet")
    Call<List<Problems>> getInternet(
            @Field("idTitulo") int idTitulo
    );

    @FormUrlEncoded
    @POST("/problems/equipamentos")
    Call<List<Problems>> getEquipamentos(
            @Field("idTitulo") int idTitulo

    );

    @FormUrlEncoded
    @POST("/problems/lentidao")
    Call<List<Problems>> getLentidao(
            @Field("idTitulo") int idTitulo

    );

    @FormUrlEncoded
    @POST("/problems/outros")
    Call<List<Problems>> getOutros(
            @Field("idTitulo") int idTitulo

    );



    @FormUrlEncoded
    @POST("/soluctions/textoInternet")
    Call<List<Soluctions>> getTextoInternet(
            @Field("idSolucao") int idSolucao,
            @Field("idtexto") int idtexto

    );

    @FormUrlEncoded
    @POST("/soluctions/textoLentidao")
    Call<List<Soluctions>> getTextoLentidao(
            @Field("idSolucao") int idSolucao,
            @Field("idtexto") int idtexto

    );

    @FormUrlEncoded
    @POST("/soluctions/textoOutros")
    Call<List<Soluctions>> getTextoOutros(
            @Field("idSolucao") int idSolucao,
            @Field("idtexto") int idtexto

    );

    @FormUrlEncoded
    @POST("/soluctions/textoEquipamento")
    Call<List<Soluctions>> getTextoEquipamento(
            @Field("idSolucao") int idSolucao,
            @Field("idtexto") int idtexto

    );

    @FormUrlEncoded
    @POST("/relatorios")
    Call<List<CRelatorio>> getRelatorio(
            @Field("chave") String chave

    );
}
