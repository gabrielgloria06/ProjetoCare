package com.example.myapplication.models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.models.LembreteModel;

import java.util.ArrayList;
import java.util.List;

public class LembretesViewModel extends ViewModel {
    private final MutableLiveData<List<LembreteModel>> lembretes = new MutableLiveData<>(new ArrayList<>());

    public LiveData<List<LembreteModel>> getLembretes() {
        return lembretes;
    }

    public void adicionarLembrete(LembreteModel lembrete) {
        List<LembreteModel> listaAtual = lembretes.getValue();
        if (listaAtual != null) {
            listaAtual.add(lembrete);
            lembretes.setValue(listaAtual);
        }
    }
}
