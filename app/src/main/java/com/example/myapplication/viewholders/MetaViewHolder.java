package com.example.myapplication.viewholders;

import android.view.View;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

public class MetaViewHolder extends RecyclerView.ViewHolder {

    public CheckBox checkBoxMeta;

    public MetaViewHolder(@NonNull  View itemView) {
        super(itemView);
        checkBoxMeta = itemView.findViewById(R.id.okMeta);
    }


}
