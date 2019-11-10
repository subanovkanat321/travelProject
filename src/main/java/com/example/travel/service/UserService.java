package com.example.travel.service;

import com.example.travel.model.User;

public interface UserService {
    User registration(User u);

    User getMe();

}
