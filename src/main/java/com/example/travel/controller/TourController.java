package com.example.travel.controller;

import com.example.travel.model.Tour;
import com.example.travel.service.CrudService;
import com.example.travel.service.TourService;
import com.example.travel.utils.AddComment;
import com.example.travel.utils.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tour")
public class TourController {
    private final CrudService<Tour> tourCrudService;

    private final TourService tourService;

    public TourController(CrudService<Tour> tourCrudService, TourService tourService) {
        this.tourCrudService = tourCrudService;
        this.tourService = tourService;
    }

    @PostMapping("/addComment")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addComment(@RequestBody AddComment addComment) {
        try {
            Tour tour = tourService.addComment(addComment.getTourId(), addComment.getText());
            return new ResponseEntity<>(tour, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response(false, e.toString()), HttpStatus.BAD_REQUEST);
        }
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/getAll")
    public ResponseEntity<List<?>> getTours() {
        try {
            return new ResponseEntity<>(tourCrudService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getTourById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(tourCrudService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response(false, e.toString()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> saveTour(@RequestBody Tour tour) {
        try {
            return new ResponseEntity<>(tourCrudService.save(tour), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response(false, e.toString()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateTour(@RequestBody Tour tour) {
        try {
            return new ResponseEntity<>(tourCrudService.save(tour), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response(false, e.toString()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<?> deleteTourById(@PathVariable Long id) {
        try {
            this.tourCrudService.deleteById(id);
            return new ResponseEntity<>("Deleted tour ", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response(false, e.toString()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> deleteAllTours() {
        try {
            this.tourCrudService.deleteAll();
            return new ResponseEntity<>("Deleted all tours", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response(false, e.toString()), HttpStatus.BAD_REQUEST);
        }
    }
}
