package com.example.travelProject.controller;

import com.example.travelProject.model.User;
import com.example.travelProject.service.CrudService;
import com.example.travelProject.utils.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final CrudService<User> userService;

    public UserController(CrudService<User> userService) {
        this.userService = userService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<?>> getUsers() {
        try {
            return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response(false, e.toString()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        try {
            return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response(false, e.toString()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        try {
            return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response(false, e.toString()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
        try {
            this.userService.deleteById(id);
            return new ResponseEntity<>("Deleted tour", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response(false, e.toString()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> deleteAllUsers() {
        try {
            this.userService.deleteAll();
            return new ResponseEntity<>("Deleted all users", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response(false, e.toString()), HttpStatus.BAD_REQUEST);
        }
    }
}
