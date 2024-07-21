package com.example.myapplication.services.user;

import com.example.myapplication.models.user.AuthUser;
import com.example.myapplication.models.user.AuthUserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthUserService {

    @POST("/auth/login")
    Call<AuthUserResponse> authUser(@Body AuthUser authUser);
}
