package com.example.travelProject.controller;

import com.example.travelProject.model.CheckList;
import com.example.travelProject.service.CrudService;
import com.example.travelProject.utils.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/checkList")
public class CheckListController {
    private final CrudService<CheckList> checkListCrudService;

    public CheckListController(CrudService<CheckList> checkListCrudService) {
        this.checkListCrudService = checkListCrudService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<?>> getCheckLists() {
        try {
            return new ResponseEntity<>(checkListCrudService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getCheckListById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(checkListCrudService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response(false, e.toString()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> saveCheckList(@RequestBody CheckList checkList) {
        try {
            return new ResponseEntity<>(checkListCrudService.save(checkList), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response(false, e.toString()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateCheckList(@RequestBody CheckList checkList) {
        try {
            return new ResponseEntity<>(checkListCrudService.save(checkList), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response(false, e.toString()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<?> deleteCheckListById(@PathVariable Long id) {
        try {
            this.checkListCrudService.deleteById(id);
            return new ResponseEntity<>("Deleted checkList ", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response(false, e.toString()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> deleteAllCheckLists() {
        try {
            this.checkListCrudService.deleteAll();
            return new ResponseEntity<>("Deleted all checkLists", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response(false, e.toString()), HttpStatus.BAD_REQUEST);
        }
    }
}
