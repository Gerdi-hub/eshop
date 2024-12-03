package com.example.eshop.controller;

import com.example.eshop.dto.UserDto;
import com.example.eshop.model.User;
import com.example.eshop.service.UserService;
import com.example.eshop.model.UserLoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final UserService userService;

    // Injecting the UserService to check for valid users
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<UserDto> login(@RequestBody UserLoginRequest request) {
        // Validate the username and password from the user table using the UserService
        Optional<User> validUser = userService.getValidUser(request.getUsername(), request.getPassword());

        if (validUser.isPresent()) {
            UserDto userDto = new UserDto(validUser.get());
            return ResponseEntity.ok(userDto);
        }

        // Return 401 Unauthorized if the username or password is incorrect
        return ResponseEntity.status(401).body(new UserDto());
    }
}
