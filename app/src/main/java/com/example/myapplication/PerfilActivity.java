package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.utils.AppSharedPreferences;
import com.example.myapplication.utils.JwtUtils;

import io.jsonwebtoken.Claims;

public class PerfilActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        TextView nomeText = findViewById(R.id.UserNameText2);
        TextView emailText = findViewById(R.id.userEmailText);
        TextView dataText = findViewById(R.id.userDateText);
        ImageView inicioBtn = findViewById(R.id.inicioButton);
        TextView nomeText2 = findViewById(R.id.UserNameText);
        Button buttonSair = findViewById(R.id.buttonSair);

        // Receber o token da DashboardActivity
        Intent intent = getIntent();
        String token = intent.getStringExtra("token");

        if (token != null && !token.isEmpty()) {
            try {
                Claims claims = AppSharedPreferences.getInstance(getApplicationContext()).getClaims(token);
                if (claims != null) {
                    String name = claims.get("name", String.class);
                    String email = claims.get("email", String.class);
                    String birthdate = claims.get("birthdate", String.class);

                    nomeText.setText(name != null ? name : "Nome indisponível");
                    emailText.setText(email != null ? email : "Email indisponível");
                    dataText.setText(birthdate != null ? birthdate : "Data de nascimento indisponível");
                    nomeText2.setText(name != null? name : "Nome indisponivel" );
                } else {
                    Toast.makeText(this, "Erro ao decodificar JWT", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Toast.makeText(this, "Erro ao decodificar JWT: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Token JWT não encontrado", Toast.LENGTH_SHORT).show();
        }


        inicioBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        buttonSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppSharedPreferences.getInstance(getApplicationContext()).clearToken();
                Intent intent = new Intent(PerfilActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

    }
}
