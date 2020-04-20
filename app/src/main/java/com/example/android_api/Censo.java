package com.example.android_api;

import com.google.gson.JsonObject;

public class Censo {
    private Integer coletor;
    private String dados;
    private String _links;

    public int getColetor() {
        return coletor;
    }

    public void setColetor(int coletor) {
        this.coletor = coletor;
    }

    public String getDados() {
        return dados;
    }

    public void setDados(String dados) {
        this.dados = dados;
    }

    public String get_links() {
        return _links;
    }

    public void set_links(String _links) {
        this._links = _links;
    }

    @Override
    public String toString() {
        return "Censo{" +
                "coletor=" + coletor +
                ", dados='" + dados + '\'' +
                ", _links=" + _links.toString() +
                '}';
    }
}
