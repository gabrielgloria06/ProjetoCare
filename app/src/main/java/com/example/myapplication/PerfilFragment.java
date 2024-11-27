package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.databinding.ActivityPerfilBinding;
import com.example.myapplication.utils.AppSharedPreferences;

import io.jsonwebtoken.Claims;

public class PerfilFragment extends Fragment {

    private ActivityPerfilBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ActivityPerfilBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

    String token = AppSharedPreferences.getInstance(getContext()).getToken();

        if (token != null && !token.isEmpty()) {
            try {
                Claims claims = AppSharedPreferences.getInstance(requireContext()).getClaims(token);
                if (claims != null) {
                    String name = claims.get("name", String.class);
                    String email = claims.get("email", String.class);
                    String birthdate = claims.get("birthdate", String.class);

                    binding.UserNameText2.setText(name != null ? name : "Nome indisponível");
                    binding.userEmailText.setText(email != null ? email : "Email indisponível");
                    binding.userDateText.setText(birthdate != null ? birthdate : "Data de nascimento indisponível");
                    binding.UserNameText.setText(name != null? name : "Nome indisponível");
                } else {
                    Toast.makeText(getContext(), "Erro ao decodificar JWT", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Toast.makeText(getContext(), "Erro ao decodificar JWT: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getContext(), "Token JWT não encontrado", Toast.LENGTH_SHORT).show();
        }


        // Configurar clique para sair
        binding.buttonSair.setOnClickListener(v -> {
            AppSharedPreferences.getInstance(requireContext()).clearToken();
            Intent intent = new Intent(getActivity(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            requireActivity().finish();
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
