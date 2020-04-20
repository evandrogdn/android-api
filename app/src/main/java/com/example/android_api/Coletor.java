package com.example.android_api;

import androidx.annotation.NonNull;

import com.google.gson.JsonObject;

public class Coletor {
//    public String coletor, dados;
//
//    @NonNull
//    @Override
//    public String toString() {
//        return coletor + "(" + dados.toString() + ")" + _links;
//    }

    public JsonObject _embedded;

    @NonNull
    @Override
    public String toString() {
        return _embedded.toString();
    }
}
