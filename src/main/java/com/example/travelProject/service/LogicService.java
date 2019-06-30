package com.example.travelProject.service;

import com.example.travelProject.enums.Mark;
import com.example.travelProject.helpers.CloneUser;
import com.example.travelProject.model.*;

public interface LogicService {

    CloneUser registration(User u);

    Payment buyTour(Long tourId, Long categoryId, Integer howManyDays, Integer howManyPeople);

    CheckList toPay(Integer confirmationCode, Integer sum);

    Tour addComment(Long tourId, String text);

    Comment putMarkOnTheComment(Long tourId, Long commentId, Mark mark);

}

