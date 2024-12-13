package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.databinding.ExerciciosActivityBinding;

public class ExerciciosActivity extends AppCompatActivity {

    private ExerciciosActivityBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inicializando o binding corretamente
        binding = ExerciciosActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Definindo os listeners para os botões
        binding.btnAgach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExerciciosActivity.this, AgachamentoActivity.class);
                startActivity(intent);
            }
        });

        ImageView btnVolt = findViewById(R.id.voltar);
        btnVolt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.btnFlex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExerciciosActivity.this, FlexaoActivity.class);
                startActivity(intent);
            }
        });

        binding.btnPoli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExerciciosActivity.this, PolichineloActivity.class);
                startActivity(intent);
            }
        });
    }
}
