package com.example.myapplication.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.App;
import com.example.myapplication.R;
import com.example.myapplication.models.LembreteModel;
import com.example.myapplication.utils.AppSharedPreferences;
import com.example.myapplication.viewholders.LembreteViewHolder;

import java.util.List;

public class LembretesAdapter extends RecyclerView.Adapter<LembreteViewHolder> {

    List<LembreteModel> lembretes;
    private OnLembreteRemovidoListener listener;

    public interface OnLembreteRemovidoListener {
        void onLembreteRemovido(LembreteModel lembrete);
    }



    public LembretesAdapter(List<LembreteModel> lembretes, OnLembreteRemovidoListener listener) {
        this.lembretes = lembretes;
        this.listener = listener;
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

        holder.nome.setText(lembrete.getNome());
        holder.dia.setText(lembrete.getDia());
        holder.hora.setText(lembrete.getHora());
        holder.local.setText(lembrete.getLocal());
        holder.conflembrete.setChecked(lembrete.isFeito());

        updateCardOpacity(holder.itemView, lembrete.isFeito());

        holder.conflembrete.setOnCheckedChangeListener((buttonView, isChecked) -> {
            lembrete.setFeito(isChecked);
            updateCardOpacity(holder.itemView, isChecked);

            if (isChecked) {
                listener.onLembreteRemovido(lembrete);
                notifyItemChanged(position); // Notifica a atualização apenas do item
            }
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
