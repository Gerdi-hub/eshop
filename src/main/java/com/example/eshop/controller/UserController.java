package com.example.eshop.controller;

import com.example.eshop.model.User;
import com.example.eshop.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/eshop")
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/validateUser")
    public boolean validateUser(@RequestBody User user) {
        return userService.validateUser(user.getUsername(), user.getPassword());
    }

@GetMapping("/username")
    public String getUserName(@RequestBody User user) {

    return userService.getUserName(user.getUsername());
}

    @GetMapping("/useremail")
    public String getUserEmail(@RequestBody User user) {

        return userService.getUserEmail(user.getUsername());
    }

}
