package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.myapplication.api.RetrofitClient;
import com.example.myapplication.services.user.CadastroUserService;

public class CadastroActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_cadastro);

        Button button = findViewById(R.id.continuarBtn);
        EditText nomeInput = findViewById(R.id.UserNameInput);
        EditText emailInput = findViewById(R.id.emailInput);
        EditText dataInput = findViewById(R.id.etdata);
        EditText senhaInput = findViewById(R.id.passwordInput);
        ImageView mostrarButton = findViewById(R.id.buttonMostrar);
        ImageView esconderButton = findViewById(R.id.btnEsconder);

        button.setOnClickListener(v -> {
            String nome = nomeInput.getText().toString().trim();
            String email = emailInput.getText().toString().trim();
            String data = dataInput.getText().toString().trim();
            String senha = senhaInput.getText().toString().trim();

            if (nome.isEmpty() || email.isEmpty() || data.isEmpty() || senha.isEmpty()) {
                Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Digite um e-mail válido.", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent it = new Intent(CadastroActivity.this, Cadastro2Activity.class);
            it.putExtra("nome", nome);
            it.putExtra("email", email);
            it.putExtra("senha", senha);
            it.putExtra("data", data);
            startActivity(it);
        });

        mostrarButton.setOnClickListener(v -> {
            if (senhaInput.getInputType() == 129) {
                senhaInput.setInputType(1);
                mostrarButton.setVisibility(View.INVISIBLE);
                esconderButton.setVisibility(View.VISIBLE);
            }
        });

        esconderButton.setOnClickListener(v -> {
            if (senhaInput.getInputType() == 1) {
                senhaInput.setInputType(129);
                esconderButton.setVisibility(View.INVISIBLE);
                mostrarButton.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();  // Isso faz com que a Activity anterior seja chamada ao pressionar o botão de voltar
    }

}

