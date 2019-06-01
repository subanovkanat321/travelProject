package com.example.travelProject.repository;

import com.example.travelProject.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRep
        extends JpaRepository<Category, Long> {
}
