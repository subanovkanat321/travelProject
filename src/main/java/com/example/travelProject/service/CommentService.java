package com.example.travelProject.service;

import com.example.travelProject.model.Comment;
import com.example.travelProject.repository.CommentRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService  implements CrudService<Comment>{
    @Autowired
    private CommentRep commentRep;

    @Override
    public List<Comment> getAll() {
        return commentRep.findAll();
    }

    @Override
    public Comment findById(Long id) {
        return commentRep.findById(id).get();
    }

    @Override
    public Comment save(Comment feedback) {
        return commentRep.save(feedback);
    }

    @Override
    public Comment update(Comment feedback) {
        return commentRep.save(feedback);
    }

    @Override
    public void deleteById(Long id) {
        commentRep.deleteById(id);
    }

    @Override
    public void deleteAll() {
        commentRep.deleteAll();
    }
}
