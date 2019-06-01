package com.example.travelProject.repository;

import com.example.travelProject.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRep
        extends JpaRepository<Price,Long> {
}
