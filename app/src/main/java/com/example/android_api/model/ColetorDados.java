package com.example.android_api.model;

import androidx.annotation.NonNull;

public class ColetorDados {
    private String nome, endereco;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @NonNull
    @Override
    public String toString() {
        return "dados: {" +
                "nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                "}";
    }
}
