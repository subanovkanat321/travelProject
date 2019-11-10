package com.example.travel.service;

import com.example.travel.enums.Mark;
import com.example.travel.model.Comment;

import java.util.List;

public interface CommentService {
    Comment putMarkOnTheComment(Long tourId, Long commentId, Mark mark);

    List<Comment> getComments();

}
