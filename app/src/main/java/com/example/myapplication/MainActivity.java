package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textCadastro = findViewById(R.id.semContaText);

        TextView esqueceuText = findViewById(R.id.esqueceuText);

        Button buttonLogin = findViewById(R.id.redefinirButton);

        ImageView mostrarButton = findViewById(R.id.buttonMostrar);

        EditText senhaInput = findViewById(R.id.passwordInput);

        EditText emailInput = findViewById(R.id.emailInput);

        ImageView esconderButton = findViewById(R.id.btnEsconder);

        textCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.myapp.Action_Cadastro");
                startActivity(intent);
                finish();
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailInput.getText().toString();
                String senha  = senhaInput.getText().toString();



                Toast.makeText(MainActivity.this,"Bem vindo ao nosso aplicativo!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent("com.example.myapp.Dashboard_Action");
                startActivity(intent);
                finish();

            }
        });


        esqueceuText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.myapp.Senha_Action");
                startActivity(intent);
                finish();
            }
        });

        mostrarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (senhaInput.getInputType() == 129) {
                            senhaInput.setInputType(1);
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
                 if(senhaInput.getInputType()==1){
                     senhaInput.setInputType(129);
                     esconderButton.setVisibility(View.INVISIBLE);
                     mostrarButton.setVisibility(View.VISIBLE);
                 }
            }
        });




    }
}

