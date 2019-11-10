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
        Payment payment = new Payment();
        CheckList checkList = new CheckList();
        User user = userRepository.findById(currentUser.getUser().getId()).get();
        Tour tour = tourRepository.findById(tourId).get();
        Category category = categoryRepository.findById(categoryId).get();
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
        List<Price> prices = priceRepository.findAll();
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

    @Override
    public List<Payment> getConfirmPayments() {
        List<Payment> payments = paymentRepository.findPaymentsByClient(currentUser.getUser());
        List<Payment> okPayments = new ArrayList<>();
        for (Payment payment : payments) {
            if (payment.getStatus().equals(PaymentStatus.OK)) {
                okPayments.add(payment);
            }
        }
        return okPayments;
    }

    @Override
    public List<Payment> getUnConfirmPayments() {
        List<Payment> payments = paymentRepository.findPaymentsByClient(currentUser.getUser());
        List<Payment> waitingPayments = new ArrayList<>();
        for (Payment payment : payments) {
            if (payment.getStatus().equals(PaymentStatus.AWAITING_CONFIRMATION)) {
                waitingPayments.add(payment);
            }
        }
        return waitingPayments;
    }

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
