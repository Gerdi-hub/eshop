package com.example.eshop.service;

import com.example.eshop.model.User;
import com.example.eshop.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean validateUser(String username, String password) {
        // Look for the user with the given username
        Optional<User> user = userRepository.findByUsername(username);
        return user.isPresent() && user.get().getPassword().equals(password); // Validate password
    }

    public String getUserName(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        String name = user.get().getFirstName() + " " + user.get().getLastName();
        return name;
    }

    public String getUserEmail(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        String email = user.get().getEmail();
        return email;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }



}
