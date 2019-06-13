package com.example.travelProject.service;

import com.example.travelProject.enums.CheckListStatus;
import com.example.travelProject.enums.Mark;
import com.example.travelProject.enums.PaymentStatus;
import com.example.travelProject.model.*;
import com.example.travelProject.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class UserServicelmpl implements CrudService<User> {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    Random random = new Random();
    @Autowired
    private UserRep userRep;

    @Override
    public List<User> getAll() {
        return userRep.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRep.findById(id).get();
    }

    @Override
    public User save(User user) {
        return userRep.save(user);
    }

    @Override
    public User update(User user) {
        return userRep.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userRep.deleteById(id);
    }

    @Override
    public void deleteAll() {
        userRep.deleteAll();
    }
}
