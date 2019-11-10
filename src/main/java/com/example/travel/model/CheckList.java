package com.example.travel.model;

import com.example.travel.enums.CheckListStatus;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class CheckList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToOne
    @JoinColumn(name = "tour_id")
    private Tour tour;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private CheckListStatus status;

    private Integer howManyDays;
    private Integer howManyPeople;
    private Integer forPayment;

    private LocalDateTime time;

    public CheckList() {
    }
}
