package com.example.myapplication.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.models.Meta;
import com.example.myapplication.viewholders.MetaViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MetaAdapter extends RecyclerView.Adapter<MetaViewHolder> {
    private final List<Meta> metas = new ArrayList<>();
    private final OnMetaClickListener onMetaClickListener;

    public MetaAdapter(OnMetaClickListener listener) {
        this.onMetaClickListener = listener;
    }

    @NonNull
    @Override
    public MetaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleitem2, parent, false);
        return new MetaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MetaViewHolder holder, int position) {
        Meta meta = metas.get(position);
        holder.checkBoxMeta.setText(meta.getTexto());
        holder.checkBoxMeta.setChecked(meta.isConcluida());

        // Atualiza a meta quando o estado do checkbox mudar
        holder.checkBoxMeta.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (buttonView.isPressed()) { // Apenas processa cliques manuais do usuário
                onMetaClickListener.onMetaClick(meta, isChecked);
            }
        });
    }

    @Override
    public int getItemCount() {
        return metas.size();
    }

    // Atualizar lista com verificação
    @SuppressLint("NotifyDataSetChanged")
    public void setMetas(List<Meta> novasMetas) {
        metas.clear();
        metas.addAll(novasMetas);
        notifyDataSetChanged();
    }

    public interface OnMetaClickListener {
        void onMetaClick(Meta meta, boolean isConcluded);
    }
}
