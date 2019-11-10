package com.example.travel.utils;

public class AddComment {
    private Long tourId;
    private String text;

    public AddComment() {
    }

    public Long getTourId() {
        return tourId;
    }

    public void setTourId(Long tourId) {
        this.tourId = tourId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
