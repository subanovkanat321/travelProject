package com.example.travelProject.controller;

import com.example.travelProject.service.FilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FilterController {
    @Autowired
    private FilterService usService;

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/me")
    public ResponseEntity<? extends Object> getMe() {
        try {
            return new ResponseEntity<>(usService.getMe(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/getPaidChecklists")
    public ResponseEntity<List<? extends Object>> getPaidChecklists() {
        try {
            return new ResponseEntity<>(usService.getPaidChecklists(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/getNotPaidChecklists")
    public ResponseEntity<List<? extends Object>> getNotPaidChecklists() {
        try {
            return new ResponseEntity<>(usService.getNotPaidChecklists(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/getConfirmPayments")
    public ResponseEntity<List<? extends Object>> getConfirmPayments() {
        try {
            return new ResponseEntity<>(usService.getConfirmPayments(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/getUnConfirmPayments")
    public ResponseEntity<List<? extends Object>> getUnConfirmPayments() {
        try {
            return new ResponseEntity<>(usService.getUnConfirmPayments(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/getComments")
    public ResponseEntity<List<? extends Object>> getComments() {
        try {
            return new ResponseEntity<>(usService.getComments(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
