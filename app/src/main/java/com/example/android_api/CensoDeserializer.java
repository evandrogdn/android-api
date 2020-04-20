package com.example.android_api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class CensoDeserializer implements JsonDeserializer<Censo> {
    private final Gson gson = new GsonBuilder().create();

    @Override
    public Censo deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        return gson.fromJson(json, type);
    }
}