package com.example.travelProject.repository;

import com.example.travelProject.model.Kindness;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KindnessRep
        extends JpaRepository<Kindness,Long> {
}
