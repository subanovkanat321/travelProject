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
    public Tour addComment(Long tourId, String text) {
        Comment c = new Comment();
        User user = userRepository.findById(currentUser.getUser().getId()).get();
        c.setUser(user);
        Tour t = getTour(tourId, c);
        List<User> users = new ArrayList<>();
        c.setUsers(users);
        List<UserPutMark> userPutMarks = new ArrayList<>();
        c.setUserChecks(userPutMarks);
        c.setText(text);
        c.setDislikes(0);
        c.setLikes(0);
        c.setTime(LocalDateTime.now());
        commentRepository.save(c);
        tourRepository.save(t);
        return t;
    }

    private Tour getTour(Long tourId, Comment comment) {
        Tour t = tourRepository.findById(tourId).get();
        List<Comment> comments = t.getComments();
        comments.add(comment);
        return t;
    }


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
