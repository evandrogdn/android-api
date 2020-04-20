package com.example.android_api.service;

import com.example.android_api.model.Censo;
import com.example.android_api.model.CensosResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CensoService {
    @POST("api/censo")
    Call<CensosResponse> repoColetorInsert(
            @Body Censo censo
    );

    @PATCH("api/censo/{id}")
    Call<CensosResponse> repoColetorPatch(
            @Body Censo censo,
            @Path("id") Integer id
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