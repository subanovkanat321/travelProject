package com.example.travelProject.bootstrap;

import com.example.travelProject.model.*;
import com.example.travelProject.repository.ChecklistRep;
import com.example.travelProject.repository.RoleRep;
import com.example.travelProject.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

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

    @Autowired
    private CrudService<Tour> tourCrudService;
    @Autowired
    private CrudService<Price> priceCrudService;

    @Autowired
    private ChecklistRep checklistRep;
    @Override
    public void run(String... args) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        Role userRole = new Role();
        userRole.setRoleName("ROLE_USER");
        Role adminRole = new Role();
        adminRole.setRoleName("ROLE_ADMIN");

        User user = new User();
        user.setPassword(passwordEncoder.encode("12345"));
        user.setLastName("Subanov");
        user.setName("Kanat");
        user.setMobilePhone("222");
        user.setEmail("kanatsubanov321@gmail.com");
        user.setActive(1);
        user.setRoles(new HashSet<>(Arrays.asList(userRole, adminRole)));
        userService.save(user);

        Kindness car = new Kindness();
        car.setKindness("Car");
        kindnessService.save(car);

        Kindness hotel = new Kindness();
        hotel.setKindness("Hotel");
        kindnessService.save(hotel);

        Kindness internet = new Kindness();
        internet.setKindness("Internet");
        kindnessService.save(internet);

        List<Kindness> fiveStar = new ArrayList<>();
        fiveStar.add(car);
        fiveStar.add(hotel);
        fiveStar.add(internet);

        Category category = new Category();
        category.setLevel(5);
        category.setDescription("5 star");
        category.setKindnesses(fiveStar);
        categoryService.save(category);

        Tour tour = new Tour();
        tour.setLocation("Ala Archa");
        tour.setDescription("super place");
        tourCrudService.save(tour);

        Price price = new Price();
        price.setCategory(category);
        price.setPrice(100);
        price.setTour(tour);
        priceCrudService.save(price);

    }
}
