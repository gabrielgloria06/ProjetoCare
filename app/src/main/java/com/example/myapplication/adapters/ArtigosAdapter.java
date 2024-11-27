package com.example.myapplication.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.myapplication.DescArtigosActivity;
import com.example.myapplication.ItemActvitty;
import com.example.myapplication.R;
import com.example.myapplication.models.ArtigoModel;
import com.example.myapplication.utils.AppSharedPreferences;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ArtigosAdapter extends BaseAdapter {

    List<ArtigoModel> artigos;
    Context context;

    public ArtigosAdapter(List<ArtigoModel> artigos, Context context) {
        this.artigos = artigos;
        this.context = context;
    }

    @Override
    public int getCount() {
        return artigos.size();
    }

    @Override
    public Object getItem(int position) {
        return artigos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return artigos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        @SuppressLint("ViewHolder") View v = LayoutInflater.from(context).inflate(R.layout.item_artigo_activity, parent,false);

        ArtigoModel artigo = artigos.get(position);

        TextView titulo = v.findViewById(R.id.titulo);
        ImageView imgart = v.findViewById(R.id.imgart);
        Button btn = v.findViewById(R.id.saibabtn);

        titulo.setText(artigo.getTitulo());
        imgart.setImageResource(artigo.getImagem());

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = context.getSharedPreferences("dadosartigo", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("titulo", artigo.getTitulo());
                editor.putString("conteudo", artigo.getConteudo());
                editor.apply();

                Intent intent = new Intent(context, DescArtigosActivity.class);
                if (context instanceof Activity) {
                    ((Activity) context).startActivity(intent);
                }
            }
        });



        return v;
     }
}
