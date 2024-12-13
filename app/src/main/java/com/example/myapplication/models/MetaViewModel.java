package com.example.myapplication.models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.App;
import com.example.myapplication.utils.AppSharedPreferences;

import java.util.ArrayList;
import java.util.List;

public class MetaViewModel extends ViewModel {
    private final MutableLiveData<List<Meta>> metasLive = new MutableLiveData<>(new ArrayList<>());
    private final List<Meta> metas = new ArrayList<>();

    String token = AppSharedPreferences.getInstance(App.getAppContext()).getToken();
    String userId = AppSharedPreferences.getInstance(App.getAppContext()).getClaims(token).get("email", String.class);

    public MetaViewModel() {
        List<Meta> metasSalvas = AppSharedPreferences.getInstance(App.getAppContext()).getMetas(userId);
        metas.addAll(metasSalvas);
        metasLive.setValue(new ArrayList<>(metas));
    }

    public LiveData<List<Meta>> getMetas() {
        return metasLive;
    }

    public void addMeta(Meta meta) {
        metas.add(meta);
        metasLive.setValue(new ArrayList<>(metas));
        AppSharedPreferences.getInstance(App.getAppContext()).saveMetas(metas, userId);
    }

    public void updateMetaStatus(Meta meta, boolean isConcluded) {
        if (meta != null) {
            meta.setConcluida(isConcluded);
            metasLive.setValue(new ArrayList<>(metas));
            AppSharedPreferences.getInstance(App.getAppContext()).saveMetas(metas, userId);
        }
    }

    public void clearMetas() {
        metas.clear();
        metasLive.setValue(new ArrayList<>());
        AppSharedPreferences.getInstance(App.getAppContext()).saveMetas(metas, userId);
    }
}
