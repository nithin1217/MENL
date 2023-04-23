package com.example.consumerapp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView name,phone,doctors,beds,distance;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        name=itemView.findViewById(R.id.name_item);
        phone=itemView.findViewById(R.id.textView3);
        doctors=itemView.findViewById(R.id.doctorCount);
        beds=itemView.findViewById(R.id.bedCount);
        distance=itemView.findViewById(R.id.distance);
    }

}
