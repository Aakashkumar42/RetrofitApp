package com.example.retrofitapp.model;

public class User {

    private int id;
    private String name,email,school;

    public User(int id, String name, String email, String school) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.school = school;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getSchool() {
        return school;
    }
}

