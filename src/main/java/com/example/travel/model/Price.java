package com.example.travel.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tour")
    private Tour tour;

    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private Integer price;

    public Price() {
    }
}
