package com.example.myapplication.services.user;

import com.example.myapplication.models.user.CadastroUser;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CadastroUserService {
    @POST("/auth/cadastro")
    Call<CadastroUser> createCadastro(@Body CadastroUser cadastro);

}
