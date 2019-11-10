package com.example.travel.controller;

import com.example.travel.model.CheckList;
import com.example.travel.service.ChecklistService;
import com.example.travel.service.CrudService;
import com.example.travel.utils.Response;
import com.example.travel.utils.ToPay;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/checkList")
public class CheckListController {
    private final CrudService<CheckList> checkListCrudService;

    private final ChecklistService checklistService;

    public CheckListController(CrudService<CheckList> checkListCrudService,
                               ChecklistService checklistService) {
        this.checkListCrudService = checkListCrudService;
        this.checklistService = checklistService;
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/getPaidChecklists")
    public ResponseEntity<List<?>> getPaidChecklists() {
        try {
            return new ResponseEntity<>(checklistService.getPaidChecklists(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/getNotPaidChecklists")
    public ResponseEntity<List<?>> getNotPaidChecklists() {
        try {
            return new ResponseEntity<>(checklistService.getNotPaidChecklists(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/toPay")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> toPay(@RequestBody ToPay toPay) {
        try {
            CheckList checkList = checklistService.toPay(toPay.getConfirmCode(), toPay.getSum());
            return new ResponseEntity<>(checkList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response(false, e.toString()), HttpStatus.BAD_REQUEST);
        }
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
