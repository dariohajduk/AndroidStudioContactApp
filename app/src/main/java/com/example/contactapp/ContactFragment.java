package com.example.contactapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.contactapp.Contact;
import com.example.contactapp.ContactAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ContactFragment extends Fragment {
    DatabaseReference myRef;
    RecyclerView recyclerView;
    ContactAdapter contactAdapter;
    ArrayList<Contact> contacts;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_contact, container, false);
        myRef = FirebaseDatabase.getInstance().getReference("Employees");

        contacts = new ArrayList<>();
        contactAdapter = new ContactAdapter(this.getActivity(),contacts);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Contact contact = new Contact(dataSnapshot.child("name").getValue().toString(),
                            dataSnapshot.child("last").getValue().toString(),
                            dataSnapshot.child("gendar").getValue().toString(),
                            dataSnapshot.child("phone").getValue().toString()
                            );
                    contacts.add(contact);
                    System.out.println(contacts.size());
                }
                contactAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        recyclerView = v.findViewById(R.id.Recycler1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(contactAdapter);

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        contactAdapter.startListeninig();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}