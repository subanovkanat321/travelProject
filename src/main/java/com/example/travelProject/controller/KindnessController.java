package com.example.travelProject.controller;

import com.example.travelProject.model.Kindness;
import com.example.travelProject.service.CrudService;
import com.example.travelProject.utils.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kindness")
public class KindnessController {
    private final CrudService<Kindness> kindnessService;

    public KindnessController(CrudService<Kindness> kindnessService) {
        this.kindnessService = kindnessService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<?>> getKindnesses() {
        try {
            return new ResponseEntity<>(kindnessService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getKindnessById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(kindnessService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response(false, e.toString()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> saveKindness(@RequestBody Kindness kindness) {
        try {
            return new ResponseEntity<>(kindnessService.save(kindness), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response(false, e.toString()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateKindness(@RequestBody Kindness kindness) {
        try {
            return new ResponseEntity<>(kindnessService.save(kindness), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response(false, e.toString()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<? extends Object> deleteKindnessById(@PathVariable Long id) {
        try {
            this.kindnessService.deleteById(id);
            return new ResponseEntity<>("Deleted kindness", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response(false, e.toString()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<? extends Object> deleteAllKindnesses() {
        try {
            this.kindnessService.deleteAll();
            return new ResponseEntity<>("Deleted all kindnesses", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response(false, e.toString()), HttpStatus.BAD_REQUEST);
        }
    }
}
