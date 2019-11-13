package com.example.travel.service;

import com.example.travel.model.Tour;

public interface TourService {
    Tour addCommentToTour(Long tourId, String text);
}
