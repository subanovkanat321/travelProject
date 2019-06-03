package com.example.travelProject.controller;

import com.example.travelProject.model.*;
import com.example.travelProject.service.CrudService;
import com.example.travelProject.service.UserService;
import com.example.travelProject.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private CrudService<User> userService;
    @Autowired
    private UserService usService;

    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<? extends Object> registration(@RequestBody User user) {
        try {
            return new ResponseEntity<>(usService.registration(user), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<? extends Object> logIn(@RequestBody LogIn logIn) {
        try {
            if (usService.logIn(logIn.getEmail(), logIn.getPassword())) {
                return new ResponseEntity<>("successfully logged in", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("no such user", HttpStatus.NOT_ACCEPTABLE);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/buyTour")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<? extends Object> buyTour(@RequestBody BuyTour buyTour) {
        try {
            Payment payment = usService.byeTour(buyTour.getUserId(), buyTour.getTourId(), buyTour.getCategoryId(),
                    buyTour.getHowManyDays(), buyTour.getHowManyPeople());
            return new ResponseEntity<>(payment, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/toPay")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<? extends Object> toPay(@RequestBody ToPay toPay) {
        try {
            CheckList checkList = usService.toPay(toPay.getUserId(), toPay.getConfirmCode(), toPay.getSum());
            return new ResponseEntity<>(checkList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addComment")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<? extends Object> addComment(@RequestBody AddComment addComment) {
        try {
            Tour tour = usService.addComment(addComment.getUserId(), addComment.getTourId(), addComment.getText());
            return new ResponseEntity<>(tour, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/putMark")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<? extends Object> putMark(@RequestBody PutMark putMark) {
        try {
            Comment comment = usService.putMarkOnTheComment(putMark.getUserId(), putMark.getTourId(), putMark.getCommentId(),putMark.getMark());
            return new ResponseEntity<>(comment, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<? extends Object>> getUsers() {
        try {
            return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<? extends Object> getUserById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<? extends Object> saveUser(@RequestBody User user) {
        try {
            return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<? extends Object> updateUser(@RequestBody User user) {
        try {
            return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<? extends Object> deleteUserById(@PathVariable Long id) {
        try {
            this.userService.deleteById(id);
            return new ResponseEntity<>("Deleted tour", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<? extends Object> deleteAllUsers() {
        try {
            this.userService.deleteAll();
            return new ResponseEntity<>("Deleted all users", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }
}
