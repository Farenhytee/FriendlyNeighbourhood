package com.example.friendlyneighbourhood;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class adapter extends RecyclerView.Adapter<viewHolder> {
    Context context;
    List<Item> items;

    public adapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolder(LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.name.setText(items.get(position).getName());
        holder.phone.setText(items.get(position).getPhone());
        holder.servOffered.setText(items.get(position).getServicesOffered());
        holder.locality.setText(items.get(position).getLocality());
    }


    @Override
    public int getItemCount() {
        return items.size();
    }
}
