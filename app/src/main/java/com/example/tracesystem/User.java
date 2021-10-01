package com.example.tracesystem;

public class User {
    public String name , email, phone, password;

    public User(String name, String email, String password, String phone){

    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;


    }
}

