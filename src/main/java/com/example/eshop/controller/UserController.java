package com.example.eshop.controller;

import com.example.eshop.dto.UserDto;
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

    @GetMapping("/allusers")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/addnewuser")
    public User addNewUser(@RequestBody User user) {
        return userService.addNewUser(user);
    }
    @GetMapping("/oneuser")
    public UserDto oneUser(@RequestBody User user)
  { return userService.getOneUser(user);
  }

}



