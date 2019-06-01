package com.example.travelProject.bootstrap;

import com.example.travelProject.model.Category;
import com.example.travelProject.model.Kindness;
import com.example.travelProject.model.Role;
import com.example.travelProject.model.User;
import com.example.travelProject.repository.RoleRep;
import com.example.travelProject.repository.UserRep;
import com.example.travelProject.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;

@Component
public class Bootstrap implements CommandLineRunner {
    @Autowired
    private CrudService<User> userService;

    @Autowired
    private RoleRep roleRep;

    @Autowired
    private CrudService<Kindness> kindnessService;

    @Autowired
    private CrudService<Category> categoryService;

    @Override
    public void run(String... args) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        Role userRole = new Role();
        userRole.setRoleName("ROLE_USER");
        roleRep.save(userRole);
        Role adminRole = new Role();
        userRole.setRoleName("ROLE_ADMIN");
        roleRep.save(adminRole);

        User user = new User();
        user.setPassword(passwordEncoder.encode("123"));
        user.setLastName("Subanov");
        user.setName("Kanat");
        user.setMobilePhone("222");
        user.setEmail("kanatsubanov321@gmail.com");
        user.setRoles(new HashSet<>(Arrays.asList(userRole, adminRole)));
        userService.save(user);

    }
}
