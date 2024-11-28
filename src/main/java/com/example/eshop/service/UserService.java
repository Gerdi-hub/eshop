package com.example.eshop.service;

import com.example.eshop.model.User;
import com.example.eshop.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean validateUser(String username, String password) {
        List<User> userList = userRepository.findAll();
        boolean validUser = false;
        for (User user : userList) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                validUser = true;

            }
        }
        return validUser;
    }



}
