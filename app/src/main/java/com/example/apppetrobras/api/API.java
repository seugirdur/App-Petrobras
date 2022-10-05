package com.example.apppetrobras.api;

import com.example.apppetrobras.Objects.AdminObj;
import com.example.apppetrobras.Objects.PerfilObj;
import com.example.apppetrobras.Objects.RelatorioObj;
import com.example.apppetrobras.Objects.SolucoesObj;
import com.example.apppetrobras.Objects.PassosObj;
import com.example.apppetrobras.Objects.LoginObj;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
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
            @Field("isAdmin") int isAdmin,
            @Field("chave") String chave,
            @Field("senha") String senha
    );

    //Rota POST para login do usuario
    @FormUrlEncoded
    @POST("/usuarios/login")
    Call<List<LoginObj>> userLogin(
            @Field("chave") String chave,
            @Field("senha") String senha
    );

    @FormUrlEncoded
    @POST("/usuarios/update")
    Call<List<PerfilObj>> updateUser(
            @Field("nome") String nome,
            @Field("tel") String tel,
            @Field("email") String email,
            @Field("chave") String chave
    );

    //Rota GET para receber o JSON com a lista teste de todos os problemas
    @FormUrlEncoded
    @POST("/problems/internet")
    Call<List<SolucoesObj>> getInternet(
            @Field("idTitulo") int idTitulo
    );

    @FormUrlEncoded
    @POST("/problems/equipamentos")
    Call<List<SolucoesObj>> getEquipamentos(
            @Field("idTitulo") int idTitulo

    );

    @FormUrlEncoded
    @POST("/problems/lentidao")
    Call<List<SolucoesObj>> getLentidao(
            @Field("idTitulo") int idTitulo

    );

    @FormUrlEncoded
    @POST("/problems/outros")
    Call<List<SolucoesObj>> getOutros(
            @Field("idTitulo") int idTitulo

    );



    @FormUrlEncoded
    @POST("/soluctions/textoInternet")
    Call<List<PassosObj>> getTextoInternet(
            @Field("idSolucao") int idSolucao,
            @Field("idtexto") int idtexto

    );

    @FormUrlEncoded
    @POST("/soluctions/textoLentidao")
    Call<List<PassosObj>> getTextoLentidao(
            @Field("idSolucao") int idSolucao,
            @Field("idtexto") int idtexto

    );

    @FormUrlEncoded
    @POST("/soluctions/textoOutros")
    Call<List<PassosObj>> getTextoOutros(
            @Field("idSolucao") int idSolucao,
            @Field("idtexto") int idtexto

    );

    @FormUrlEncoded
    @POST("/soluctions/textoEquipamento")
    Call<List<PassosObj>> getTextoEquipamento(
            @Field("idSolucao") int idSolucao,
            @Field("idtexto") int idtexto

    );

    @FormUrlEncoded
    @POST("/relatorios/access")
    Call<List<RelatorioObj>> getRelatorio(
            @Field("chave") String chave

    );

    @FormUrlEncoded
    @POST("/relatorios/store")
    Call<ResponseBody> postRelatorio(
            @Field("nome") String nome,
            @Field("chave") String chave,
            @Field("dataProcesso") String dataProcesso,
            @Field("idSecao") int idSecao,
            @Field("secao") String secao,
            @Field("idTitulo") int idTitulo,
            @Field("titulo") String titulo,
            @Field("made_check") String made_check

    );

    @GET("/relatorios/admin/aberto")
    Call<List<AdminObj>> getAllRelatoriosOpen();


    @GET("/relatorios/admin/fechado")
    Call<List<AdminObj>> getAllRelatoriosClosed();


}
