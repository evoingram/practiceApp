package com.example.practiceapp.model;

public class RegularUser extends User {

    public RegularUser(String username, String password, String email) {
        super(username, password, email);
    }

    @Override
    public boolean authenticate(String password) {
        // Simple authentication for regular users
        return getPassword().equals(password);
    }
}
