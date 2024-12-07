package com.example.myapplication.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.myapplication.models.LembreteModel;
import com.example.myapplication.models.Meta;
import com.fasterxml.jackson.databind.deser.std.MapEntryDeserializer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import io.jsonwebtoken.Claims;

public class AppSharedPreferences {
    private static final String PREF_NAME = "app_preferences";
    private static final String LEMBRETES_KEY = "lembretes";
    private static final String METAS_KEY = "metas";


    private static SharedPreferences sharedPreferences;
    private static AppSharedPreferences instance;

    private AppSharedPreferences(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static synchronized AppSharedPreferences getInstance(Context context) {
        if (instance == null) {
            instance = new AppSharedPreferences(context);
        }
        return instance;
    }

    public void saveToken(String token) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token_jwt", token);
        editor.apply();
    }

    public String getToken() {
        return sharedPreferences.getString("token_jwt", null);
    }

    public Claims getClaims(String token) {
        if (token == null || token.isEmpty()) {
            return null;
        }
        return JwtUtils.decodeJWT(token, "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA3FlqJr5TRskIQIgdE3Dd7D9lboWdcTUT8a+fJR7MAvQm7XXNoYkm3v7MQL1NYtDvL2l8CAnc0WdSTINU6IRvc5Kqo2Q4csNX9SHOmEfzoROjQqahEcve1jBXluoCXdYuYpx4/1tfRgG6ii4Uhxh6iI8qNMJQX+fLfqhbfYfxBQVRPywBkAbIP4x1EAsbC6FSNmkhCxiMNqEgxaIpY8C2kJdJ/ZIV+WW4noDdzpKqHcwmB8FsrumlVY/DNVvUSDIipiq9PbP4H99TXN1o746oRaNa07rq1hoCgMSSy+85SagCoxlmyE+D+of9SsMY8Ol9t0rdzpobBuhyJ/o5dfvjKwIDAQAB");
    }

    public void clearToken() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("token_jwt");
        editor.apply();
    }

    public void saveLembretes(List<LembreteModel> lembretes, String userId) {
        if (userId == null || lembretes == null) return;

        Gson gson = new Gson();
        String json = gson.toJson(lembretes);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(LEMBRETES_KEY+ "_"+ userId, json);
        editor.apply();
    }

    public List<LembreteModel> getLembretes(String userId) {
        String json = sharedPreferences.getString(LEMBRETES_KEY + "_" + userId, null);
        if (json == null) return new ArrayList<>();

        Gson gson = new Gson();
        Type type = new TypeToken<List<LembreteModel>>() {}.getType();
        return gson.fromJson(json, type);
    }

    public void saveMetas(List<Meta> metas, String userId) {
        if (userId == null || metas == null) return;

        Gson gson = new Gson();
        String json = gson.toJson(metas);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(METAS_KEY+ "_"+ userId, json);
        editor.apply();
    }

    public List<Meta> getMetas(String userId) {
        String json = sharedPreferences.getString(METAS_KEY + "_" + userId, null);
        if (json == null) return new ArrayList<>();

        Gson gson = new Gson();
        Type type = new TypeToken<List<Meta>>() {}.getType();
        return gson.fromJson(json, type);
    }
}