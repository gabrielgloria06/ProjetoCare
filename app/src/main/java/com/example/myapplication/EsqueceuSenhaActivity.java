package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.api.RetrofitClient;
import com.example.myapplication.models.user.AlteraSenhaUser;
import com.example.myapplication.services.user.AlteraSenhaService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EsqueceuSenhaActivity extends Activity {

    private AlteraSenhaService alteraSenhaService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senha);
        Intent intent = new Intent(EsqueceuSenhaActivity.this, MainActivity.class);

        ImageView mostrarButton = findViewById(R.id.buttonMostrar);
        ImageView esconderButton = findViewById(R.id.btnEsconder);
        EditText senhaInput = findViewById(R.id.passwordInput);
        Button redefinirButton = findViewById(R.id.redefinirButton);
        EditText emailInput = findViewById(R.id.emailInput);
        alteraSenhaService = RetrofitClient.getClient().create(AlteraSenhaService.class);

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

        esconderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (senhaInput.getInputType() == 1) {
                    senhaInput.setInputType(129);
                    esconderButton.setVisibility(View.INVISIBLE);
                    mostrarButton.setVisibility(View.VISIBLE);
                }
            }
        });

        redefinirButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailInput.getText().toString();
                String novaSenha = senhaInput.getText().toString();

                AlteraSenhaUser alteraSenhaUser = new AlteraSenhaUser(email, novaSenha);

                Call<Void> call = alteraSenhaService.changePassword(alteraSenhaUser);

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(@NonNull Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(EsqueceuSenhaActivity.this, "Senha alterada com sucesso!", Toast.LENGTH_SHORT).show();
                            // Navega de volta para a tela de login
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(EsqueceuSenhaActivity.this, "Erro ao alterar a senha.", Toast.LENGTH_SHORT).show();
                            startActivity(intent);

                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(EsqueceuSenhaActivity.this, "Falha na solicitação: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    }
                });
            }
        });
    }
}
