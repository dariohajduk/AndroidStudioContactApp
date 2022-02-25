package com.example.contactapp;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddContactFragment extends Fragment {
    EditText nameTxt, lasteTxt, phoneTxt;
    RadioGroup radioGroup;
    RadioButton notChoose, male, female;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_contact, container, false);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Contact List");
        male = v.findViewById(R.id.male);
        female = v.findViewById(R.id.female);
        notChoose = v.findViewById(R.id.notChoosed);
        nameTxt = v.findViewById(R.id.txtName);
        lasteTxt = v.findViewById(R.id.txtLast);
        phoneTxt = v.findViewById(R.id.txtPhone);
        Button send = v.findViewById(R.id.btnAdd);
        send.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
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
                Contact contact = new Contact(name, lastName, phoneNumber, gendar);

                myRef.push().setValue(contact);
                male.setChecked(false);
                female.setChecked(false);
                notChoose.setChecked(true);
                nameTxt.setText("");
                lasteTxt.setText("");
                phoneTxt.setText("");

                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.setVisible(true);
            }

        });
        return v;
    }


}