package com.example.mvvmdaggerhilt.network;

import com.example.mvvmdaggerhilt.model.RecyclerList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitServisInterface {

    @GET("repositories")
    Call<RecyclerList> getDataFromGitHupApi(@Query("q") String query);
}
