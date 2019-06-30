package com.example.travelProject.controller;

import com.example.travelProject.model.*;
import com.example.travelProject.service.LogicService;
import com.example.travelProject.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogicController {
    @Autowired
    private LogicService usService;


    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<? extends Object> registration(@RequestBody User user) {
        try {
            return new ResponseEntity<>(usService.registration(user), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response(false, e.toString()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/buyTour")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<? extends Object> buyTour(@RequestBody BuyTour buyTour) {
        try {
            Payment payment = usService.buyTour(buyTour.getTourId(), buyTour.getCategoryId(),
                    buyTour.getHowManyDays(), buyTour.getHowManyPeople());
            return new ResponseEntity<>(payment, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response(false, e.toString()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/toPay")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<? extends Object> toPay(@RequestBody ToPay toPay) {
        try {
            CheckList checkList = usService.toPay(toPay.getConfirmCode(), toPay.getSum());
            return new ResponseEntity<>(checkList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response(false, e.toString()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addComment")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<? extends Object> addComment(@RequestBody AddComment addComment) {
        try {
            Tour tour = usService.addComment(addComment.getTourId(), addComment.getText());
            return new ResponseEntity<>(tour, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response(false, e.toString()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/putMark")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<? extends Object> putMark(@RequestBody PutMark putMark) {
        try {
            Comment comment = usService.putMarkOnTheComment(putMark.getTourId(), putMark.getCommentId(),putMark.getMark());
            return new ResponseEntity<>(comment, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response(false, e.toString()), HttpStatus.BAD_REQUEST);
        }
    }
}
