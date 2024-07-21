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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.api.RetrofitClient;
import com.example.myapplication.models.user.CadastroUser;
import com.example.myapplication.services.user.CadastroUserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CadastroActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_cadastro);

        Button button = findViewById(R.id.cadastrarButton);
        EditText nomeInput = findViewById(R.id.UserNameInput);
        EditText emailInput = findViewById(R.id.emailInput);
        EditText dataInput = findViewById(R.id.etdata);
        ImageView mostrarButton = findViewById(R.id.buttonMostrar);
        EditText senhaInput = findViewById(R.id.passwordInput);
        ImageView esconderButton = findViewById(R.id.btnEsconder);
        CadastroUserService cadastroUserService = RetrofitClient.getClient().create(CadastroUserService.class);
        Intent intent = new Intent(CadastroActivity.this,MainActivity.class);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = nomeInput.getText().toString();
                String email = emailInput.getText().toString();
                String data = dataInput.getText().toString();
                String senha = senhaInput.getText().toString();
                CadastroUser cadastroUser = new CadastroUser(nome,senha,email,data);
                Call<CadastroUser> call = cadastroUserService.createCadastro(cadastroUser);
                call.enqueue(new Callback<CadastroUser>() {
                    @Override
                    public void onResponse(@NonNull Call<CadastroUser> call, @NonNull Response<CadastroUser> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(CadastroActivity.this, "Cadastro criado com sucesso!", Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                        } else {
                            Toast.makeText(CadastroActivity.this, "Erro ao criar cadastro, tente novamente!", Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<CadastroUser> call, @NonNull Throwable t) {
                        Toast.makeText(CadastroActivity.this, "Falha ao criar cadastro: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                     }
                });

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
