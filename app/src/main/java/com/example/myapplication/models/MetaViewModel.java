package com.example.myapplication.models;

import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.App;
import com.example.myapplication.utils.AppSharedPreferences;

import java.util.ArrayList;
import java.util.List;

public class MetaViewModel extends ViewModel {
    private final MutableLiveData<List<Meta>> metasLive = new MutableLiveData<>(new ArrayList<>());
    private List<Meta> metas = new ArrayList<>();

    String token = AppSharedPreferences.getInstance(App.getAppContext()).getToken();
    String userId = AppSharedPreferences.getInstance(App.getAppContext()).getClaims(token).get("email", String.class);

    public MetaViewModel() {
        List<Meta> metasSalvas = AppSharedPreferences.getInstance(App.getAppContext()).getMetas(userId);
        metas.addAll(metasSalvas);
        metasLive.setValue(metas);
    }

    // Método para obter as metas
    public LiveData<List<Meta>> getMetas() {
        return metasLive;
    }

    // Método para adicionar uma nova meta
    public void addMeta(Meta meta) {
        if (token != null) {
            if (metas.size() >= 4) {
                // Notifica que o limite foi atingido
                Toast.makeText(App.getAppContext(), "Você só pode adicionar até 4 metas.", Toast.LENGTH_SHORT).show();
                return;
            }
            metas.add(meta);
            metasLive.setValue(metas);
            AppSharedPreferences.getInstance(App.getAppContext()).saveMetas(metas, userId);
        }
    }

    // Método para marcar uma meta como concluída
    public void updateMetaStatus(Meta meta, boolean isConcluded) {
        if (meta != null) {
            meta.setConcluida(isConcluded);
            metasLive.setValue(metas);
            AppSharedPreferences.getInstance(App.getAppContext()).saveMetas(metas, userId); // Salva a lista atualizada
        }
    }

    public void clearMetas() {
        metas.clear(); // Remove todas as metas
        metasLive.setValue(metas); // Atualiza o LiveData
        AppSharedPreferences.getInstance(App.getAppContext()).saveMetas(metas, userId); // Salva o estado limpo
    }
}
