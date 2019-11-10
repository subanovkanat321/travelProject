package com.example.travel.repository;

import com.example.travel.model.CheckList;
import com.example.travel.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChecklistRepository extends JpaRepository<CheckList, Long> {
    List<CheckList> findChecklistsByUser(User user);
}
