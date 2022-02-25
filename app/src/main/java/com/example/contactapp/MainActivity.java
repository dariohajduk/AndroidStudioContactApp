package com.example.contactapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {
    Button btnAddContact;
    Fragment choosenFragment=null;


    int size=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCompat.requestPermissions(MainActivity.this,new String[]
                {Manifest.permission.CALL_PHONE,
                Manifest.permission.READ_SMS,
                Manifest.permission.SEND_SMS,
                Manifest.permission.CALL_PRIVILEGED}, PackageManager.PERMISSION_GRANTED);

        setContentView(R.layout.activity_main);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myContact = database.getReference("message");
        System.out.println("connected");
        BottomNavigationView bottomNav =findViewById(R.id.bottom_nav);
        bottomNav.setOnNavigationItemSelectedListener(navLis);
        getSupportFragmentManager().beginTransaction().replace(R.id.frag_cont,
                new DialFragment()).commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navLis =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    switch (item.getItemId()) {
                        case R.id.dial_pad:
                            choosenFragment = new DialFragment();
                            break;
                        case R.id.phone:
                            choosenFragment = new PhoneFragment();
                            break;
                        case R.id.favorite:
                            choosenFragment = new FavoriteFragment();
                            break;
                        case R.id.contact:
                            choosenFragment = new ContactFragment();
                            break;
                        case R.id.btnCall:
                            choosenFragment = new AddContactFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.frag_cont,
                            choosenFragment).commit();
                    return true;
                }
            };

}