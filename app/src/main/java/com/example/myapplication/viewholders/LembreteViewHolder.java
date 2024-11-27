package com.example.myapplication.viewholders;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

public class LembreteViewHolder extends RecyclerView.ViewHolder {

    public TextView nome, dia, hora, local;
    public CheckBox conflembrete;

    public LembreteViewHolder(@NonNull View itemView) {
        super(itemView);

        nome = itemView.findViewById(R.id.tvnome);
        dia = itemView.findViewById(R.id.tvdia);
        hora = itemView.findViewById(R.id.tvhora);
        local = itemView.findViewById(R.id.tvlugar);
        conflembrete = itemView.findViewById(R.id.btnMarcado);
    }
}
