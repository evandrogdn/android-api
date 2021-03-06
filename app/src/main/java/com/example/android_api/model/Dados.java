package com.example.android_api.model;

public class Dados {
    private String nome_completo;
    private String endereco;
    private String telefone;

    public String getNome() {
        return nome_completo;
    }

    public void setNome(String nome) {
        this.nome_completo = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "{\"nome_completo\": \"" + nome_completo + "\", \"endereco\": \"" + endereco + "\", \"telefone\": \"" + telefone + "\"}";
    }
}
