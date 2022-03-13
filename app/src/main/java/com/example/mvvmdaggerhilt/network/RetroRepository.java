package com.example.mvvmdaggerhilt.network;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmdaggerhilt.model.RecyclerData;
import com.example.mvvmdaggerhilt.model.RecyclerList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetroRepository {
    private RetrofitServisInterface retrofitServisInterface;

    public RetroRepository(RetrofitServisInterface retrofitServisInterface) {
        this.retrofitServisInterface = retrofitServisInterface;
    }

    public void makeAPICall(String query, MutableLiveData<List<RecyclerData>> liveData ) {
        Call<RecyclerList> call = retrofitServisInterface.getDataFromGitHupApi(query);
        call.enqueue(new Callback<RecyclerList>() {
            @Override
            public void onResponse(Call<RecyclerList> call, Response<RecyclerList> response) {
                if (response.isSuccessful()) {
                    liveData.postValue(response.body().getItems());
                } else {
                    liveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<RecyclerList> call, Throwable t) {

            }
        });
    }
}
