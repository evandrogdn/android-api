package com.example.android_api;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android_api.adapter.CensoAdapter;
import com.example.android_api.model.Censos;
import com.example.android_api.model.CensosResponse;
import com.example.android_api.service.CensoService;

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

    @Override
    protected void onResume() {
        super.onResume();
        onListCensos();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_novo:
                Intent intent = new Intent(
                        MainActivity.this,
                        FormActivity.class
                );
                startActivity(intent);
                break;
            default:
                Toast.makeText(
                        MainActivity.this,
                        "NOT IMPLEMENTED YET",
                        Toast.LENGTH_LONG
                ).show();
        }

        return super.onOptionsItemSelected(item);
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
