package com.example.travelProject.repository;

import com.example.travelProject.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRep
        extends JpaRepository<Role,Long> {
}
