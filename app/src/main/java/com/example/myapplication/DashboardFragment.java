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

import com.example.myapplication.databinding.ActivityDashboardBinding;
import com.example.myapplication.utils.AppSharedPreferences;
import io.jsonwebtoken.Claims;

public class DashboardFragment extends Fragment {

    private ActivityDashboardBinding binding;

    @Nullable
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
            }
        }

        binding.btnCuriosidades.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ArtigosActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            requireActivity().finish();
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
