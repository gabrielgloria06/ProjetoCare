package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.CadastroActivity;
import com.example.myapplication.MainActivity;
import com.example.myapplication.api.RetrofitClient;
import com.example.myapplication.models.user.CadastroUser;
import com.example.myapplication.models.user.Sexo; // Certifique-se de que a enumeração Sexo está definida
import com.example.myapplication.services.user.CadastroUserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Cadastro2Activity extends AppCompatActivity {

    private String genero = ""; // Variável para armazenar o gênero
    private float fatorAtividade; // Variável para armazenar o fator de atividade

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro2_activity);

        Spinner spinner = findViewById(R.id.spinner);
        String[] niveisAf = getResources().getStringArray(R.array.niveis_af);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, niveisAf);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        ImageView btnMuie = findViewById(R.id.btnMuie);
        ImageView btnH = findViewById(R.id.btnH);
        EditText edtAlt = findViewById(R.id.edtAltura);
        EditText edtIdade = findViewById(R.id.edtIdade);
        EditText edtPeso = findViewById(R.id.edtPeso);

        btnMuie.setOnClickListener(v -> {
            genero = "FEMININO";
            btnMuie.setAlpha(0.5f);
            btnH.setAlpha(1f);
        });

        btnH.setOnClickListener(v -> {
            genero = "MASCULINO";
            btnH.setAlpha(0.5f);
            btnMuie.setAlpha(1f);
        });

        findViewById(R.id.cadastrarBtn).setOnClickListener(v -> {
            String nivelAtividade = spinner.getSelectedItem().toString();
            String alturaStr = edtAlt.getText().toString().trim();
            String idadeStr = edtIdade.getText().toString().trim();
            String pesoStr = edtPeso.getText().toString().trim();

            if (alturaStr.isEmpty() || idadeStr.isEmpty() || pesoStr.isEmpty() || genero.isEmpty()) {
                Toast.makeText(this, "Por favor, preencha todos os campos e selecione um gênero.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (nivelAtividade.equals("Selecione um nível de atividade")) {
                Toast.makeText(this, "Selecione um nível de atividade.", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                int idade = Integer.parseInt(idadeStr);
                float peso = Float.parseFloat(pesoStr);
                float altura = Float.parseFloat(alturaStr);

                switch (nivelAtividade) {
                    case "Sedentário":
                        fatorAtividade = 1.2F;
                        break;
                    case "Leve":
                        fatorAtividade = 1.375F;
                        break;
                    case "Moderada":
                        fatorAtividade = 1.55F;
                        break;
                    case "Alta":
                        fatorAtividade = 1.725F;
                        break;
                    case "Muito alta":
                        fatorAtividade = 1.9F;
                        break;
                    default:
                        Toast.makeText(this, "Selecione um nível de atividade.", Toast.LENGTH_SHORT).show();
                        return;
                }

                Intent it = getIntent();
                String nome = it.getStringExtra("nome");
                String email = it.getStringExtra("email");
                String senha = it.getStringExtra("senha");
                String data = it.getStringExtra("data");

                Sexo sexo = genero.equals("FEMININO") ? Sexo.FEMININO : Sexo.MASCULINO;

                CadastroUser cadastroUser = new CadastroUser(nome, senha, email, data, fatorAtividade, idade, peso, sexo, altura);
                CadastroUserService cadastroUserService = RetrofitClient.getClient().create(CadastroUserService.class);
                Call<CadastroUser> call = cadastroUserService.createCadastro(cadastroUser);

                call.enqueue(new Callback<CadastroUser>() {
                    @Override
                    public void onResponse(@NonNull Call<CadastroUser> call, @NonNull Response<CadastroUser> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(Cadastro2Activity.this, "Cadastro criado com sucesso!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Cadastro2Activity.this, MainActivity.class));
                        } else {
                            Toast.makeText(Cadastro2Activity.this, "Erro ao criar cadastro, tente novamente!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<CadastroUser> call, @NonNull Throwable t) {
                        Toast.makeText(Cadastro2Activity.this, "Falha ao criar cadastro: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            } catch (NumberFormatException e) {
                Toast.makeText(this, "Por favor, insira valores numéricos válidos.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();  // Isso faz com que a Activity anterior seja chamada ao pressionar o botão de voltar
    }
}
