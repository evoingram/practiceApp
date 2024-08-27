package com.example.practiceapp.model;

import java.util.*;
import com.example.practiceapp.exception.InvalidCredentialsException;
import com.example.practiceapp.exception.UserNotFoundException;

public class UserManager {
    private Map<String, User> users = new HashMap<>();

    public void addUser(User user) {
        users.put(user.getUsername(), user);
    }

    public User getUser(String username) throws UserNotFoundException {
        User user = users.get(username);
        if (user == null) {
            throw new UserNotFoundException("User not found: " + username);
        }
        return user;
    }

    public void authenticateUser(String username, String password) throws UserNotFoundException, InvalidCredentialsException {
        User user = getUser(username);
        if (!user.authenticate(password)) {
            throw new InvalidCredentialsException("Invalid credentials for user: " + username);
        }
        System.out.println("User authenticated successfully: " + user);
    }

    public void printAllUsers() {
        users.values().forEach(System.out::println);
    }
}
