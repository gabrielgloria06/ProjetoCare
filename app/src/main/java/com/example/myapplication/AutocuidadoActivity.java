package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.adapters.MetaAdapter;
import com.example.myapplication.models.Meta;
import com.example.myapplication.models.MetaViewModel;

import java.util.ArrayList;
import java.util.List;

public class AutocuidadoActivity extends AppCompatActivity {

    public MetaViewModel metaViewModel;
    private EditText edtMeta;
    private Button enviarBtn, limparBtn;
    private RecyclerView recyclerView;
    private MetaAdapter metaAdapter;
    private ConstraintLayout meditacao, cp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.autocuidado_activity);

        metaViewModel = new ViewModelProvider(this).get(MetaViewModel.class);
        edtMeta = findViewById(R.id.edtMeta);
        enviarBtn = findViewById(R.id.enviarBtn);
        recyclerView = findViewById(R.id.recycle2);
        meditacao = findViewById(R.id.meditacao);
        cp = findViewById(R.id.cuidadopessoal);
        limparBtn = findViewById(R.id.limparBtn);
        ImageView btnVoltar = findViewById(R.id.voltar);

        metaAdapter = new MetaAdapter(new ArrayList<>(), (meta, isConcluded) -> {
            metaViewModel.updateMetaStatus(meta, isConcluded); // Atualiza o status da meta
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(metaAdapter);

        // Observar mudanças nas metas
        metaViewModel.getMetas().observe(this, new Observer<List<Meta>>() {
            @Override
            public void onChanged(List<Meta> metas) {
                metaAdapter.updateMetas(metas); // Atualizar RecyclerView

            }
        });

        enviarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String texto = edtMeta.getText().toString();
                Meta meta = new Meta(texto);
                metaViewModel.addMeta(meta);
                edtMeta.setText(""); // Limpar o campo de entrada
            }
        });

        limparBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                metaViewModel.clearMetas(); // Chama o método para limpar as metas
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}