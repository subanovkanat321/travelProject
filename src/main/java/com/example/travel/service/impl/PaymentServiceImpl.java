package com.example.travel.service.impl;

import com.example.travel.enums.CheckListStatus;
import com.example.travel.enums.PaymentStatus;
import com.example.travel.helpers.CurrentUser;
import com.example.travel.model.*;
import com.example.travel.repository.*;
import com.example.travel.service.CrudService;
import com.example.travel.service.PaymentService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class PaymentServiceImpl implements CrudService<Payment>, PaymentService {
    private final Random random = new Random();

    private final PaymentRepository paymentRepository;

    private final CurrentUser currentUser;

    private final UserRepository userRepository;

    private final TourRepository tourRepository;

    private final CategoryRepository categoryRepository;

    private final PriceRepository priceRepository;

    private final ChecklistRepository checklistRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository,
                              CurrentUser currentUser,
                              UserRepository userRepository,
                              TourRepository tourRepository,
                              CategoryRepository categoryRepository,
                              PriceRepository priceRepository,
                              ChecklistRepository checklistRepository) {
        this.paymentRepository = paymentRepository;
        this.currentUser = currentUser;
        this.userRepository = userRepository;
        this.tourRepository = tourRepository;
        this.categoryRepository = categoryRepository;
        this.priceRepository = priceRepository;
        this.checklistRepository = checklistRepository;
    }

    @Override
    public Payment buyTour(Long tourId, Long categoryId, Integer howManyDays, Integer howManyPeople) {
        return getSumToPay(tourId, categoryId, howManyDays, howManyPeople);
    }

    private Payment getSumToPay(Long tourId, Long categoryId, Integer howManyDays, Integer howManyPeople) {
        Payment payment = getPayment();
        User user = getCurrentUser();
        Tour tour = tourRepository.findById(tourId).get();
        Category category = categoryRepository.findById(categoryId).get();
        List<Price> prices = priceRepository.findAll();
        CheckList checkList = getCheckList(category, user, tour, howManyDays, howManyPeople);
        for (Price price : prices) {
            if (price.getTour().getId().equals(tourId) && price.getCategory().getId().equals(categoryId)) {
                checkList.setForPayment(price.getPrice() * checkList.getHowManyDays() * checkList.getHowManyPeople());
                payment.setForPayment(checkList.getForPayment());
                break;
            }
        }
        paymentRepository.save(payment);
        checklistRepository.save(checkList);
        return payment;
    }
    private User getCurrentUser() {
        return userRepository.findById(currentUser.getUser().getId()).get();
    }

    private Payment getPayment() {
        Payment payment = new Payment();
        payment.setConfirmationCode(random.nextInt(99999));
        payment.setStatus(PaymentStatus.AWAITING_CONFIRMATION);
        payment.setClient(getCurrentUser());
        payment.setTime(LocalDateTime.now());
        System.out.println("This is confirmation code: " + payment.getConfirmationCode());
        return payment;
    }

    private CheckList getCheckList(Category category, User user, Tour tour, Integer howManyDays, Integer howManyPeople) {
        CheckList checkList = new CheckList();
        checkList.setCategory(category);
        checkList.setHowManyDays(howManyDays);
        checkList.setHowManyPeople(howManyPeople);
        checkList.setUser(user);
        checkList.setTime(LocalDateTime.now());
        checkList.setTour(tour);
        checkList.setStatus(CheckListStatus.NotPaid);
        return checkList;
    }

    @Override
    public List<Payment> getConfirmPayments() {
        List<Payment> payments = paymentRepository.findPaymentsByClient(currentUser.getUser());
        List<Payment> okPayments = new ArrayList<>();
        payments.stream().filter(x -> x.getStatus().equals(PaymentStatus.OK)).forEach(okPayments::add);
        return okPayments;
    }

    @Override
    public List<Payment> getUnConfirmPayments() {
        List<Payment> payments = paymentRepository.findPaymentsByClient(currentUser.getUser());
        List<Payment> waitingPayments = new ArrayList<>();
        payments.stream().filter(x -> x.getStatus().equals(PaymentStatus.AWAITING_CONFIRMATION)).forEach(waitingPayments::add);
        return waitingPayments;
    }

    //Crud methods
    @Override
    public List<Payment> getAll() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment findById(Long id) {
        return paymentRepository.findById(id).get();
    }

    @Override
    public Payment save(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public Payment update(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public void deleteById(Long id) {
        paymentRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        paymentRepository.deleteAll();
    }
}
