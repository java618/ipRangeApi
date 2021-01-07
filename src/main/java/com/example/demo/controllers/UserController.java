package com.example.demo.controllers;

import com.example.demo.models.UserRequestModel;
import com.example.demo.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class UserController {
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Autowired
    UserServiceImpl userService;

    @PostMapping("/login")
    public ResponseEntity<Boolean> checkUser(@RequestBody UserRequestModel user){
        return userService.checkUser(user);
    }

    @PostMapping("/registration")
    public ResponseEntity<String> creatUser(@RequestBody UserRequestModel user){
        return userService.createUser(user);
    }
}
