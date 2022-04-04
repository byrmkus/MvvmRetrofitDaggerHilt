package com.example.mvvmdaggerhilt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mvvmdaggerhilt.adapter.RecyclerViewAdapter;
import com.example.mvvmdaggerhilt.databinding.ActivityMainBinding;
import com.example.mvvmdaggerhilt.model.RecyclerData;
import com.example.mvvmdaggerhilt.viewmodel.MainActivtyViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private RecyclerViewAdapter recyclerViewAdapter;
    private MainActivtyViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        initRecyclerView();
        initViewModel();
    }

    private void initRecyclerView() {
        recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(recyclerViewAdapter);
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(MainActivtyViewModel.class);
        viewModel.getLiveData().observe(this, new Observer<List<RecyclerData>>() {
            @Override
            public void onChanged(List<RecyclerData> recyclerData) {
                if (recyclerData != null) {
                    recyclerViewAdapter.setListItems(recyclerData);
                    recyclerViewAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this, "Error in getting data", Toast.LENGTH_SHORT).show();
                }

            }

        });
        viewModel.makeApiCall();
    }
}