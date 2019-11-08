package com.example.travelProject.controller;

import com.example.travelProject.model.Category;
import com.example.travelProject.service.CrudService;
import com.example.travelProject.utils.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CrudService<Category> categoryService;

    public CategoryController(CrudService<Category> categoryService) {
        this.categoryService = categoryService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/getAll")
    public ResponseEntity<List<?>> getCategories() {
        try {
            return new ResponseEntity<>(categoryService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(categoryService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response(false, e.toString()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> saveCategory(@RequestBody Category category) {
        try {
            return new ResponseEntity<>(categoryService.save(category), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response(false, e.toString()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateCategory(@RequestBody Category category) {
        try {
            return new ResponseEntity<>(categoryService.save(category), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response(false, e.toString()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<? extends Object> deleteCategoryById(@PathVariable Long id) {
        try {
            this.categoryService.deleteById(id);
            return new ResponseEntity<>("Deleted category ", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response(false, e.toString()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<? extends Object> deleteAllCategories() {
        try {
            this.categoryService.deleteAll();
            return new ResponseEntity<>("Deleted all categories", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response(false, e.toString()), HttpStatus.BAD_REQUEST);
        }
    }
}
