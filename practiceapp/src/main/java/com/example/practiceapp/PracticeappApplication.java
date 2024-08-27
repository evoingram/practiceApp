package com.example.practiceapp;

import com.example.practiceapp.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.example.practiceapp")
@EnableJpaRepositories(basePackages = "com.example.practiceapp.repository")
public class PracticeappApplication {
    public static void main(String[] args) {
        SpringApplication.run(PracticeappApplication.class, args);
    }
}
