package com.example.myapplication;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SonoActivity extends AppCompatActivity {

    EditText edtHora;
    TextView txtTipoSono, txtSono, txtHora;

     ImageView voltar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sonoactivity);

        edtHora = findViewById(R.id.edtHora);
        txtTipoSono = findViewById(R.id.txtTipoSono);
        txtSono = findViewById(R.id.txtSono);
        txtHora = findViewById(R.id.txtHora);

        // Configura o listener para o botão Enter do teclado
        edtHora.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE ||
                    (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN)) {
                String horaStr = edtHora.getText().toString();

                if (!TextUtils.isEmpty(horaStr)) {
                    txtHora.setText(horaStr);
                    txtHora.setVisibility(View.VISIBLE);
                    edtHora.setVisibility(View.INVISIBLE);

                    int hora = Integer.parseInt(horaStr);
                    avaliarSono(hora);
                } else {
                    txtTipoSono.setText("Digite um valor válido!");
                    txtSono.setText("");
                }
                return true; // Indica que a ação foi tratada
            }
            return false; // Indica que a ação não foi tratada
        });

        voltar = findViewById(R.id.voltar);

        // Botão Voltar
        voltar.setOnClickListener(v -> {
            // Volta para o fragment anterior na pilha de navegação
            finish();
        });

    }

    private void avaliarSono(int hora) {
        if (hora < 6) {
            txtTipoSono.setText("Sono Insuficiente");
            txtSono.setText("Você dormiu menos de 6 horas, o que pode afetar sua concentração, humor e energia. Tente ajustar sua rotina para incluir mais tempo de descanso!");
        } else if (hora >= 6 && hora < 7) {
            txtTipoSono.setText("Sono Adequado");
            txtSono.setText("Seu sono foi adequado, mas pode não ser suficiente para o descanso ideal. Se possível, tente dormir um pouco mais para garantir máxima recuperação e bem-estar.");
        } else if (hora >= 7 && hora <= 9) {
            txtTipoSono.setText("Sono Ideal");
            txtSono.setText("Parabéns! Você teve um sono ideal, que é perfeito para sua saúde física e mental. Continue priorizando seu descanso!");
        } else if (hora > 9) {
            txtTipoSono.setText("Sono Excessivo");
            txtSono.setText("Você dormiu mais de 9 horas. Isso pode ser sinal de cansaço acumulado ou outra condição. Observe como você se sente ao longo do dia e tente equilibrar seu tempo de sono.");
        } else {
            txtTipoSono.setText("Valor inválido");
            txtSono.setText("Por favor, insira um número válido de horas.");
        }
    }
}
