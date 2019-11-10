package com.example.travel.service;

import com.example.travel.model.Payment;

import java.util.List;

public interface PaymentService {
    Payment buyTour(Long tourId, Long categoryId, Integer howManyDays, Integer howManyPeople);

    List<Payment> getConfirmPayments();

    List<Payment> getUnConfirmPayments();
}
