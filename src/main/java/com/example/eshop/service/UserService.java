package com.example.eshop.service;

import com.example.eshop.dto.UserDto;
import com.example.eshop.model.User;
import com.example.eshop.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
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


    public List<UserDto> getAllUsers() {
        List<User> AllUsers = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : AllUsers) {
            userDtos.add(new UserDto(user));
        }
        return userDtos;
    }

    public User addNewUser(User user) {
        return userRepository.save(user);
    }

//    public UserDto getOneUser(String username) {
//
//    Optional<User> user = userRepository.findByUsername(username);
//      return user.isPresent() ? new UserDto(user.get()) : null;
//    }

    public void updateUser(String username, User updatedUser) {
        // Retrieve the existing user by username
        Optional<User> optionalUser = userRepository.findByUsername(username);

        // Check if the user exists
        User existingUser = optionalUser
                .orElseThrow(() ->
                new UserNotFoundException("User with username " + username + " not found")
        );

        // Update fields of the existing user with values from the updated user object
        existingUser.setFirstName(updatedUser.getFirstName());
        existingUser.setLastName(updatedUser.getLastName());
        existingUser.setEmail(updatedUser.getEmail());


        // Save the updated user back to the repository
        userRepository.save(existingUser);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String message) {
            super(message);
        }
    }
}


