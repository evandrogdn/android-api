package com.example.android_api.model;

import androidx.annotation.NonNull;

public class Coletor {
    public String coletor, dados;

    @NonNull
    @Override
    public String toString() {
        return coletor + "(" + dados.toString() + ")";
    }

}
