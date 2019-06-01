package com.example.travelProject.controller;

import com.example.travelProject.model.Tour;
import com.example.travelProject.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tour")
public class TourController {
    @Autowired
    private CrudService<Tour> tourCrudService;

    @GetMapping("/getAll")
    public ResponseEntity<List<? extends Object>> getTours() {
        try {
            return new ResponseEntity<>(tourCrudService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<? extends Object> getTourById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(tourCrudService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<? extends Object> saveTour(@RequestBody Tour tour) {
        try {
            return new ResponseEntity<>(tourCrudService.save(tour), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<? extends Object> updateTour(@RequestBody Tour tour) {
        try {
            return new ResponseEntity<>(tourCrudService.save(tour), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<? extends Object> deleteTourById(@PathVariable Long id) {
        try {
            this.tourCrudService.deleteById(id);
            return new ResponseEntity<>("Deleted tour ", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<? extends Object> deleteAllTours() {
        try {
            this.tourCrudService.deleteAll();
            return new ResponseEntity<>("Deleted all tours", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }
}
