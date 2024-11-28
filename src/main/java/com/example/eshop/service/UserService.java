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
    boolean validUser = false;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public boolean validateUser(String username, String password) {
        List<User> userList = userRepository.findAll();

        for (User user : userList) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                validUser = true;

            }
        }
        return validUser;
    }

    public User addNewUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
        validUser = false;

    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }



}
