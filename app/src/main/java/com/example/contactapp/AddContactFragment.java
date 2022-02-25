package com.example.contactapp;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.Key;

public class AddContactFragment extends Fragment {
    EditText nameTxt, lasteTxt, phoneTxt;

    RadioGroup radioGroup;
    RadioButton notChoose, male, female;
    ImageView picture;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_contact, container, false);
        String value = getArguments().getString("1");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("ContactList");
        picture = v.findViewById(R.id.profilePic);
        male = v.findViewById(R.id.male);
        female = v.findViewById(R.id.female);
        notChoose = v.findViewById(R.id.notChoosed);
        nameTxt = v.findViewById(R.id.txtName);
        lasteTxt = v.findViewById(R.id.txtLast);
        phoneTxt = v.findViewById(R.id.txtPhone);
        Button send = v.findViewById(R.id.btnAdd);
        phoneTxt.setText(value);

        send.setOnClickListener(new View.OnClickListener() {

            @SuppressLint({"SetTextI18n", "RestrictedApi"})
            @Override
            public void onClick(View view) {
                male.setChecked(false);
                female.setChecked(false);
                radioGroup = v.findViewById(R.id.gendar);
                int radioId = radioGroup.getCheckedRadioButtonId();
                System.out.println(radioId);
                String name = nameTxt.getText().toString();
                String lastName = lasteTxt.getText().toString();
                String phoneNumber = phoneTxt.getText().toString();
                String pic = picture.toString();
                String gendar;
                if (radioId != male.getId()) {
                    if (radioId != female.getId()) {
                        gendar = notChoose.getText().toString();
                    } else {
                        gendar = female.getText().toString();
                    }
                } else {
                    gendar = male.getText().toString();
                }
                Contact contact = new Contact(gendar, name, lastName, phoneNumber);
                myRef.child(name).setValue(contact);
                male.setChecked(false);
                female.setChecked(false);
                notChoose.setChecked(true);
                nameTxt.setText("");
                lasteTxt.setText("");
                phoneTxt.setText("");

                getFragmentManager().popBackStack();
            }

        });


        return v;

    }


}