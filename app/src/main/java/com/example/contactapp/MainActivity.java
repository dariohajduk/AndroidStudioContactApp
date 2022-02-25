package com.example.contactapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myContact = database.getReference("message");
        System.out.println("connected");
        BottomNavigationView bottomNav =findViewById(R.id.bottom_nav);
        bottomNav.setOnNavigationItemSelectedListener(navLis);

        getSupportFragmentManager().beginTransaction().replace(R.id.frag_cont,
                new DialFragment()).commit();
        Button btnAddContact = findViewById(R.id.BtnAddContact);
        btnAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnAddContact.setVisibility(View.INVISIBLE);
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.frag_cont,new AddContactFragment(),null).
                        setReorderingAllowed(true).
                        addToBackStack(null).
                        commit();
            }
        });

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navLis =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment choosenFragment=null;
                    Button btnAddContact = findViewById(R.id.BtnAddContact);
                    btnAddContact.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            btnAddContact.setVisibility(View.INVISIBLE);
                            FragmentManager fragmentManager = getSupportFragmentManager();
                            fragmentManager.beginTransaction().replace(R.id.frag_cont,new AddContactFragment(),null).
                                    setReorderingAllowed(true).
                                    addToBackStack(null).
                                    commit();
                    }});
                    switch (item.getItemId()) {
                        case R.id.dial_pad:
                            choosenFragment = new DialFragment();
                            btnAddContact.setVisibility(View.VISIBLE);
                            break;
                        case R.id.phone:
                            choosenFragment = new PhoneFragment();
                            btnAddContact.setVisibility(View.INVISIBLE);
                            break;
                        case R.id.favorite:
                            choosenFragment = new FavoriteFragment();
                            btnAddContact.setVisibility(View.INVISIBLE);
                            break;
                        case R.id.contact:
                            choosenFragment = new ContactFragment();
                            btnAddContact.setVisibility(View.INVISIBLE);
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.frag_cont,
                            choosenFragment).commit();
                    return true;
                }
            };
}