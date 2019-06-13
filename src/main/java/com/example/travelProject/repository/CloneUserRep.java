package com.example.travelProject.repository;

import com.example.travelProject.helpers.CloneUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CloneUserRep
        extends JpaRepository<CloneUser, Long> {
}
