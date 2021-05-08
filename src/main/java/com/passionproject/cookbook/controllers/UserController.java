package com.passionproject.cookbook.controllers;

import com.passionproject.cookbook.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.passionproject.cookbook.services.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    private final UserService svc;

    @Autowired
    public UserController(UserService svc) {
        this.svc = svc;
    }

    @PostMapping
    public ResponseEntity<User> addNewUser(@RequestBody User user) {
        return new ResponseEntity<>(this.svc.addUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> findUserById(@PathVariable Long userId) {
        return new ResponseEntity<>(this.svc.getUser(userId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Iterable<User>> findAllUsers() {
        return new ResponseEntity<>(this.svc.getAllUsers(), HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public Boolean deleteUserById(@PathVariable Long userId) {
        this.svc.deleteUser(userId);
        return this.svc.getUser(userId) == null;
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUserById(@PathVariable Long userId, @RequestBody User user) {
        return new ResponseEntity<>(this.svc.updateUser(userId, user), HttpStatus.OK);
    }
}
