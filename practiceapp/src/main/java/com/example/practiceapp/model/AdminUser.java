package com.example.practiceapp.model;

public class AdminUser extends User {

    private String adminCode;

    public AdminUser(String username, String password, String email, String adminCode) {
        super(username, password, email);
        this.adminCode = adminCode;
    }

    public String getAdminCode() {
        return adminCode;
    }

    public void setAdminCode(String adminCode) {
        this.adminCode = adminCode;
    }

    @Override
    public boolean authenticate(String password) {
        // Example of polymorphism - additional authentication step for admins
        return getPassword().equals(password) && "SECRET_ADMIN_CODE".equals(adminCode);
    }
}
