package com.example.travelProject.service;

import com.example.travelProject.enums.Mark;
import com.example.travelProject.model.*;

public interface UserService {
    User registration(User u);
    boolean logIn(String email, String password);
    Payment byeTour(Long userId, Long tourId, Long categoryId, Integer howManyDays, Integer howManyPeople);
    CheckList toPay(Long userId, Integer confirmationCode, Integer sum);
    Tour addComment(Long userId, Long tourId, String text);
    Comment putMarkOnTheComment(Long userId, Long tourId, Long commentId, Mark mark);}
