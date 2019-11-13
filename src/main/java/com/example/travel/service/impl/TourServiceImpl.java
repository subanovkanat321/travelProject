package com.example.travel.service.impl;

import com.example.travel.helpers.CurrentUser;
import com.example.travel.model.Comment;
import com.example.travel.model.Tour;
import com.example.travel.model.User;
import com.example.travel.model.UserPutMark;
import com.example.travel.repository.CommentRepository;
import com.example.travel.repository.TourRepository;
import com.example.travel.repository.UserRepository;
import com.example.travel.service.CrudService;
import com.example.travel.service.TourService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TourServiceImpl implements CrudService<Tour>, TourService {
    private final TourRepository tourRepository;

    private final UserRepository userRepository;

    private final CurrentUser currentUser;

    private final CommentRepository commentRepository;

    public TourServiceImpl(TourRepository tourRepository,
                           UserRepository userRepository,
                           CurrentUser currentUser,
                           CommentRepository commentRepository) {
        this.tourRepository = tourRepository;
        this.userRepository = userRepository;
        this.currentUser = currentUser;
        this.commentRepository = commentRepository;
    }

    @Override
    public Tour addCommentToTour(Long tourId, String text) {
        return getTourAddedComment(tourId, getComment(text));
    }

    private User getCurrentUser() {
        return userRepository.findById(currentUser.getUser().getId()).get();
    }

    private Comment getComment(String text) {
        Comment comment = new Comment();
        comment.setUser(getCurrentUser());
        List<User> users = new ArrayList<>();
        comment.setUsers(users);
        List<UserPutMark> usersPutMark = new ArrayList<>();
        comment.setUserChecks(usersPutMark);
        comment.setText(text);
        comment.setDislikes(0);
        comment.setLikes(0);
        comment.setTime(LocalDateTime.now());
        return commentRepository.save(comment);
    }

    private Tour getTourAddedComment(Long tourId, Comment comment) {
        Tour tour = tourRepository.findById(tourId).get();
        List<Comment> comments = tour.getComments();
        comments.add(comment);
        tour.setComments(comments);
        tourRepository.save(tour);
        return tour;
    }

    //Crud methods
    @Override
    public List<Tour> getAll() {
        return tourRepository.findAll();
    }

    @Override
    public Tour findById(Long id) {
        return tourRepository.findById(id).get();
    }

    @Override
    public Tour save(Tour tour) {
        return tourRepository.save(tour);
    }

    @Override
    public Tour update(Tour tour) {
        return tourRepository.save(tour);
    }

    @Override
    public void deleteById(Long id) {
        tourRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        tourRepository.deleteAll();
    }
}
