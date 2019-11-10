package com.example.travel.repository;

import com.example.travel.model.Comment;
import com.example.travel.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findCommentsByUser(User user);
}
