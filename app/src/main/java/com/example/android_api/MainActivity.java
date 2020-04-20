package com.example.android_api;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android_api.adapter.CensoAdapter;
import com.example.android_api.model.Censos;
import com.example.android_api.model.CensosResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ListView listaCensos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaCensos = findViewById(R.id.lista_alunos);
        onListCensos();
    }

    private void onListCensos() {
        CensoService censoService = CensoService.retrofit.create(CensoService.class);
        final Call<CensosResponse> call = censoService.repoColetor("1001");
        // send busca
        call.enqueue(new Callback<CensosResponse>() {
            @Override
            public void onResponse(Call<CensosResponse> call, Response<CensosResponse> response) {
                List censos = (List<Censos>) response.body().getEmbedded().getCensos();
                CensoAdapter adapter = new CensoAdapter(MainActivity.this, censos);
                listaCensos.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<CensosResponse> call, Throwable throwable) {
                Toast.makeText(
                    MainActivity.this,
                    "Erro: " + throwable.getMessage(),
                    Toast.LENGTH_LONG
                ).show();
            }
        });
    }
}
