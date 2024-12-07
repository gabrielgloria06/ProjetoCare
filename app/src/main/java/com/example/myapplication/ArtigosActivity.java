package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

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

        // Inicializa os artigos
        artigoModels.addAll(inicializarArtigos());

        // Configura o adapter e a ListView
        adapter = new ArtigosAdapter(artigoModels, this);
        ListView lista = findViewById(R.id.listaarts);
        lista.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        // Configura o botão para voltar à MainFragment
        ImageView img = findViewById(R.id.voltar);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ArtigosActivity.this, MainFragment.class);
                startActivity(it);
            }
        });
    }

    private List<ArtigoModel> inicializarArtigos() {
        List<ArtigoModel> artigoModels2 = new ArrayList<>();

        // Adiciona os artigos manualmente
        artigoModels2.add(new ArtigoModel(
                1,
                "Os Riscos da Obesidade",
                R.drawable.obesidade,
                "A obesidade é uma condição crônica que afeta milhões de pessoas em todo o mundo e está associada a uma série de complicações graves para a saúde. Entre elas estão doenças cardiovasculares, diabetes tipo 2, apneia do sono e certos tipos de câncer. O excesso de peso também pode impactar a saúde mental, levando a problemas como baixa autoestima e depressão. Combater a obesidade requer uma abordagem multifacetada, que inclui uma dieta equilibrada, exercícios regulares, suporte psicológico e, em alguns casos, intervenções médicas. Estratégias como evitar alimentos ultraprocessados, consumir porções adequadas e manter uma rotina de exercícios podem ajudar a reduzir o peso e melhorar a saúde geral.",
                "Dr. Rafael Lima",
                "2023-11-25"
        ));

        artigoModels2.add(new ArtigoModel(
                2,
                "Impacto da Ansiedade na Saúde",
                R.drawable.ansiedade,
                "A ansiedade, em níveis moderados, é uma reação natural ao estresse, mas quando se torna excessiva, pode afetar significativamente a saúde física e mental. Pessoas que sofrem de transtornos de ansiedade frequentemente relatam dificuldades de concentração, insônia, e aumento do risco de doenças cardíacas. Técnicas de relaxamento, como meditação, yoga e exercícios de respiração, podem ajudar a gerenciar os sintomas. A terapia cognitivo-comportamental (TCC) e, em casos específicos, medicamentos prescritos por um profissional de saúde, também podem ser eficazes no tratamento da ansiedade.",
                "Psicóloga Marina Santos",
                "2023-11-10"
        ));

        artigoModels2.add(new ArtigoModel(
                3,
                "Dietas Low Carb: O Que Você Precisa Saber",
                R.drawable.dieta_lowcarb,
                "As dietas low carb ganharam popularidade como uma forma eficaz de perder peso e melhorar a saúde metabólica. Elas envolvem a redução significativa do consumo de carboidratos, enquanto aumentam a ingestão de proteínas e gorduras saudáveis. Estudos mostram que essas dietas podem ajudar a reduzir os níveis de açúcar no sangue, melhorar a sensibilidade à insulina e promover a perda de peso. No entanto, é importante equilibrar a dieta para evitar deficiências nutricionais e priorizar alimentos como vegetais, carnes magras, ovos e gorduras boas, como abacate e azeite de oliva.",
                "Nutricionista Clara Fernandes",
                "2023-10-15"
        ));

        artigoModels2.add(new ArtigoModel(
                4,
                "Como Melhorar a Qualidade do Sono",
                R.drawable.qualidade_sono,
                "A qualidade do sono é tão importante quanto a quantidade de horas dormidas. Uma boa noite de sono melhora a memória, aumenta a produtividade e ajuda a manter o equilíbrio hormonal. Para dormir melhor, recomenda-se estabelecer horários consistentes para deitar e acordar, criar um ambiente escuro e silencioso, e evitar a ingestão de cafeína nas horas que antecedem o sono. Estudos também indicam que a prática regular de exercícios e a exposição à luz natural durante o dia podem melhorar o ciclo do sono.",
                "Dr. João Almeida",
                "2023-09-30"
        ));

        artigoModels2.add(new ArtigoModel(
                5,
                "A Importância das Vacinas na Saúde Pública",
                R.drawable.vacinas,
                "As vacinas desempenham um papel crucial na prevenção de doenças infecciosas e na proteção da saúde pública. Elas ajudam a reduzir a disseminação de doenças como sarampo, poliomielite e gripe, protegendo tanto os indivíduos vacinados quanto as comunidades ao seu redor por meio da imunidade coletiva. Com a evolução da tecnologia, novas vacinas estão sendo desenvolvidas para combater doenças emergentes e melhorar a eficácia das já existentes. Manter o calendário de vacinação em dia é essencial para proteger não apenas a si mesmo, mas também grupos vulneráveis, como idosos e pessoas com sistemas imunológicos enfraquecidos.",
                "Dr. Pedro Carvalho",
                "2023-12-05"
        ));

        artigoModels2.add(new ArtigoModel(
                6,
                "O Papel da Saúde Mental na Produtividade",
                R.drawable.saude_mental,
                "A saúde mental tem um impacto direto na produtividade e no bem-estar geral. Estresse, ansiedade e burnout são problemas comuns no ambiente de trabalho moderno e podem afetar a capacidade de concentração, tomada de decisões e relações interpessoais. Empresas que promovem um ambiente de trabalho saudável, com políticas de apoio psicológico e equilíbrio entre vida pessoal e profissional, têm equipes mais engajadas e produtivas. Cuidar da saúde mental envolve reconhecer sinais de esgotamento, praticar atividades que promovam relaxamento, e buscar apoio profissional quando necessário.",
                "Psicóloga Renata Martins",
                "2023-12-01"
        ));

        return artigoModels2;
    }
}
