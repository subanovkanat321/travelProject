package com.example.travel.controller;

import com.example.travel.model.Comment;
import com.example.travel.service.CommentService;
import com.example.travel.service.CrudService;
import com.example.travel.utils.PutMark;
import com.example.travel.utils.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    private final CrudService<Comment> commentCrudService;

    private final CommentService commentService;

    public CommentController(CrudService<Comment> commentCrudService, CommentService commentService) {
        this.commentCrudService = commentCrudService;
        this.commentService = commentService;
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/getComments")
    public ResponseEntity<List<?>> getFilteredComments() {

        try {
            return new ResponseEntity<>(commentService.getComments(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/putMark")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> putMark(@RequestBody PutMark putMark) {
        try {
            Comment comment = commentService.putMarkOnTheComment(putMark.getTourId(), putMark.getCommentId(),putMark.getMark());
            return new ResponseEntity<>(comment, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response(false, e.toString()), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/getAll")
    public ResponseEntity<List<?>> getComments() {
        try {
            return new ResponseEntity<>(commentCrudService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getCommentById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(commentCrudService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response(false, e.toString()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> saveComment(@RequestBody Comment comment) {
        try {
            return new ResponseEntity<>(commentCrudService.save(comment), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response(false, e.toString()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateComment(@RequestBody Comment comment) {
        try {
            return new ResponseEntity<>(commentCrudService.save(comment), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response(false, e.toString()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<?> deleteCommentById(@PathVariable Long id) {
        try {
            this.commentCrudService.deleteById(id);
            return new ResponseEntity<>("Deleted comment", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response(false, e.toString()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> deleteAllComments() {
        try {
            this.commentCrudService.deleteAll();
            return new ResponseEntity<>("Deleted all comments", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }
}
