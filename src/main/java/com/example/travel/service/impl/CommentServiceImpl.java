package com.example.travel.service.impl;

import com.example.travel.enums.Mark;
import com.example.travel.helpers.CurrentUser;
import com.example.travel.model.Comment;
import com.example.travel.model.Tour;
import com.example.travel.model.User;
import com.example.travel.model.UserPutMark;
import com.example.travel.repository.CommentRepository;
import com.example.travel.repository.TourRepository;
import com.example.travel.repository.UserPutMarkRepository;
import com.example.travel.repository.UserRepository;
import com.example.travel.service.CommentService;
import com.example.travel.service.CrudService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class CommentServiceImpl implements CrudService<Comment>, CommentService {
    private final CommentRepository commentRepository;

    private final UserPutMarkRepository userPutMarkRepository;

    private final UserRepository userRepository;

    private final CurrentUser currentUser;

    private final TourRepository tourRepository;

    public CommentServiceImpl(CommentRepository commentRepository,
                              UserPutMarkRepository userPutMarkRepository,
                              UserRepository userRepository,
                              CurrentUser currentUser,
                              TourRepository tourRepository) {
        this.commentRepository = commentRepository;
        this.userPutMarkRepository = userPutMarkRepository;
        this.userRepository = userRepository;
        this.currentUser = currentUser;
        this.tourRepository = tourRepository;
    }

    @Override
    public List<Comment> getComments() {
        List<Comment> comments = commentRepository.findCommentsByUser(currentUser.getUser());
        Collections.sort(comments);
        Collections.reverse(comments);
        return comments;
    }

    @Override
    public Comment putMarkOnTheComment(Long tourId, Long commentId, Mark mark) {
        User user1 = userRepository.findById(currentUser.getUser().getId()).get();
        Tour tour = tourRepository.findById(tourId).get();
        Comment c = commentRepository.findById(commentId).get();
        List<UserPutMark> userBln = c.getUserChecks();
        List<User> userIds = c.getUsers();
        List<Comment> comments = tour.getComments();
        boolean checkUserIds = true;
        for (Comment comment : comments) {
            if (comment.getId().equals(commentId)) {
                for (User user : userIds) {
                    if (user.getId().equals(user1.getId())) {
                        checkUserIds = false;
                        break;
                    }
                }
            }
        }
        if (checkUserIds) {
            if (mark.equals(Mark.LIKE)) {
                c.setLikes(c.getLikes() + 1);
                UserPutMark userPutMark = new UserPutMark();
                userPutMark.setUser(user1);
                userPutMark.setMark(Mark.LIKE);
                userPutMarkRepository.save(userPutMark);
                userBln.add(userPutMark);
                userIds.add(user1);
            } else if (mark.equals(Mark.DISLIKE)) {
                c.setLikes(c.getDislikes() + 1);
                UserPutMark userPutMark = new UserPutMark();
                userPutMark.setUser(user1);
                userPutMark.setMark(Mark.DISLIKE);
                userPutMarkRepository.save(userPutMark);
                userBln.add(userPutMark);
                userIds.add(user1);
            }
        } else {
            for (UserPutMark userCheck : userBln) {
                if (userCheck.getUser().getId().equals(user1.getId())) {
                    if (userCheck.getMark().equals(Mark.LIKE)) {
                        if (mark.equals(Mark.DISLIKE)) {
                            userCheck.setMark(Mark.DISLIKE);
                            c.setDislikes(c.getDislikes() + 1);
                            c.setLikes(c.getLikes() - 1);
                            userPutMarkRepository.save(userCheck);
                        }
                    } else {
                        if (mark.equals(Mark.LIKE)) {
                            c.setDislikes(c.getDislikes() - 1);
                            c.setLikes(c.getLikes() + 1);
                            userCheck.setMark(Mark.LIKE);
                            userPutMarkRepository.save(userCheck);
                        }
                    }
                }
            }
        }
        c.setUsers(userIds);
        c.setUserChecks(userBln);
        c.setTime(LocalDateTime.now());
        commentRepository.save(c);
        return c;
    }

    //Crud methods
    @Override
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment findById(Long id) {
        return commentRepository.findById(id).get();
    }

    @Override
    public Comment save(Comment feedback) {
        return commentRepository.save(feedback);
    }

    @Override
    public Comment update(Comment feedback) {
        return commentRepository.save(feedback);
    }

    @Override
    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        commentRepository.deleteAll();
    }
}
