package com.example.travelProject.controller;

import com.example.travelProject.model.CheckList;
import com.example.travelProject.service.CrudService;
import com.example.travelProject.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/checkList")
public class CheckListController {
    @Autowired
    private CrudService<CheckList> checkListCrudService;

    @GetMapping("/getAll")
    public ResponseEntity<List<? extends Object>> getCheckLists() {
        try {
            return new ResponseEntity<>(checkListCrudService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<? extends Object> getCheckListById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(checkListCrudService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response(false, e.toString()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<? extends Object> saveCheckList(@RequestBody CheckList checkList) {
        try {
            return new ResponseEntity<>(checkListCrudService.save(checkList), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response(false, e.toString()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<? extends Object> updateCheckList(@RequestBody CheckList checkList) {
        try {
            return new ResponseEntity<>(checkListCrudService.save(checkList), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response(false, e.toString()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<? extends Object> deleteCheckListById(@PathVariable Long id) {
        try {
            this.checkListCrudService.deleteById(id);
            return new ResponseEntity<>("Deleted checkList ", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response(false, e.toString()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<? extends Object> deleteAllCheckLists() {
        try {
            this.checkListCrudService.deleteAll();
            return new ResponseEntity<>("Deleted all checkLists", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response(false, e.toString()), HttpStatus.BAD_REQUEST);
        }
    }
}
