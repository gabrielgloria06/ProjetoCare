package com.example.myapplication;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
import com.example.myapplication.utils.AppSharedPreferences;

import java.util.ArrayList;
import java.util.List;

public class ListaLembretesActivity extends Fragment {

    private RecyclerView recyclerView;
    private LembretesAdapter adapter;

    TextView tvNao;

    private LembretesViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listalembretes_activity, container, false);

        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        viewModel = new ViewModelProvider(requireActivity()).get(LembretesViewModel.class);

        adapter = new LembretesAdapter(new ArrayList<>(), lembrete -> {
            viewModel.removerLembrete(lembrete); // Remove o lembrete do ViewModel
        });        recyclerView.setAdapter(adapter);


        String token = AppSharedPreferences.getInstance(App.getAppContext()).getToken();

        // Observe mudanças na lista de lembretes
        viewModel.getLembretes().observe(getViewLifecycleOwner(), lembretes -> {
            adapter.atualizarLista(lembretes);
        });

        tvNao = view.findViewById(R.id.tvNao);

        if(token != null) {
            String userId = AppSharedPreferences.getInstance(App.getAppContext()).getClaims(token).get("email", String.class);

            List<LembreteModel> lembreteModels = AppSharedPreferences.getInstance(requireContext()).getLembretes(userId);


            if (lembreteModels.isEmpty()) {
                tvNao.setVisibility(View.VISIBLE);
            }
        }


        return view;
    }
}