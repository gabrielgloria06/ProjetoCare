package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.myapplication.api.RetrofitClient;
import com.example.myapplication.models.user.AuthUser;
import com.example.myapplication.models.user.AuthUserResponse;
import com.example.myapplication.services.user.AuthUserService;
import com.example.myapplication.utils.AppSharedPreferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends Activity {

    private AuthUserService authUserService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar serviços Retrofit
        authUserService = RetrofitClient.getClient().create(AuthUserService.class);

        // Inicializar views
        TextView textCadastro = findViewById(R.id.semContaText);
        TextView esqueceuText = findViewById(R.id.esqueceuText);
        Button buttonLogin = findViewById(R.id.redefinirButton);
        ImageView mostrarButton = findViewById(R.id.buttonMostrar);
        EditText senhaInput = findViewById(R.id.passwordInput);
        EditText emailInput = findViewById(R.id.emailInput);
        ImageView esconderButton = findViewById(R.id.btnEsconder);

        // Verificar se há um token salvo
        String savedToken = AppSharedPreferences.getInstance(getApplicationContext()).getToken();
        if (savedToken != null && !savedToken.isEmpty()) {
            // Passar o token para a próxima atividade (DashboardActivity)
            Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
            intent.putExtra("token", savedToken);
            startActivity(intent);
            finish();
        }

        // Configurar cliques dos botões e ações
        textCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CadastroActivity.class);
                startActivity(intent);
                finish();
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailInput.getText().toString();
                String senha = senhaInput.getText().toString();

                // Validar campos de entrada
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(MainActivity.this, "Por favor, insira seu e-mail", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(senha)) {
                    Toast.makeText(MainActivity.this, "Por favor, insira sua senha", Toast.LENGTH_SHORT).show();
                    return;
                }

                AuthUser authUser = new AuthUser(email, senha);

                // Chamar API para autenticar usuário
                Call<AuthUserResponse> call = authUserService.authUser(authUser);

                call.enqueue(new Callback<AuthUserResponse>() {
                    @Override
                    public void onResponse(Call<AuthUserResponse> call, Response<AuthUserResponse> response) {
                        if (response.isSuccessful()) {
                            AuthUserResponse authUserResponse = response.body();
                            if (authUserResponse != null) {
                                String token = authUserResponse.getAccessToken();

                                // Verificar se o token não está vazio ou nulo
                                if (token != null && !token.isEmpty()) {
                                    AppSharedPreferences.getInstance(getApplicationContext()).saveToken(token);
                                    Intent intent1 = new Intent(MainActivity.this, DashboardActivity.class);
                                    intent1.putExtra("token", token);
                                    startActivity(intent1);
                                    finish();
                                } else {
                                    Toast.makeText(MainActivity.this, "Token JWT vazio ou nulo", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(MainActivity.this, "Resposta de autenticação vazia", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "Erro ao fazer login: " + response.message(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<AuthUserResponse> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Falha ao fazer login: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        esqueceuText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EsqueceuSenhaActivity.class);
                startActivity(intent);
                finish();
            }
        });

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
    }
}
