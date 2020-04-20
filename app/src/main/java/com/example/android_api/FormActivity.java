package com.example.android_api;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android_api.helper.FormHelper;
import com.example.android_api.model.Censo;
import com.example.android_api.model.Censos;
import com.example.android_api.model.CensosResponse;
import com.example.android_api.model.Dados;
import com.example.android_api.service.CensoService;
import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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

    private void sendPost(Censo censo) {
        CensoService service = CensoService.retrofit.create(CensoService.class);
        final Call<CensosResponse> call = service.repoColetorInsert(censo);

        call.enqueue(new Callback<CensosResponse>() {
            @Override
            public void onResponse(Call<CensosResponse> call, Response<CensosResponse> response) {
                Toast.makeText(
                    FormActivity.this,
                    "Inserido com sucesso!",
                    Toast.LENGTH_LONG
                ).show();
            }

            @Override
            public void onFailure(Call<CensosResponse> call, Throwable throwable) {
                Toast.makeText(
                        FormActivity.this,
                        "Erro: " + throwable.getMessage(),
                        Toast.LENGTH_LONG
                ).show();
            }
        });
    }

    private void censoAdicionar() {
        Dados dadosForm = (new FormHelper(FormActivity.this)).getDadosFromForm();

        Censo censo = new Censo();
        censo.setColetor(1006608);
        censo.setDados(dadosForm.toString());

        sendPost(censo);
    }

    private void sendPatch(Censo censo, Integer id) {
        CensoService service = CensoService.retrofit.create(CensoService.class);
        final Call<CensosResponse> call = service.repoColetorPatch(censo, id);

        call.enqueue(new Callback<CensosResponse>() {
            @Override
            public void onResponse(Call<CensosResponse> call, Response<CensosResponse> response) {
                Toast.makeText(
                        FormActivity.this,
                        "Alterado com sucesso!",
                        Toast.LENGTH_LONG
                ).show();
            }

            @Override
            public void onFailure(Call<CensosResponse> call, Throwable throwable) {
                Toast.makeText(
                        FormActivity.this,
                        "Erro: " + throwable.getMessage(),
                        Toast.LENGTH_LONG
                ).show();
            }
        });
    }

    private void censoEditar() {
        String[] link = censoToUpdate.getLinks().getCenso().getHref().split("/");
        Integer id = Integer.parseInt(link[link.length - 1]);

        Dados dadosForm = (new FormHelper(FormActivity.this)).getDadosFromForm();

        Censo censo = new Censo();
        censo.setColetor(1006608);
        censo.setDados(dadosForm.toString());

        sendPatch(censo, id);
    }

    private void addListeners() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (censoToUpdate == null) {
                    censoAdicionar();
                } else {
                    censoEditar();
                }
                finish();
            }
        });
    }
}
