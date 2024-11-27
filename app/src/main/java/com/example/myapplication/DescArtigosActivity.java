package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DescArtigosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.desc_artigos_activity);

        TextView titulo = findViewById(R.id.tituloArt);
        TextView conteudo = findViewById(R.id.conteudoArt);
        Button btnVolt = findViewById(R.id.voltar);

        SharedPreferences sharedPreferences = getSharedPreferences("dadosartigo", MODE_PRIVATE);

        String tit = sharedPreferences.getString("titulo","");

        titulo.setText(tit);

        String cont = sharedPreferences.getString("conteudo","");
        conteudo.setText(cont);

        btnVolt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DescArtigosActivity.this, ArtigosActivity.class);
                startActivity(intent);
            }
        });

    }
}
