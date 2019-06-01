package com.example.travelProject.service;

import com.example.travelProject.model.User;
import com.example.travelProject.repository.UserRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServicelmpl implements CrudService<User> {
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
