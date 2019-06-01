package com.example.travelProject.repository;

import com.example.travelProject.model.UserPutMark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPutMarkRep
        extends JpaRepository<UserPutMark, Long> {
}
