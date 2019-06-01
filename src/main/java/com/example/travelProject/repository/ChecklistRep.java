package com.example.travelProject.repository;

import com.example.travelProject.model.CheckList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChecklistRep
        extends JpaRepository<CheckList, Long> {
}
