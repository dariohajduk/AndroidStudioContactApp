package com.example.contactapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;


public class ContactFragment extends Fragment {
    DatabaseReference myRef;
    RecyclerView recyclerView;
    ArrayList<Contact> contacts;
    SearchView searchView;
    ContactAdapter contactAdapter;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.fragment_contact, container, false);

        myRef = FirebaseDatabase.getInstance().getReference("ContactList");
        contacts = new ArrayList<>();


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Contact contact = new Contact(dataSnapshot.child("gendar").getValue(String.class),
                            dataSnapshot.child("name").getValue(String.class),
                            dataSnapshot.child("last").getValue(String.class),
                            dataSnapshot.child("phone").getValue(String.class));
                    contacts.add(contact);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        contactAdapter = new ContactAdapter(this.getActivity(), contacts);
        recyclerView = v.findViewById(R.id.Recycler1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(contactAdapter);

        searchView = v.findViewById(R.id.searchBar);
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (searchView != null) {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    search(query);
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    search(s);
                    return true;
                }
            });
        }
    }

    private void search(String str) {
        ArrayList<Contact> myContacts = new ArrayList<>();
        for (Contact object : contacts) {
            if (object.getName().toLowerCase().contains(str.toLowerCase())) {
                myContacts.add(object);
            }
        }
        ContactAdapter contactAdapter1 = new ContactAdapter(this.getActivity(), myContacts);
        recyclerView.setAdapter(contactAdapter1);
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}