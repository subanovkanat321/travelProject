package com.example.travel.service;

import com.example.travel.model.Tour;

public interface TourService {
    Tour addComment(Long tourId, String text);
}
