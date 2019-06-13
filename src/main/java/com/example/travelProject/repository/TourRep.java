package com.example.travelProject.repository;

import com.example.travelProject.model.Tour;
import com.example.travelProject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TourRep
        extends JpaRepository<Tour, Long> {
}
