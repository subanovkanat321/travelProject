package com.example.travel.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "role1")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role", nullable = false, length = 100)
    private String roleName;

    public Role() {
    }
}
