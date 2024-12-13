package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.databinding.DadosActivityBinding;
import com.example.myapplication.utils.AppSharedPreferences;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import io.jsonwebtoken.Claims;

public class DadosActivity extends AppCompatActivity {

    DadosActivityBinding binding;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DadosActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ImageView btnVolt = findViewById(R.id.voltar);
        btnVolt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });

        String token = AppSharedPreferences.getInstance(getApplicationContext()).getToken();

        if (token != null && !token.isEmpty()) {
            try {
                Claims claims = AppSharedPreferences.getInstance(getApplicationContext()).getClaims(token);
                if (claims != null) {
                    String name = claims.get("name", String.class);
                    String birthdate = claims.get("birthdate", String.class); // Ex: "dd/MM/yyyy"
                    String sexo = claims.get("sexo", String.class); // "M" ou "F"
                    Double peso = claims.get("peso", Double.class); // Peso em kg
                    Double altura = claims.get("altura", Double.class); // Altura em cm
                    Double naf = claims.get("naf", Double.class); // NAF

                    binding.txtNome.setText("Nome: " + name);

                    if (birthdate != null && !birthdate.isEmpty()) {
                        try {
                            // Formatar a data de nascimento
                            DateTimeFormatter formatter = null;
                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                                formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                            }
                            LocalDate birthDate = null;
                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                                birthDate = LocalDate.parse(birthdate, formatter);
                            }
                            LocalDate currentDate = null;
                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                                currentDate = LocalDate.now();
                            }

                            // Calcular a idade
                            int idade = 0;
                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                                idade = Period.between(birthDate, currentDate).getYears();
                            }
                            binding.txtIdade.setText("Idade: " + idade + " anos");

                            if (peso != null && altura != null && sexo != null && naf != null) {
                                // Cálculo do IMC
                                double alturaEmMetros = altura / 100.0;
                                double imc = peso / (alturaEmMetros * alturaEmMetros);
                                binding.txtIMC.setText(String.format("%.2f", imc));

                                // Cálculo da TMB
                                double tmb;
                                if ("M".equalsIgnoreCase(sexo)) {
                                    tmb = 66 + (13.8 * peso) + (5 * altura) - (6.8 * idade);
                                } else {
                                    tmb = 655 + (9.6 * peso) + (1.8 * altura) - (4.7 * idade);
                                }
                                binding.txtTMB.setText(String.format("%.2f kcal", tmb));

                                // Cálculo do GET
                                double get = tmb * naf;
                                binding.txtGET.setText(String.format("%.2f kcal", get));

                                binding.btnDiag.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(DadosActivity.this, DiagnosticoActivity.class);
                                        intent.putExtra("IMC", imc); // valorIMC é o resultado do cálculo do IMC
                                        startActivity(intent);
                                    }
                                });
                            } else {
                                Toast.makeText(getApplicationContext(), "Dados insuficientes para cálculos", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            binding.txtIdade.setText("Data de nascimento inválida");
                            Toast.makeText(getApplicationContext(), "Erro ao processar a data de nascimento: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        binding.txtIdade.setText("Idade indisponível");
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Erro ao decodificar JWT", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Erro ao decodificar JWT: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Token JWT não encontrado", Toast.LENGTH_SHORT).show();
        }


    }
}
