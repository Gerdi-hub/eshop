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

    public boolean validateUser(User user, String password) {
        return user.getPassword().equals(password); // Validate password
    }

    public Optional<User> getValidUser (String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent() && validateUser(user.get(), password)) {
            return user;
        } else {
            return Optional.empty();
        }
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
