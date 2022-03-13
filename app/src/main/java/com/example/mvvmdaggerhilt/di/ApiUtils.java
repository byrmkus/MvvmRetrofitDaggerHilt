package com.example.mvvmdaggerhilt.di;

import com.example.mvvmdaggerhilt.network.RetrofitServisInterface;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;


@InstallIn(SingletonComponent.class)
@Module
public class ApiUtils {

    public static final String BASE_URL = "https://api.github.com/search/"; //repositories?q=newyork";


    public ApiUtils() throws IOException {

    }
    @Singleton
    @Provides
    public static RetrofitServisInterface getRetrofitServisInterface() {
        return AppModule.getRetrofitInstance().create(RetrofitServisInterface.class);
    }
}
