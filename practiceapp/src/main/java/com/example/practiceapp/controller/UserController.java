package com.example.practiceapp.controller;

import com.example.practiceapp.dto.UserDTO;
import com.example.practiceapp.model.User;
import com.example.practiceapp.service.UserService;
import com.example.practiceapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        try {
            List<UserDTO> users = userService.getAllUsers();
            if (users.isEmpty()) {
                logger.info("No users found.");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            logger.info("Fetched {} users.", users.size());
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error occurred while fetching users: ", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        try {
            Optional<UserDTO> user = userService.getUserById(id);
            if (user.isPresent()) {
                return new ResponseEntity<>(user.get(), HttpStatus.OK);
            } else {
                logger.info("User with id {} not found.", id);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("Error occurred while fetching user by id: ", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody User user) {
        try {
            UserDTO createdUser = userService.saveUser(user);
            logger.info("Created user with id {}", createdUser.getId());
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error occurred while creating user: ", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @Valid @RequestBody User userDetails) {
        try {
            Optional<UserDTO> user = userService.getUserById(id);
            if (user.isPresent()) {
                User updatedUser = userRepository.findById(id).get();
                updatedUser.setUsername(userDetails.getUsername());
                updatedUser.setEmail(userDetails.getEmail());
                updatedUser.setPassword(userDetails.getPassword());
                UserDTO updatedUserDTO = userService.saveUser(updatedUser);
                logger.info("Updated user with id {}", updatedUserDTO.getId());
                return new ResponseEntity<>(updatedUserDTO, HttpStatus.OK);
            } else {
                logger.info("User with id {} not found for update.", id);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("Error occurred while updating user: ", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id) {
        try {
            Optional<UserDTO> user = userService.getUserById(id);
            if (user.isPresent()) {
                userService.deleteUser(id);
                logger.info("Deleted user with id {}", id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                logger.info("User with id {} not found for deletion.", id);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("Error occurred while deleting user: ", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
