package com.example.android_api.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.android_api.R;
import com.example.android_api.model.Censos;
import com.example.android_api.model.Dados;
import com.google.gson.Gson;

import java.util.List;

public class CensoAdapter extends ArrayAdapter<Censos> {
    public CensoAdapter(Context context, List<Censos> censos) {
        super(context, 0, censos);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Censos censo = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
        }

        Dados dados = (new Gson()).fromJson(censo.getDados(), Dados.class);

        ImageView imagem = (ImageView) convertView.findViewById(R.id.foto);
        TextView nome = (TextView) convertView.findViewById(R.id.nome);
        imagem.setImageResource(R.drawable.ic_no_image);
        nome.setText("   " + dados.getNome());

        return convertView;
    }
}
