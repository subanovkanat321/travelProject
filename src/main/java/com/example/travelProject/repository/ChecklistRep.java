package com.example.travelProject.repository;

import com.example.travelProject.model.CheckList;
import com.example.travelProject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChecklistRep
        extends JpaRepository<CheckList, Long> {
    List<CheckList> findChecklistsByUser(User user);
}
