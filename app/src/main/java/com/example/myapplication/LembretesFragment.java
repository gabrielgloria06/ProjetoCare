package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.example.myapplication.models.LembreteModel;
import com.example.myapplication.models.LembretesViewModel;
import com.example.myapplication.utils.NotificationWorker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class LembretesFragment extends Fragment {
    private EditText edtnome, edtdia, edtlugar, edthora;
    private Button btnadd, btncontinuar;
    private ImageView voltar;

    private LembretesViewModel viewModel;

    private boolean validarData(String data) {
        String regex = "^(0[1-9]|[1-2][0-9]|3[0-1])/(0[1-9]|1[0-2])/\\d{4}$";
        return data.matches(regex);
    }

    private boolean validarHora(String hora) {
        // Formato esperado: "HH:mm" (exemplo: "14:00")
        String regex = "^([01]\\d|2[0-3]):([0-5]\\d)$";
        return hora.matches(regex);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lembretesactivity, container, false);

        edtnome = view.findViewById(R.id.tvnome);
        edtdia = view.findViewById(R.id.tvdia);
        edthora = view.findViewById(R.id.tvhora);
        edtlugar = view.findViewById(R.id.tv2lugar);
        btnadd = view.findViewById(R.id.adicionarBtn);
        btncontinuar = view.findViewById(R.id.verLembretes);
        voltar = view.findViewById(R.id.voltar);

        viewModel = new ViewModelProvider(requireActivity()).get(LembretesViewModel.class);


        btnadd.setOnClickListener(v -> {
            String nome = edtnome.getText().toString();
            String dia = edtdia.getText().toString();
            String hora = edthora.getText().toString();
            String lugar = edtlugar.getText().toString();

            if (nome.isEmpty() || dia.isEmpty() || hora.isEmpty() || lugar.isEmpty()) {
                Toast.makeText(getContext(), "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!validarData(dia)) {
                Toast.makeText(getContext(), "Data inválida! Use o formato: dd/mm/yyyy", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!validarHora(hora)) {
                Toast.makeText(getContext(), "Hora inválida! Use o formato: HH:mm", Toast.LENGTH_SHORT).show();
                return;
            }

            LembreteModel novoLembrete = new LembreteModel(nome, dia, hora, lugar, false);
            viewModel.adicionarLembrete(novoLembrete);

            // Agendar notificação
            agendarNotificacao(nome, dia, hora);

            edtnome.setText("");
            edtdia.setText("");
            edthora.setText("");
            edtlugar.setText("");

            Toast.makeText(getContext(), "Lembrete adicionado com sucesso!", Toast.LENGTH_SHORT).show();
        });
        // Botão Ver Lembretes
        btncontinuar.setOnClickListener(v -> {
            // Substitui o fragment atual pelo ListaLembretesActivity (Fragment)
            getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frameLayout, new ListaLembretesActivity())
                    .addToBackStack(null) // Permite voltar para o fragment atual
                    .commit();
        });

        // Botão Voltar
        voltar.setOnClickListener(v -> {
            // Volta para o fragment anterior na pilha de navegação
            getParentFragmentManager().popBackStack();
        });


        return view;
    }

    private void agendarNotificacao(String nome, String dia, String hora) {
        long triggerTime = calcularTimestamp(dia, hora);

        if (triggerTime <= System.currentTimeMillis()) {
            Toast.makeText(requireContext(), "A hora do lembrete já passou!", Toast.LENGTH_SHORT).show();
            return;
        }

        long delay = triggerTime - System.currentTimeMillis();

        Data inputData = new Data.Builder()
                .putString(NotificationWorker.LEMBRETE_NAME, nome)
                .build();

        OneTimeWorkRequest notificationWork = new OneTimeWorkRequest.Builder(NotificationWorker.class)
                .setInitialDelay(delay, TimeUnit.MILLISECONDS)
                .setInputData(inputData)
                .build();

        WorkManager.getInstance(requireContext()).enqueue(notificationWork);
        Toast.makeText(requireContext(), "Notificação agendada com sucesso!", Toast.LENGTH_SHORT).show();
    }


    private long calcularTimestamp(String dia, String hora) {
        try {
            // Combina a data e hora no formato dd/MM/yyyy HH:mm
            String dateTimeString = dia + " " + hora; // Exemplo: "05/12/2024 14:00"
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
            Date date = format.parse(dateTimeString);
            return date != null ? date.getTime() : 0;
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }


}
