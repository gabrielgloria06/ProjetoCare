package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.adapters.LembretesAdapter;
import com.example.myapplication.models.LembreteModel;
import com.example.myapplication.models.LembretesViewModel;

import java.util.ArrayList;
import java.util.List;

public class ListaLembretesActivity extends Fragment {

    private RecyclerView recyclerView;
    private LembretesAdapter adapter;
    private LembretesViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listalembretes_activity, container, false);

        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        viewModel = new ViewModelProvider(requireActivity()).get(LembretesViewModel.class);

        adapter = new LembretesAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        // Observe mudanças na lista de lembretes
        viewModel.getLembretes().observe(getViewLifecycleOwner(), lembretes -> {
            adapter.atualizarLista(lembretes);
        });

        return view;
    }
}