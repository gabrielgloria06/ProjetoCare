package com.example.myapplication;

import android.app.sdksandbox.LoadSdkException;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.adapters.LembretesAdapter;
import com.example.myapplication.models.LembreteModel;
import com.example.myapplication.models.LembretesViewModel;

import java.util.ArrayList;
import java.util.List;

public class LembretesFragment extends Fragment {
    private EditText edtnome, edtdia, edtlugar, edthora;
    private Button btnadd, btncontinuar;
    private ImageView voltar;

    private LembretesViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lembretesactivity, container, false);

        edtnome = view.findViewById(R.id.tvnome);
        edtdia = view.findViewById(R.id.tvdia);
        edthora = view.findViewById(R.id.tvhora);
        edtlugar = view.findViewById(R.id.tvlugar);
        btnadd = view.findViewById(R.id.adicionarBtn);
        btncontinuar = view.findViewById(R.id.verLembretes);
        voltar = view.findViewById(R.id.voltar);

        viewModel = new ViewModelProvider(requireActivity()).get(LembretesViewModel.class);

        btnadd.setOnClickListener(v -> {
            String nome = edtnome.getText().toString();
            String dia = edtdia.getText().toString();
            String hora = edthora.getText().toString();
            String lugar = edtlugar.getText().toString();

            if (!nome.isEmpty() && !dia.isEmpty() && !hora.isEmpty() && !lugar.isEmpty()) {
                LembreteModel novoLembrete = new LembreteModel(nome, dia, hora, lugar, false);
                viewModel.adicionarLembrete(novoLembrete);

                edtnome.setText("");
                edtdia.setText("");
                edthora.setText("");
                edtlugar.setText("");
            }
        });

        // Botão Ver Lembretes
        btncontinuar.setOnClickListener(v -> {
            // Substitui o fragment atual pelo ListaLembretesActivity (Fragment)
            getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frameLayout, new ListaLembretesActivity())
                    .addToBackStack(null) // Permite voltar para o fragment atual
                    .commit();
        });

        // Botão Voltar
        voltar.setOnClickListener(v -> {
            // Volta para o fragment anterior na pilha de navegação
            getParentFragmentManager().popBackStack();
        });


        return view;
    }
}
