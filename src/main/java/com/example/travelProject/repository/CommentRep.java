package com.example.travelProject.repository;

import com.example.travelProject.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRep
        extends JpaRepository<Comment, Long> {
}
