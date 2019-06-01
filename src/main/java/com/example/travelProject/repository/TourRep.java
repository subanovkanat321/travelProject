package com.example.travelProject.repository;

import com.example.travelProject.model.Tour;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourRep
        extends JpaRepository<Tour, Long> {
}
