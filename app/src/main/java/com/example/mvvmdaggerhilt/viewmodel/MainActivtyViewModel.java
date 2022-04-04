package com.example.mvvmdaggerhilt.viewmodel;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmdaggerhilt.di.AppModule;
import com.example.mvvmdaggerhilt.model.RecyclerData;
import com.example.mvvmdaggerhilt.network.RetroRepository;
import com.example.mvvmdaggerhilt.network.RetrofitServisInterface;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class MainActivtyViewModel extends ViewModel {

    MutableLiveData<List<RecyclerData>> liveData;

    @Inject
    RetrofitServisInterface retrofitServisInterface;

    @Inject
    public MainActivtyViewModel() {
        liveData = new MutableLiveData();
        retrofitServisInterface = new AppModule().getRetrofitServisInterface(new AppModule().getRetrofitInstance());
    }

    public MutableLiveData<List<RecyclerData>> getLiveData() {
        return liveData;
    }

    public void makeApiCall() {
        RetroRepository retroRepository = new RetroRepository(retrofitServisInterface);
        retroRepository.makeAPICall("cat", liveData);
    }

}
