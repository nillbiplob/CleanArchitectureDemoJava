package com.biplob.cleanarchitecturedemo.data.model;

import com.google.gson.Gson;

import java.lang.reflect.Constructor;// DTO (Data Transfer Object) for User
/*
Why No Constructor in DTO?
Gson and most JSON mappers require a public no-arg constructor and setters to create objects reflectively.

If you add a constructor with all fields, Gson might not use it and will still require the empty one.

        So:

DTO = empty constructor + setters + getters (for Gson).

Domain entity = full constructor + getters only.

 */


public class UserDto {
    private int id;
    private String name;
    private String username;
    private String email;

    // (Add more fields as needed, matching API)

    // Required empty constructor for Gson/serialization
    public UserDto() {}

    // Getters and setters (or use public fields for DTOs)
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
