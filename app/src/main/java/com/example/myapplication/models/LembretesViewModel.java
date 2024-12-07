package com.example.myapplication.models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.App;
import com.example.myapplication.utils.AppSharedPreferences;

import java.util.ArrayList;
import java.util.List;

public class LembretesViewModel extends ViewModel {
    private final MutableLiveData<List<LembreteModel>> lembretesLiveData = new MutableLiveData<>();
    private List<LembreteModel> lembretes = new ArrayList<>();

    String token = AppSharedPreferences.getInstance(App.getAppContext()).getToken();

    String userId = AppSharedPreferences.getInstance(App.getAppContext()).getClaims(token).get("email", String.class);


    public LembretesViewModel() {
        // Carregar lembretes salvos
        List<LembreteModel> lembretesSalvos = AppSharedPreferences.getInstance(App.getAppContext()).getLembretes(userId);
        lembretes.addAll(lembretesSalvos);
        lembretesLiveData.setValue(lembretes);
    }

    public LiveData<List<LembreteModel>> getLembretes() {
        return lembretesLiveData;
    }

    public void adicionarLembrete(LembreteModel lembrete) {
        if(token != null){
            lembretes.add(lembrete);
            lembretesLiveData.setValue(lembretes);
            // Salvar nos SharedPreferences
            AppSharedPreferences.getInstance(App.getAppContext()).saveLembretes(lembretes,userId);
        }

    }

    public void removerLembrete(LembreteModel lembrete) {
        lembretes.remove(lembrete); // Remove o lembrete da lista
        lembretesLiveData.setValue(lembretes); // Atualiza a LiveData

        // Salvar a lista atualizada nos SharedPreferences
        AppSharedPreferences.getInstance(App.getAppContext()).saveLembretes(lembretes,userId);
    }
}
