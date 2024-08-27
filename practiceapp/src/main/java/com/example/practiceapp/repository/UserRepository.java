package com.example.practiceapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.practiceapp.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
