package com.example.friendlyneighbourhood;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class viewHolder extends RecyclerView.ViewHolder {
    TextView name, phone, servOffered, locality;
    public viewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.tv_name);
        phone = itemView.findViewById(R.id.tv_phone);
        servOffered = itemView.findViewById(R.id.tv_servicesOffered);
        locality = itemView.findViewById(R.id.tv_locality);

    }
}
