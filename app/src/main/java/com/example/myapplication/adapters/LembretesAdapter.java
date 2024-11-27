package com.example.myapplication.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.models.LembreteModel;
import com.example.myapplication.viewholders.LembreteViewHolder;

import java.util.List;

public class LembretesAdapter extends RecyclerView.Adapter<LembreteViewHolder> {

    List<LembreteModel> lembretes;

    public LembretesAdapter(List<LembreteModel> lembretes) {
        this.lembretes = lembretes;
    }

    @NonNull
    @Override
    public LembreteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleitem,parent,false);
        return new LembreteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LembreteViewHolder holder, int position) {
        LembreteModel lembrete = lembretes.get(position);

        // Preencher os dados
        holder.nome.setText(lembrete.getNome());
        holder.dia.setText(lembrete.getDia());
        holder.hora.setText(lembrete.getHora());
        holder.conflembrete.setChecked(lembrete.isFeito());

        // Atualizar a opacidade do CardView com base no estado inicial
        updateCardOpacity(holder.itemView, lembrete.isFeito());

        // Listener para mudanças no estado do CheckBox
        holder.conflembrete.setOnCheckedChangeListener((buttonView, isChecked) -> {
            lembrete.setFeito(isChecked);
            updateCardOpacity(holder.itemView, isChecked); // Atualizar a opacidade
        });
    }


    @Override
    public int getItemCount() {
        return lembretes.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void atualizarLista(List<LembreteModel> novosLembretes) {
        this.lembretes.clear();
        this.lembretes.addAll(novosLembretes);
        notifyDataSetChanged();

    }

    private void updateCardOpacity(View itemView, boolean isChecked) {
        if (isChecked) {
            itemView.setAlpha(0.5f); // Tornar o CardView opaco
        } else {
            itemView.setAlpha(1.0f); // Restaurar a opacidade total
        }
    }
}
