package com.org.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.ecommerce.modal.Admin;
import com.org.ecommerce.modal.User;
import com.org.ecommerce.requests.ChangePasswordRequest;
import com.org.ecommerce.requests.CreateAdminRequest;
import com.org.ecommerce.service.UserServices;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServices userServices;



    @GetMapping("/list")
    public ResponseEntity<List<User>> get(){
        List<User> users = userServices.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/create")
    public ResponseEntity<User> create(User user){
        User newUser = userServices.createUser(user);
        return ResponseEntity.ok(newUser);
    }

    @PostMapping("/update")
    public ResponseEntity<Integer> update(@RequestBody User user){
        int updatedUser = userServices.updateUser(user);
        return ResponseEntity.ok(updatedUser);
    }


    @PostMapping("/changePassword")
    public ResponseEntity<Integer> changePassword(@RequestBody User user){
        int updatedUser = userServices.updateUser(user);
        return ResponseEntity.ok(updatedUser);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<User> authenticate(@RequestBody User user){
        User authenticatedUser = userServices.authenticate(user.getEmail(), user.getPwd());
        return ResponseEntity.ok(authenticatedUser);
    }

    @GetMapping("/getUserById")
    public ResponseEntity<User> getUserById(long id){
        User user = userServices.getUserById(id);
        return ResponseEntity.ok(user);
    }
    
}
