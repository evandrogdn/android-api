package com.example.android_api;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupMenu;
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
    private Censos censoSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaCensos = findViewById(R.id.lista_alunos);
        onListCensos();
        onLoadAddListeners();
    }

    public void getCensoSelecionado(int position) {
        this.censoSelecionado = (Censos) listaCensos.getAdapter().getItem(position);
    }

    private void censoEditar() {
        Intent intent = new Intent(MainActivity.this, FormActivity.class);
        intent.putExtra("censo_selecionado", this.censoSelecionado);
        startActivity(intent);
        onListCensos();
    }

    private void censoRemover() {
        Toast.makeText(
                MainActivity.this,
                "Rota não implementada na nossa API",
                Toast.LENGTH_LONG
        ).show();
    }

    public void popUpMenuAction(MenuItem item) {
        switch (item.getTitle().toString()) {
            case "Editar":
                censoEditar();
                break;
            case "Remover":
                censoRemover();
                break;
            default:
                Toast.makeText(
                    MainActivity.this,
                    "NOT IMPLEMENTED YET",
                    Toast.LENGTH_LONG
                ).show();
        }
    }

    private void onLoadAddListeners() {
        listaCensos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getCensoSelecionado(position);

                PopupMenu menu = new PopupMenu(MainActivity.this, view);
                menu.getMenu().add("Editar");
                menu.getMenu().add("Remover");
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        popUpMenuAction(item);
                        onListCensos();
                        return true;
                    }
                });
                menu.show();
            }
        });
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
        final Call<CensosResponse> call = censoService.repoColetor("1006608");
        // send busca
        call.enqueue(new Callback<CensosResponse>() {
            @Override
            public void onResponse(Call<CensosResponse> call, Response<CensosResponse> response) {
                List<Censos> censos = (List<Censos>) response.body().getEmbedded().getCensos();
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
