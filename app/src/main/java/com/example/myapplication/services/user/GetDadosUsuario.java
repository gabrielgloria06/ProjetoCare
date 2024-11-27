package com.example.myapplication.services.user;

import com.example.myapplication.models.user.CadastroUser;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface GetDadosUsuario {

    @GET("/getdadosusuario")
    Call<CadastroUser> getDadosUser(@Header("Authorization") String token);
}
