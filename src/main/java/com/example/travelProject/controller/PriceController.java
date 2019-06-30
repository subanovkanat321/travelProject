package com.example.travelProject.controller;

import com.example.travelProject.model.Price;
import com.example.travelProject.service.CrudService;
import com.example.travelProject.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/price")
public class PriceController {
    @Autowired
    private CrudService<Price> priceCrudService;

    @GetMapping("/getAll")
    public ResponseEntity<List<? extends Object>> getPrices() {
        try {
            return new ResponseEntity<>(priceCrudService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<? extends Object> getPriceById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(priceCrudService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response(false, e.toString()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<? extends Object> savePrice(@RequestBody Price price) {
        try {
            return new ResponseEntity<>(priceCrudService.save(price), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response(false, e.toString()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<? extends Object> updatePrice(@RequestBody Price price) {
        try {
            return new ResponseEntity<>(priceCrudService.save(price), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response(false, e.toString()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<? extends Object> deletePriceById(@PathVariable Long id) {
        try {
            this.priceCrudService.deleteById(id);
            return new ResponseEntity<>("Deleted price" + id, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response(false, e.toString()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<? extends Object> deleteAllPrices() {
        try {
            this.priceCrudService.deleteAll();
            return new ResponseEntity<>("Deleted all prices", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response(false, e.toString()), HttpStatus.BAD_REQUEST);
        }
    }
}
