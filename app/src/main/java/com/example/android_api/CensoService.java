package com.example.android_api;

import com.example.android_api.model.CensosResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CensoService {
    @GET("api/censo/{id}")
    Call<List<Coletor>> repoCenso(
            @Path("id") String repo
    );

    @GET("censo/search/findByColetor")
    Call<CensosResponse> repoColetor(
            @Query("coletor") String coletor
    );

    public static final Retrofit retrofit =
            new Retrofit.Builder()
                .baseUrl("https://whispering-headland-07022.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
}