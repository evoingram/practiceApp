package com.example.practiceapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@SpringBootApplication
public class PracticeappApplication {

    private static final Logger logger = LoggerFactory.getLogger(PracticeappApplication.class);

    public static void main(String[] args) {
        logger.info("Before starting the application...");
        logger.info("Current directory: " + new File(".").getAbsolutePath());
    
        try (FileWriter writer = new FileWriter("logs/manual-logfile.log", true)) {
            writer.write("Manual log entry: Application is starting.\n");
        } catch (IOException e) {
            logger.error("Failed to write manual log file.", e);
        }        
        
        SpringApplication.run(PracticeappApplication.class, args);
        logger.info("After application has started...");
    }
}
