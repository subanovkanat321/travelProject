package com.example.travel.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer level;
    private String description;

    @ManyToMany
    @JoinTable(name = "category_kindness",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "kindness_id"))
    private List<Kindness> kindnesses;

    public Category() {
    }
}
