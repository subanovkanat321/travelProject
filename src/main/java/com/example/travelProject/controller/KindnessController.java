package com.example.travelProject.controller;

import com.example.travelProject.model.Kindness;
import com.example.travelProject.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kindness")
public class KindnessController {
    @Autowired
    private CrudService<Kindness> kindnessService;

    @GetMapping("/getAll")
    public ResponseEntity<List<? extends Object>> getKindnesses() {
        try {
            return new ResponseEntity<>(kindnessService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<? extends Object> getKindnessById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(kindnessService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<? extends Object> saveKindness(@RequestBody Kindness kindness) {
        try {
            return new ResponseEntity<>(kindnessService.save(kindness), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<? extends Object> updateKindness(@RequestBody Kindness kindness) {
        try {
            return new ResponseEntity<>(kindnessService.save(kindness), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<? extends Object> deleteKindnessById(@PathVariable Long id) {
        try {
            this.kindnessService.deleteById(id);
            return new ResponseEntity<>("Deleted kindness", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<? extends Object> deleteAllKindnesses() {
        try {
            this.kindnessService.deleteAll();
            return new ResponseEntity<>("Deleted all kindnesses", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }
}
