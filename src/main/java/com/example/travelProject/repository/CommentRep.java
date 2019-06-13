package com.example.travelProject.repository;

import com.example.travelProject.model.Comment;
import com.example.travelProject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRep
        extends JpaRepository<Comment, Long> {
    List<Comment> findCommentsByUser(User user);
}
