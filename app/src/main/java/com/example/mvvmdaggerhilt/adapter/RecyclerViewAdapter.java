package com.example.mvvmdaggerhilt.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mvvmdaggerhilt.databinding.RecyclerRowBinding;
import com.example.mvvmdaggerhilt.model.RecyclerData;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private List<RecyclerData> listItems;

    public void setListItems(List<RecyclerData> listItems) {
        this.listItems = listItems;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RecyclerRowBinding recyclerRowBinding = RecyclerRowBinding.inflate(inflater, parent, false);

        return new MyViewHolder(recyclerRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.binding.tvName.setText(listItems.get(position).name);
            holder.binding.tvDesc.setText(listItems.get(position).description);
        Glide.with(holder.binding.imageView)
                .load(listItems.get(position).getOwner().getAvatar_url())
                .into(holder.binding.imageView);


    }

    @Override
    public int getItemCount() {
        if (listItems == null)
            return 0;
        else
            return listItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder  {

        private RecyclerRowBinding binding;
        public MyViewHolder(@NonNull RecyclerRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
