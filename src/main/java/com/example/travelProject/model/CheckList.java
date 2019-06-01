package com.example.travelProject.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CheckList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "price_id")
    private Price price;
    @OneToOne
    @JoinColumn(name = "tour_id")
    private Tour tour;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Integer howManyDays;
    private Integer howManyPeople;
    private Integer forPayment;

    private LocalDateTime time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Integer getForPayment() {
        return forPayment;
    }

    public void setForPayment(Integer forPayment) {
        this.forPayment = forPayment;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
