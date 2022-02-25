package com.example.contactapp;

import android.widget.ImageView;

public class Contact {
    String gendar, last, name, phone;
    ImageView profilePic;

    public Contact() {
    }

    public Contact(String gendar, ImageView profilePic, String name,String last) {
        this.gendar = gendar;
        this.profilePic= profilePic;
        this.name = name;
        this.last=last;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGendar() {
        return gendar;
    }

    public void setGendar(String gendar) {
        this.gendar = gendar;
    }
}