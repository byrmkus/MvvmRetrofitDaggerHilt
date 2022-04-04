package com.example.mvvmdaggerhilt.adapter;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.mvvmdaggerhilt.R;
import com.example.mvvmdaggerhilt.databinding.RecyclerRowBinding;
import com.example.mvvmdaggerhilt.model.RecyclerData;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private List<RecyclerData> listItems;
    Context context;

    public  RecyclerViewAdapter (Context context) {
        this.context = context;
    }

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

        holder.binding.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(holder.itemView.getContext(), "Notification oluşacak", Toast.LENGTH_LONG).show();
                bildirimGoster(context);
            }
        });
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

    private void bildirimGoster(Context mContext) {

        NotificationCompat.Builder builder;

        NotificationManager bildirimYoneticisi = (NotificationManager)context.getSystemService(context.NOTIFICATION_SERVICE);




        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { // Oreo sürümü için bu kod çalışacak.

            String kanalId = "kanalId";
            String kanalAd = "kanalAd";
            String kanalTanım = "kanalTanım";
            int kanalOnceligi = NotificationManager.IMPORTANCE_HIGH;

            NotificationChannel kanal = bildirimYoneticisi.getNotificationChannel(kanalId);

            if (kanal == null) {
                kanal = new NotificationChannel(kanalId, kanalAd, kanalOnceligi);
                kanal.setDescription(kanalTanım);
                bildirimYoneticisi.createNotificationChannel(kanal);
            }

            builder = new NotificationCompat.Builder(context, kanalId);

            builder.setContentTitle("Başlık")  // gerekli
                    .setContentText("İçerik")  // gerekli
                    .setSmallIcon(R.drawable.ic_launcher_background) // gerekli
                    .setAutoCancel(true);  // Bildirim tıklandıktan sonra kaybolur."
        } else { // OREO Sürümü haricinde bu kod çalışacak.

            builder = new NotificationCompat.Builder(context);

            builder.setContentTitle("Başlık")  // gerekli
                    .setContentText("İçerik")  // gerekli
                    .setSmallIcon(R.drawable.ic_launcher_background) // gerekli
                    .setAutoCancel(true)  // Bildirim tıklandıktan sonra kaybolur."
                    .setPriority(Notification.PRIORITY_HIGH);
        }

        bildirimYoneticisi.notify(1,builder.build());

    }
}

