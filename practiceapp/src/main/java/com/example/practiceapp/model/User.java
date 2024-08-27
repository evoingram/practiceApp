package com.example.practiceapp.model;

import com.example.practiceapp.security.Authenticatable;
import javax.persistence.*;

@Entity
@Table(name = "users")
public class User implements Authenticatable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;

    public User() {
        // Default constructor
    }

    public User(String username, String password, String email) {

        this.username = username;
        this.password = password;
        this.email = email;
    }

    // Getters and setters for encapsulation
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean authenticate(String password) {
        return this.password.equals(password);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
    
    // Method demonstrating encapsulation and a Java 11 feature (String API enhancement)
    public boolean isPasswordStrong() {
        return password.length() >= 8 &&
               password.chars().anyMatch(Character::isUpperCase) &&
               password.chars().anyMatch(Character::isLowerCase) &&
               password.chars().anyMatch(Character::isDigit);
    }
}
