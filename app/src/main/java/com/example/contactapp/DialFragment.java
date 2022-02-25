package com.example.contactapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;

import java.util.ArrayList;

public class DialFragment extends Fragment implements View.OnClickListener {
    EditText edtPhoneNo;
    TextView lblinfo;
    String phoneNo;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Contact List");
    MainActivity main =new MainActivity();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dial_fragment, container, false);


        Button btnAddContact=v.findViewById(R.id.btnAddContact);
        Button btnOne = v.findViewById(R.id.btnOne);
        Button btnTwo = v.findViewById(R.id.btnTwo);
        Button btnThree = v.findViewById(R.id.btnThree);
        Button btnFour = v.findViewById(R.id.btnFour);
        Button btnFive = v.findViewById(R.id.btnFive);
        Button btnSix = v.findViewById(R.id.btnSix);
        Button btnSeven = v.findViewById(R.id.btnSeven);
        Button btnEight = v.findViewById(R.id.btnEight);
        Button btnNine = v.findViewById(R.id.btnNine);
        Button btnZero = v.findViewById(R.id.btnZero);
        Button btnCall = v.findViewById(R.id.btnCall);
        Button btnHash = v.findViewById(R.id.btnHash);
        Button btnAterisk = v.findViewById(R.id.btnAterisk);
        Button btnClearAll = v.findViewById(R.id.btnClearAll);
        Button btndel = v.findViewById(R.id.btndel);
        Button btnSms = v.findViewById(R.id.btnSms);

        btnSms.setOnClickListener(this);
        btnAddContact.setOnClickListener(this);
        btndel.setOnClickListener(this);
        btnAterisk.setOnClickListener(this);
        btnClearAll.setOnClickListener(this);
        btnHash.setOnClickListener(this);
        btnOne.setOnClickListener(this);
        btnTwo.setOnClickListener(this);
        btnThree.setOnClickListener(this);
        btnFour.setOnClickListener(this);
        btnFive.setOnClickListener(this);
        btnSix.setOnClickListener(this);
        btnSeven.setOnClickListener(this);
        btnEight.setOnClickListener(this);
        btnNine.setOnClickListener(this);
        btnZero.setOnClickListener(this);
        btnCall.setOnClickListener(this);

        edtPhoneNo = v.findViewById(R.id.edtPhoneNumber);
        lblinfo = v.findViewById(R.id.lblinfo);
        return v;

    }

    @SuppressLint("ResourceType")
    @Override
    public void onClick(View v) {
        phoneNo = edtPhoneNo.getText().toString();
        switch (v.getId()) {
            case R.id.btnAterisk:
                lblinfo.setText("");
                phoneNo += "*";
                edtPhoneNo.setText(phoneNo);
                break;
            case R.id.btnHash:
                lblinfo.setText("");
                phoneNo += "#";
                edtPhoneNo.setText(phoneNo);
                break;
            case R.id.btnZero:
                lblinfo.setText("");
                phoneNo += "0";
                edtPhoneNo.setText(phoneNo);
                break;
            case R.id.btnOne:
                lblinfo.setText("");
                phoneNo += "1";
                edtPhoneNo.setText(phoneNo);
                break;
            case R.id.btnTwo:
                lblinfo.setText("");
                phoneNo += "2";
                edtPhoneNo.setText(phoneNo);
                break;
            case R.id.btnThree:
                lblinfo.setText("");
                phoneNo += "3";
                edtPhoneNo.setText(phoneNo);
                break;
            case R.id.btnFour:
                lblinfo.setText("");
                phoneNo += "4";
                edtPhoneNo.setText(phoneNo);
                break;
            case R.id.btnFive:
                lblinfo.setText("");
                phoneNo += "5";
                edtPhoneNo.setText(phoneNo);
                break;
            case R.id.btnSix:
                lblinfo.setText("");
                phoneNo += "6";
                edtPhoneNo.setText(phoneNo);
                break;
            case R.id.btnSeven:
                lblinfo.setText("");
                phoneNo += "7";
                edtPhoneNo.setText(phoneNo);
                break;
            case R.id.btnEight:
                lblinfo.setText("");
                phoneNo += "8";
                edtPhoneNo.setText(phoneNo);
                break;
            case R.id.btnNine:
                lblinfo.setText("");
                phoneNo += "9";
                edtPhoneNo.setText(phoneNo);
                break;
            case R.id.btndel:
                lblinfo.setText("");
                if (phoneNo != null && phoneNo.length() > 0) {
                    phoneNo = phoneNo.substring(0, phoneNo.length() - 1);
                }
                edtPhoneNo.setText(phoneNo);
                break;
            case R.id.btnClearAll:
                lblinfo.setText("");
                phoneNo = "";
                edtPhoneNo.setText(phoneNo);
                break;
            case R.id.btnCall:
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+phoneNo));
                startActivity(Intent.createChooser(callIntent,"Dialing to: "+phoneNo));
                break;
            case R.id.btnSms:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setData(Uri.parse("tel:"+phoneNo));
                startActivity(Intent.createChooser(intent,"Sending to: "+phoneNo));


                break;


            case R.id.btnAddContact:
                AddContactFragment add = new AddContactFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                Bundle args = new Bundle();
                args.putString("1",phoneNo);
                add.setArguments(args);
                fragmentManager.beginTransaction().remove(this).commit();
                fragmentTransaction.replace(android.R.id.content, add);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
        }
    }
}


