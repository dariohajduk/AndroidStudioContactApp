package com.example.contactapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactViewHolder> {

    Context context;
    ArrayList<Contact> contacts;

    public ContactAdapter(Context context, ArrayList<Contact> contacts) {
        this.context = context;
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new ContactViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contact contact = contacts.get(position);
        holder.name.setText(contact.getName());
        holder.last.setText(contact.getLast());
        holder.phone.setText(contact.getPhone());
        holder.gendar.setText(contact.getPhone());


    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public void startListeninig() {
    }
}
