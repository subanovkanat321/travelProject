package com.example.travelProject.controller;

import com.example.travelProject.service.FilterService;
import com.example.travelProject.utils.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FilterController {


    private final FilterService usService;

    public FilterController(FilterService usService) {
        this.usService = usService;
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/me")
    public ResponseEntity<?> getMe() {
        try {
            return new ResponseEntity<>(usService.getMe(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response(false, e.toString()), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/getPaidChecklists")
    public ResponseEntity<List<?>> getPaidChecklists() {
        try {
            return new ResponseEntity<>(usService.getPaidChecklists(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/getNotPaidChecklists")
    public ResponseEntity<List<?>> getNotPaidChecklists() {
        try {
            return new ResponseEntity<>(usService.getNotPaidChecklists(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/getConfirmPayments")
    public ResponseEntity<List<?>> getConfirmPayments() {
        try {
            return new ResponseEntity<>(usService.getConfirmPayments(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/getUnConfirmPayments")
    public ResponseEntity<List<?>> getUnConfirmPayments() {
        try {
            return new ResponseEntity<>(usService.getUnConfirmPayments(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/getComments")
    public ResponseEntity<List<?>> getComments() {

        try {
            return new ResponseEntity<>(usService.getComments(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
