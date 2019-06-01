package com.example.travelProject.controller;

import com.example.travelProject.model.Comment;
import com.example.travelProject.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CrudService<Comment> commentCrudService;

    @GetMapping("/getAll")
    public ResponseEntity<List<? extends Object>> getComments() {
        try {
            return new ResponseEntity<>(commentCrudService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<? extends Object> getCommentById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(commentCrudService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<? extends Object> saveComment(@RequestBody Comment comment) {
        try {
            return new ResponseEntity<>(commentCrudService.save(comment), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<? extends Object> updateComment(@RequestBody Comment comment) {
        try {
            return new ResponseEntity<>(commentCrudService.save(comment), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<? extends Object> deleteCommentById(@PathVariable Long id) {
        try {
            this.commentCrudService.deleteById(id);
            return new ResponseEntity<>("Deleted comment", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<? extends Object> deleteAllComments() {
        try {
            this.commentCrudService.deleteAll();
            return new ResponseEntity<>("Deleted all comments", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }
}
