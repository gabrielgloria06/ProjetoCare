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
        EditText nomeInput = findViewById(R.id.UserNameInput);
        EditText emailInput = findViewById(R.id.emailInput);
        EditText dataInput = findViewById(R.id.DataInput);
        ImageView mostrarButton = findViewById(R.id.buttonMostrar);
        EditText senhaInput = findViewById(R.id.passwordInput);
        ImageView esconderButton = findViewById(R.id.btnEsconder);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = nomeInput.getText().toString();
                String email = emailInput.getText().toString();
                String data = dataInput.getText().toString();
                String senha = senhaInput.getText().toString();

                Intent intent = new Intent(CadastroActivity.this, MainActivity.class);
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
                            esconderButton.setVisibility(View.VISIBLE);
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
