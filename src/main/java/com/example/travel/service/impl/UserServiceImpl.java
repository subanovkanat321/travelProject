package com.example.travel.service.impl;

import com.example.travel.helpers.CurrentUser;
import com.example.travel.model.Role;
import com.example.travel.model.User;
import com.example.travel.repository.RoleRepository;
import com.example.travel.repository.UserRepository;
import com.example.travel.service.CrudService;
import com.example.travel.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceImpl implements CrudService<User>, UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final CurrentUser currentUser;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.currentUser = currentUser;
    }

    @Override
    public User getMe() {
        return currentUser.getUser();
    }

    @Override
    public User registration(User user) {
        List<Role> roles = roleRepository.findAll();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(1);
        boolean isRole = false;
        for (Role role : roles) {
            if (role.getRoleName().equals("ROLE_USER")) {
                user.setRoles(new HashSet<>(Arrays.asList(role)));
                isRole = true;
                break;
            }
        }
        if (!isRole) {
            Role role = new Role();
            role.setRoleName("ROLE_USER");
            user.setRoles(new HashSet<>(Arrays.asList(role)));
        }
        userRepository.save(user);
        return user;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }
}
