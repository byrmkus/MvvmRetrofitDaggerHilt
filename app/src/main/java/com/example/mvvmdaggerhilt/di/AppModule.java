package com.example.mvvmdaggerhilt.di;

import com.example.mvvmdaggerhilt.network.RetrofitServisInterface;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    String base_url = "https://api.github.com/search/"; //repositories?q=newyork";

    @Singleton
    @Provides
    public  RetrofitServisInterface getRetrofitServisInterface(Retrofit retrofit) {
        return retrofit.create(RetrofitServisInterface.class);
    }

    @Singleton
    @Provides
    public Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }
}
