package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.adapters.ArtigosAdapter;
import com.example.myapplication.models.ArtigoModel;

import java.util.ArrayList;
import java.util.List;

public class ArtigosActivity extends Activity {

    ArtigosAdapter adapter;
    List<ArtigoModel> artigoModels = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artigo);

        artigoModels.addAll(inicializarArtigos());

        adapter = new ArtigosAdapter(artigoModels, this);

        ListView lista = findViewById(R.id.listaarts);
        lista.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        ImageView img = findViewById(R.id.voltar);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent it = new Intent(ArtigosActivity.this,MainFragment.class);
               startActivity(it);
            }
        });

    }

    private List<ArtigoModel> inicializarArtigos() {
        List<ArtigoModel> artigoModels2 = new ArrayList<>();

        // Adicionando os artigos manualmente
        artigoModels2.add(new ArtigoModel(
                1,
                "Os Benefícios da Água",
                R.drawable.agua,
                "A água é essencial para a vida e é uma das substâncias mais importantes para o funcionamento adequado do corpo humano. Ela desempenha vários papéis críticos, incluindo a regulação da temperatura corporal, a eliminação de toxinas e a lubrificação das articulações. Além disso, a água é fundamental para a digestão e absorção de nutrientes, ajudando o corpo a utilizar as vitaminas e minerais dos alimentos que consumimos. A desidratação pode levar a problemas graves, como pedras nos rins, infecções urinárias, e até problemas cardiovasculares. Portanto, é essencial beber água regularmente ao longo do dia, especialmente durante atividades físicas e em climas quentes. Especialistas recomendam o consumo de pelo menos 2 litros de água por dia para manter o corpo funcionando corretamente.\"",
                "Dr. João Silva",
                "2023-08-20"
        ));

        artigoModels2.add(new ArtigoModel(
                2,
                "Importância de Exercícios Físicos",
                R.drawable.cara, // Use o recurso drawable correto
                "A prática regular de exercícios físicos traz uma série de benefícios para a saúde. Estudos mostram que a atividade física ajuda a controlar o peso corporal, melhora a saúde cardiovascular, e aumenta a força muscular e a flexibilidade. Além disso, o exercício regular está associado a uma redução do risco de doenças crônicas, como diabetes tipo 2, hipertensão e certos tipos de câncer. A atividade física também tem efeitos positivos na saúde mental, ajudando a reduzir sintomas de depressão e ansiedade. Ela promove a liberação de endorfinas, que são neurotransmissores que causam a sensação de bem-estar. Para obter os benefícios, recomenda-se pelo menos 150 minutos de atividade moderada ou 75 minutos de atividade intensa por semana, combinando exercícios aeróbicos com treinamento de força.",
                "Prof. Maria Oliveira",
                "2023-07-15"
        ));

        artigoModels2.add(new ArtigoModel(
                3,
                "Alimentação Saudável",
                R.drawable.salada_resized, // Use o recurso drawable correto
                "Uma alimentação saudável é a base para o bom funcionamento do organismo e a prevenção de diversas doenças. Uma dieta balanceada deve incluir uma variedade de alimentos, como frutas, vegetais, grãos integrais, proteínas magras e gorduras saudáveis. Esses alimentos fornecem os nutrientes necessários para a produção de energia, crescimento e reparo dos tecidos corporais, e a manutenção da saúde mental. Além disso, uma alimentação rica em fibras, vitaminas e minerais está associada a uma menor incidência de doenças crônicas, como doenças cardíacas, diabetes e obesidade. Reduzir o consumo de açúcar, sal e gorduras saturadas também é importante para manter uma dieta equilibrada. Comer conscientemente, prestando atenção às porções e evitando o consumo excessivo de alimentos processados, é fundamental para uma alimentação saudável.",
                "Nutricionista Ana Costa",
                "2023-06-10"
        ));

        artigoModels2.add(new ArtigoModel(
                4,
                "A Importância do Sono",
                R.drawable.cama, // Use o recurso drawable correto
                "O sono desempenha um papel vital na manutenção da saúde física e mental. Durante o sono, o corpo realiza uma série de funções essenciais, como a reparação de tecidos, o fortalecimento do sistema imunológico, e a consolidação da memória e aprendizado. A falta de sono de qualidade pode levar a problemas de saúde a longo prazo, como obesidade, doenças cardíacas, diabetes e depressão. Além disso, a privação de sono afeta a cognição, o humor e a produtividade, aumentando o risco de acidentes e erros. Para garantir um sono reparador, é importante manter uma rotina regular de sono, criar um ambiente propício ao descanso, e evitar o uso de dispositivos eletrônicos antes de dormir. A maioria dos adultos precisa de 7 a 9 horas de sono por noite para funcionar de forma otimizada.",
                "Dr. Carlos Almeida",
                "2023-05-01"
        ));

        return artigoModels2;
    }


}
