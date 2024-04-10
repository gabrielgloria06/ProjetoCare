package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class CadastroActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_cadastro);

        Button button = findViewById(R.id.cadastrarButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ImageView mostrarButton = findViewById(R.id.buttonMostrar);

        EditText passwordInput = findViewById(R.id.passwordInput);

        ImageView esconderButton = findViewById(R.id.btnEsconder);

        
        mostrarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (passwordInput.getInputType() == 129) {
                            passwordInput.setInputType(1);
                            mostrarButton.setVisibility(View.INVISIBLE);
                            esconderButton.setVisibility(View.VISIBLE); // Tornar o botão "esconder" visível
                        }
                    }
                });
            }
        });

        esconderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(passwordInput.getInputType()==1){
                    passwordInput.setInputType(129);
                    esconderButton.setVisibility(View.INVISIBLE);
                    mostrarButton.setVisibility(View.VISIBLE);
                }
            }
        });



    }
}
