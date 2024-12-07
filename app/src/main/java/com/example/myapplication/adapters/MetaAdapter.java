package com.example.myapplication.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.models.Meta;
import com.example.myapplication.viewholders.MetaViewHolder;

import java.util.List;

public class MetaAdapter extends RecyclerView.Adapter<MetaViewHolder> {
    private List<Meta> metas;
    private final OnMetaClickListener onMetaClickListener;


    public MetaAdapter(List<Meta> metas, OnMetaClickListener listener) {
        this.metas = metas;
        this.onMetaClickListener = listener;
    }

    @NonNull
    @Override
    public MetaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate o layout para o item do RecyclerView
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleitem2, parent, false);
        return new MetaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MetaViewHolder holder, int position) {
        Meta meta = metas.get(position);
        holder.checkBoxMeta.setText(meta.getTexto());
        holder.checkBoxMeta.setChecked(meta.isConcluida());

        // Quando o CheckBox for marcado/desmarcado, atualiza o estado
        holder.checkBoxMeta.setOnCheckedChangeListener((buttonView, isChecked) -> {
            meta.setConcluida(isChecked);
            onMetaClickListener.onMetaClick(meta, isChecked); // Atualiza o estado no ViewModel
        });
    }


    @Override
    public int getItemCount() {
        return metas.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateMetas(List<Meta> novasMetas) {
        this.metas = novasMetas;
        notifyDataSetChanged();
    }



    public interface OnMetaClickListener {
        void onMetaClick(Meta meta, boolean isConcluded);
    }
}