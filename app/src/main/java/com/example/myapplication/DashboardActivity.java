package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.myapplication.utils.AppSharedPreferences;

import io.jsonwebtoken.Claims;

public class DashboardActivity extends Activity {

    private String token;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        ImageView buttonPerfil = findViewById(R.id.buttonPerfil);
        TextView userNameText = findViewById(R.id.UserNameText);

        Intent intent = getIntent();
        token = intent.getStringExtra("token");

        Claims claims = AppSharedPreferences.getInstance(getApplicationContext()).getClaims(token);

        if (claims != null) {
            String nomeCompleto = claims.get("name", String.class);
            String primeiroNome = extractFirstName(nomeCompleto);
            userNameText.setText(primeiroNome != null ? primeiroNome : "Indisponível");
        }

        buttonPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPerfil = new Intent(DashboardActivity.this, PerfilActivity.class);
                intentPerfil.putExtra("token", token);
                startActivity(intentPerfil);
            }
        });
    }

    private String extractFirstName(String fullName) {
        if (fullName != null) {
            int firstSpaceIndex = fullName.indexOf(' ');
            if (firstSpaceIndex != -1) {
                return fullName.substring(0, firstSpaceIndex);
            } else {
                return fullName;
            }
        }
        return null;
    }
}