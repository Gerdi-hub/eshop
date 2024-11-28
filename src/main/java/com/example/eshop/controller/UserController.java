package com.example.eshop.controller;

import com.example.eshop.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/eshop")
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/validateUser/{username}/{password}")
    public boolean validateUser(@PathVariable ("username") String username, @PathVariable ("password") String password) {
        return  userService.validateUser(username, password);
    }


}
