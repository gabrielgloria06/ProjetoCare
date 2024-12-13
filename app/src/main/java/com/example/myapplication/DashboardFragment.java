package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.databinding.ActivityDashboardBinding;
import com.example.myapplication.models.LembreteModel;
import com.example.myapplication.models.Meta;
import com.example.myapplication.utils.AppSharedPreferences;

import java.util.List;

import io.jsonwebtoken.Claims;

public class DashboardFragment extends Fragment {

    private ActivityDashboardBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ActivityDashboardBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        String token = AppSharedPreferences.getInstance(requireContext()).getToken();

        if (token != null) {
            Claims claims = AppSharedPreferences.getInstance(requireContext()).getClaims(token);
            if (claims != null) {
                String nomeCompleto = claims.get("name", String.class);
                String primeiroNome = extractFirstName(nomeCompleto);
                binding.UserNameText.setText(primeiroNome != null ? primeiroNome : "Indisponível");
                String userId = claims.get("email", String.class);


                // Recuperar lembretes salvos
                List<LembreteModel> lembretes = AppSharedPreferences.getInstance(requireContext()).getLembretes(userId);
                if (!lembretes.isEmpty()) {
                    Toast.makeText(requireContext(), "Lembretes carregados com sucesso!", Toast.LENGTH_SHORT).show();
                }
            String userId2 = claims.get("email", String.class);

            List<Meta> metas = AppSharedPreferences.getInstance(requireContext()).getMetas(userId2);
            if(!metas.isEmpty()){
                Toast.makeText(requireContext(), "Metas carregadss com sucesso!", Toast.LENGTH_SHORT).show();
            }
        }}

        binding.btnCuriosidades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(requireContext(), ArtigosActivity.class);
                startActivity(it);
            }
        });

        binding.autocuidado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(requireContext(), AutocuidadoActivity.class);
                startActivity(it);
            }
        });

        binding.sonobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(requireContext(), SonoActivity.class);
                startActivity(it);
            }
        });

        binding.bemestar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(requireContext(), DadosActivity.class);
                startActivity(it);
            }
        });

        binding.btnEx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(requireContext(), ExerciciosActivity.class);
                startActivity(it);
            }
        });

        return view;
    }

    private String extractFirstName(String nomeCompleto) {
        if (nomeCompleto != null && !nomeCompleto.isEmpty()) {
            return nomeCompleto.split(" ")[0];
        }
        return null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
