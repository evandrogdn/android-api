package com.example.android_api.helper;

import android.widget.EditText;

import com.example.android_api.FormActivity;
import com.example.android_api.R;
import com.example.android_api.model.Dados;

public class FormHelper {
    private EditText nome;
    private EditText telefone;
    private EditText endereco;

    private Dados dados;

    public FormHelper(FormActivity form) {
        this.nome = form.findViewById(R.id.nome);
        this.telefone = form.findViewById(R.id.telefone);
        this.endereco = form.findViewById(R.id.endereco);

        this.dados = new Dados();
    }

    private Dados getDadosFromForm() {
        this.dados.setNome(this.nome.getEditableText().toString());
        this.dados.setTelefone(this.telefone.getEditableText().toString());
        this.dados.setEndereco(this.endereco.getEditableText().toString());

        return this.dados;
    }

    private void setDadosIntoForm(Dados dados) {
        this.dados = dados;

        this.nome.setText(this.dados.getNome());
        this.telefone.setText(this.dados.getTelefone());
        this.endereco.setText(this.dados.getEndereco());
    }
}
