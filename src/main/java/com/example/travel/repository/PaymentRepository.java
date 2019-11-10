package com.example.travel.repository;

import com.example.travel.model.Payment;
import com.example.travel.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
    List<Payment> findPaymentsByClient(User user);
}
