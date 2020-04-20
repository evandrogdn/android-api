package com.example.android_api;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android_api.helper.FormHelper;
import com.example.android_api.model.Censos;
import com.example.android_api.model.Dados;
import com.google.gson.Gson;

public class FormActivity extends AppCompatActivity {
    private Button button;
    private Censos censoToUpdate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Censos censos = (Censos) getIntent().getSerializableExtra("censo_selecionado");
            if (censos != null) {
                censoToUpdate = censos;
                Dados dados = (new Gson()).fromJson(censos.getDados(), Dados.class);
                (new FormHelper(FormActivity.this)).setDadosIntoForm(dados);
            }
        }

        button = findViewById(R.id.botao);
        addListeners();
    }

    private void addListeners() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FormActivity.this, "Not yet", Toast.LENGTH_LONG).show();
            }
        });
    }
}
