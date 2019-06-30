package com.example.travelProject.service;

import com.example.travelProject.enums.CheckListStatus;
import com.example.travelProject.enums.Mark;
import com.example.travelProject.enums.PaymentStatus;
import com.example.travelProject.helpers.CloneUser;
import com.example.travelProject.helpers.Helper;
import com.example.travelProject.model.*;
import com.example.travelProject.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class LogicServicelmpl implements LogicService {

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    Random random = new Random();

    @Autowired
    private UserRep userRep;
    @Autowired
    private CommentRep commentRep;
    @Autowired
    private RoleRep roleRep;
    @Autowired
    private ChecklistRep checklistRep;
    @Autowired
    private TourRep tourRep;
    @Autowired
    private CategoryRep categoryRep;
    @Autowired
    private PriceRep priceRep;
    @Autowired
    private PaymentRep paymentRep;
    @Autowired
    private UserPutMarkRep userPutMarkRep;
    @Autowired
    private Helper helper;

    @Override
    public Comment putMarkOnTheComment(Long tourId, Long commentId, Mark mark) {
        User user1 = userRep.findById(helper.getUser().getId()).get();
        Tour tour = tourRep.findById(tourId).get();
        Comment c = commentRep.findById(commentId).get();
        List<UserPutMark> userBln = c.getUserChecks();
        List<User> userIds = c.getUsers();
        List<Comment> comments = tour.getComments();
        boolean checkUserIds = true;
        for (Comment comment : comments) {
            if (comment.getId().equals(commentId)) {
                for (User user : userIds) {
                    if (user.getId().equals(user1.getId())) {
                        checkUserIds = false;
                        break;
                    }
                }
            }
        }
        if (checkUserIds) {
            if (mark.equals(Mark.LIKE)) {
                c.setLikes(c.getLikes() + 1);
                UserPutMark userPutMark = new UserPutMark();
                userPutMark.setUser(user1);
                userPutMark.setMark(Mark.LIKE);
                userPutMarkRep.save(userPutMark);
                userBln.add(userPutMark);
                userIds.add(user1);
            } else if (mark.equals(Mark.DISLIKE)) {
                c.setLikes(c.getDislikes() + 1);
                UserPutMark userPutMark = new UserPutMark();
                userPutMark.setUser(user1);
                userPutMark.setMark(Mark.DISLIKE);
                userPutMarkRep.save(userPutMark);
                userBln.add(userPutMark);
                userIds.add(user1);
            }
        } else {
            for (UserPutMark userCheck : userBln) {
                if (userCheck.getUser().getId().equals(user1.getId())) {
                    if (userCheck.getMark().equals(Mark.LIKE)) {
                        if (mark.equals(Mark.DISLIKE)) {
                            userCheck.setMark(Mark.DISLIKE);
                            c.setDislikes(c.getDislikes() + 1);
                            c.setLikes(c.getLikes() - 1);
                            userPutMarkRep.save(userCheck);
                        }
                    } else {
                        if (mark.equals(Mark.LIKE)) {
                            c.setDislikes(c.getDislikes() - 1);
                            c.setLikes(c.getLikes() + 1);
                            userCheck.setMark(Mark.LIKE);
                            userPutMarkRep.save(userCheck);
                        }
                    }
                }
            }
        }
        c.setUsers(userIds);
        c.setUserChecks(userBln);
        commentRep.save(c);
        return c;
    }

    @Override
    public Tour addComment(Long tourId, String text) {
        Comment c = new Comment();
        User user = userRep.findById(helper.getUser().getId()).get();
        c.setUser(user);
        Tour t = getTour(tourId, c);
        List<User> users = new ArrayList<>();
        c.setUsers(users);
        List<UserPutMark> userPutMarks = new ArrayList<>();
        c.setUserChecks(userPutMarks);
        c.setText(text);
        c.setDislikes(0);
        c.setLikes(0);
        c.setTime(LocalDateTime.now());
        commentRep.save(c);
        tourRep.save(t);
        return t;
    }

    public Tour getTour(Long tourId, Comment comment) {
        Tour t = tourRep.findById(tourId).get();
        List<Comment> comments = t.getComments();
        comments.add(comment);
        return t;
    }


    @Override
    public CheckList toPay(Integer confirmationCode, Integer sum) {
        List<CheckList> checkLists = checklistRep.findAll();
        List<Payment> payments = paymentRep.findAll();
        boolean paid = false;
        boolean paidAllSum = false;
        Integer s = 0;
        for (Payment payment : payments) {
            if (payment.getClient().getId().equals(helper.getUser().getId())) {
                if (payment.getConfirmationCode().equals(confirmationCode)) {
                    if (sum > payment.getForPayment()) {
                        break;
                    }

                    payment.setTime(LocalDateTime.now());
                    payment.setForPayment(payment.getForPayment() - sum);
                    s = payment.getForPayment();
                    if (s > 0) {
                        payment.setStatus(PaymentStatus.AWAITING_CONFIRMATION);
                    } else {
                        payment.setStatus(PaymentStatus.OK);
                        paidAllSum = true;
                    }
                    paymentRep.save(payment);
                    paid = true;
                    break;
                }
            }
        }

        if (paid) {
            for (CheckList checkList : checkLists) {
                if (checkList.getUser().getId().equals(helper.getUser().getId())) {
                    if (paidAllSum) {
                        checkList.setStatus(CheckListStatus.Paid);
                    }
                    checkList.setForPayment(s);
                    checklistRep.save(checkList);
                }
                return checkList;
            }
        }
        return null;
    }


    @Override
    public CloneUser registration(User user) {
        List<Role> roles = roleRep.findAll();
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
        CloneUser cloneUser = new CloneUser();
        cloneUser.setEmail(user.getEmail());
        cloneUser.setName(user.getName());
        cloneUser.setLastName(user.getLastName());
        cloneUser.setMobilePhone(user.getMobilePhone());
        userRep.save(user);
        return cloneUser;
    }
    @Override
    public Payment buyTour(Long tourId, Long categoryId, Integer howManyDays, Integer howManyPeople) {
        Payment payment = new Payment();
        CheckList checkList = new CheckList();
        User user = userRep.findById(helper.getUser().getId()).get();
        Tour tour = tourRep.findById(tourId).get();
        Category category = categoryRep.findById(categoryId).get();
        checkList.setCategory(category);
        checkList.setHowManyDays(howManyDays);
        checkList.setHowManyPeople(howManyPeople);
        checkList.setUser(user);
        checkList.setTime(LocalDateTime.now());
        checkList.setTour(tour);
        checkList.setStatus(CheckListStatus.NotPaid);
        payment.setConfirmationCode(random.nextInt(99999));
        System.out.println("This is confirmation code: " + payment.getConfirmationCode());
        payment.setStatus(PaymentStatus.AWAITING_CONFIRMATION);
        payment.setClient(user);
        payment.setTime(LocalDateTime.now());
        List<Price> prices = priceRep.findAll();
        for (Price price : prices) {
            if (price.getTour().getId().equals(tourId) && price.getCategory().getId().equals(categoryId)) {
                checkList.setForPayment(price.getPrice() * checkList.getHowManyDays() * checkList.getHowManyPeople());
                payment.setForPayment(checkList.getForPayment());
                break;
            }
        }
        paymentRep.save(payment);
        checklistRep.save(checkList);
        return payment;
    }
}
