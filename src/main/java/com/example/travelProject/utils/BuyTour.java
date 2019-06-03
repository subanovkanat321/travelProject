package com.example.travelProject.utils;

public class BuyTour {
    private Long userId;
    private Long tourId;
    private Long categoryId;
    private Integer howManyDays;
    private Integer howManyPeople;

    public BuyTour() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTourId() {
        return tourId;
    }

    public void setTourId(Long tourId) {
        this.tourId = tourId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getHowManyDays() {
        return howManyDays;
    }

    public void setHowManyDays(Integer howManyDays) {
        this.howManyDays = howManyDays;
    }

    public Integer getHowManyPeople() {
        return howManyPeople;
    }

    public void setHowManyPeople(Integer howManyPeople) {
        this.howManyPeople = howManyPeople;
    }
}
