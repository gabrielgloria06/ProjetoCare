package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.databinding.DiagnosticoActivityBinding;

public class DiagnosticoActivity extends AppCompatActivity {

    DiagnosticoActivityBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DiagnosticoActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        double imc = intent.getDoubleExtra("IMC", 0.0);

        // Determina o diagnóstico com base no IMC
        if (imc < 17) {
            binding.txtEstar.setText("Você está muito abaixo do peso!");
            binding.txtDiag.setText("Seu IMC indica que você está muito abaixo do peso. Isso pode significar que o seu corpo não está recebendo os nutrientes necessários para funcionar corretamente. É importante buscar orientação para melhorar a sua alimentação e atender às necessidades nutricionais do seu organismo, garantindo mais energia e bem-estar.");
        } else if (imc >= 17 && imc < 18.5) {
            binding.txtEstar.setText("Você está abaixo do peso ideal.");
            binding.txtDiag.setText("Seu IMC mostra que você está abaixo do peso ideal. Esse quadro pode ser melhorado com uma alimentação mais balanceada e adequada às suas necessidades. Consultar um especialista pode ajudar a encontrar o equilíbrio nutricional para alcançar um peso mais saudável.");
        } else if (imc >= 18.5 && imc < 25) {
            binding.txtEstar.setText("Parabéns! Você está com peso normal.");
            binding.txtDiag.setText("Seu IMC está dentro da faixa saudável, o que é excelente para a sua saúde. Manter uma alimentação equilibrada e hábitos saudáveis é fundamental para continuar preservando seu peso ideal e garantindo qualidade de vida.");
        } else if (imc >= 25 && imc < 30) {
            binding.txtEstar.setText("Você está acima do peso.");
            binding.txtDiag.setText("Seu IMC indica que você está acima do peso. Essa condição pode ser melhorada com ajustes na sua alimentação, focando em refeições mais nutritivas e balanceadas. Uma alimentação adequada pode ajudar a reduzir os riscos associados ao excesso de peso.");
        } else if (imc >= 30 && imc < 35) {
            binding.txtEstar.setText("Você está com obesidade grau I.");
            binding.txtDiag.setText("Seu IMC está na faixa de obesidade grau I. Isso pode ter impactos na sua saúde a longo prazo. Reavaliar a qualidade e a composição das suas refeições é essencial para reduzir os riscos associados à obesidade e alcançar uma melhor qualidade de vida.");
        } else if (imc >= 35 && imc < 40) {
            binding.txtEstar.setText("Você está com obesidade grau II.");
            binding.txtDiag.setText("Seu IMC indica obesidade grau II, um sinal de que sua saúde pode estar comprometida. Adotar mudanças na alimentação, priorizando alimentos mais nutritivos, pode ser um passo importante para melhorar o bem-estar geral e prevenir complicações futuras.");
        } else {
            binding.txtEstar.setText("Você está com obesidade grau III.");
            binding.txtDiag.setText("Seu IMC indica obesidade grau III, o que exige atenção especial para evitar complicações graves à saúde. Mudanças estruturadas na alimentação, com foco em qualidade e equilíbrio, são fundamentais para recuperar a saúde e alcançar um peso mais adequado.");
        }

        binding.verEx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(DiagnosticoActivity.this, ExerciciosActivity.class);
                startActivity(it);
            }
        });
    }
}