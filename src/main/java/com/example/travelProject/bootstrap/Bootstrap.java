package com.example.travelProject.bootstrap;

import com.example.travelProject.model.*;
import com.example.travelProject.service.CrudService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Component
public class Bootstrap implements CommandLineRunner {
    private final CrudService<User> userCrudService;

    private final CrudService<Kindness> kindnessService;

    private final CrudService<Category> categoryService;

    private final CrudService<Tour> tourCrudService;
    private final CrudService<Price> priceCrudService;

    public Bootstrap(CrudService<User> userCrudService,
                     CrudService<Kindness> kindnessService,
                     CrudService<Category> categoryService,
                     CrudService<Tour> tourCrudService,
                     CrudService<Price> priceCrudService) {
        this.userCrudService = userCrudService;
        this.kindnessService = kindnessService;
        this.categoryService = categoryService;
        this.tourCrudService = tourCrudService;
        this.priceCrudService = priceCrudService;
    }

    @Override
    public void run(String... args) {
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//
//        Role userRole = new Role();
//        userRole.setRoleName("ROLE_USER");
//
//        Role adminRole = new Role();
//        adminRole.setRoleName("ROLE_ADMIN");
//
//        User user = new User.Builder("kanatsubanov321@gmail.com",passwordEncoder.encode("12345"),
//                1,new HashSet<>(Arrays.asList(userRole, adminRole))).withLastame("Subanov").withName("Kanat").
//                withPhone("222").build();
//        userCrudService.save(user);
//
//        Kindness car = new Kindness();
//        car.setKindness("Gid");
//        kindnessService.save(car);
//
//        Kindness hotel = new Kindness();
//        hotel.setKindness("Hotel");
//        kindnessService.save(hotel);
//
//        Kindness internet = new Kindness();
//        internet.setKindness("Internet");
//        kindnessService.save(internet);
//
//        List<Kindness> fiveStar = new ArrayList<>();
//        fiveStar.add(car);
//        fiveStar.add(hotel);
//        fiveStar.add(internet);
//
//        List<Kindness> fourthStar = new ArrayList<>();
//        fourthStar.add(car);
//        fourthStar.add(hotel);
//
//        Category category = new Category();
//        category.setLevel(5);
//        category.setDescription("5 star");
//        category.setKindnesses(fiveStar);
//        categoryService.save(category);
//
//        Category category1 = new Category();
//        category1.setLevel(4);
//        category1.setDescription("4 star");
//        category1.setKindnesses(fourthStar);
//        categoryService.save(category1);
//
//        Tour tour = new Tour();
//        tour.setLocation("Ala Archa");
//        tour.setDescription("super place");
//        tourCrudService.save(tour);
//
//        Tour tour1 = new Tour();
//        tour1.setLocation("Chungurchak");
//        tour1.setDescription("amazing place");
//        tourCrudService.save(tour1);
//
//        Price price = new Price();
//        price.setCategory(category);
//        price.setPrice(120);
//        price.setTour(tour);
//        priceCrudService.save(price);
//
//        Price price1 = new Price();
//        price1.setCategory(category);
//        price1.setPrice(120);
//        price1.setTour(tour1);
//        priceCrudService.save(price1);
//
//        Price price2 = new Price();
//        price2.setCategory(category1);
//        price2.setPrice(100);
//        price2.setTour(tour);
//        priceCrudService.save(price2);
//
//        Price price3 = new Price();
//        price3.setCategory(category1);
//        price3.setPrice(100);
//        price3.setTour(tour1);
//        priceCrudService.save(price3);

    }
}
