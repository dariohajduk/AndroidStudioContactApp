package com.example.contactapp;

import android.widget.ImageView;

public class Contact {
    String gendar, last, name, phone,profilePic;


    public Contact() {
    }

    public Contact(String gendar, String name,String last, String phone) {
        this.gendar = gendar;
        this.name = name;
        this.last=last;
        this.phone=phone;
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