package com.biplob.cleanarchitecturedemo.domain.model;

//Domain layer does not depend on anything else.
//
//It’s pure Java, and fully unit-testable.
//
//This is the "innermost" part of clean architecture.

public class User {
    private int id;
    private String name;
    private String username;
    private String email;

    // (Add more fields as needed)

    public User(int id, String name, String username, String email) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
    }

    // Getters (no setters unless you want mutability)
    public int getId() { return id; }
    public String getName() { return name; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
}