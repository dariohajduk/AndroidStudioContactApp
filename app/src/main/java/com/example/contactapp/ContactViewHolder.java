package com.example.contactapp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ContactViewHolder extends RecyclerView.ViewHolder {
    TextView name, last, phone,gendar;

    public ContactViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.txtFirstName);
        last = itemView.findViewById(R.id.txtLast);
        phone = itemView.findViewById(R.id.txtPhone);
        gendar = itemView.findViewById(R.id.txtGendarT);

    }
}
