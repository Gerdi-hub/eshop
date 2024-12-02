package com.example.eshop.controller;



import com.example.eshop.service.UserService;
import com.example.eshop.model.UserLoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final UserService userService;

    // Injecting the UserService to check for valid users
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> login(@RequestBody UserLoginRequest request) {
        // Validate the username and password from the user table using the UserService
        boolean isValidUser = userService.validateUser(request.getUsername(), request.getPassword());

        if (isValidUser) {
            // Here you can generate a real token, e.g., using JWT or session-based authentication
            // For now, we're returning a mock token
            return ResponseEntity.ok("{ \"token\": \"mock-token\" }");
        }

        // Return 401 Unauthorized if the username or password is incorrect
        return ResponseEntity.status(401).body("Invalid username or password");
    }
}
