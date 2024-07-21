package com.example.myapplication.services.user;

import com.example.myapplication.models.user.AlteraSenhaUser;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PUT;

public interface AlteraSenhaService {
    @PUT("/auth/change-password")
    Call<Void> changePassword(@Body AlteraSenhaUser alteraSenhaUser);
}
