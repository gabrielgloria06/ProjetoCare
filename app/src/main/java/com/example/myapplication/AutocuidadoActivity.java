package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.adapters.MetaAdapter;
import com.example.myapplication.models.Meta;
import com.example.myapplication.models.MetaViewModel;

import java.util.List;

public class AutocuidadoActivity extends AppCompatActivity {

    private MetaViewModel metaViewModel;
    private EditText edtMeta;

    private ConstraintLayout meditacao, cp;
    private MetaAdapter metaAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.autocuidado_activity);

        metaViewModel = new ViewModelProvider(this).get(MetaViewModel.class);

        edtMeta = findViewById(R.id.edtMeta);
        Button enviarBtn = findViewById(R.id.enviarBtn);
        Button limparBtn = findViewById(R.id.limparBtn);
        RecyclerView recyclerView = findViewById(R.id.recycle2);
        ImageView btnVoltar = findViewById(R.id.voltar);
        meditacao = findViewById(R.id.meditacao);
        cp = findViewById(R.id.cuidadopessoal);

        metaAdapter = new MetaAdapter((meta, isConcluded) -> metaViewModel.updateMetaStatus(meta, isConcluded));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(metaAdapter);

        // Observa mudanças nas metas
        metaViewModel.getMetas().observe(this, metas -> metaAdapter.setMetas(metas));

        // Botão para adicionar metas
        enviarBtn.setOnClickListener(v -> {
            String textoMeta = edtMeta.getText().toString().trim();
            if (!textoMeta.isEmpty()) {
                Meta novaMeta = new Meta(textoMeta);
                metaViewModel.addMeta(novaMeta);
                edtMeta.setText("");
            }
        });

        // Botão para limpar metas
        limparBtn.setOnClickListener(v -> metaViewModel.clearMetas());

        // Voltar para a tela anterior
        btnVoltar.setOnClickListener(v -> finish());

        meditacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(AutocuidadoActivity.this,MeditacaoActivity.class);
                startActivity(it);
            }
        });

        cp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(AutocuidadoActivity.this,CPActivity.class);
                startActivity(it);
            }
        });
    }
}
