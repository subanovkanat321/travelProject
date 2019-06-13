package com.example.travelProject.repository;

import com.example.travelProject.model.Payment;
import com.example.travelProject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRep
        extends JpaRepository<Payment,Long> {
    List<Payment> findPaymentsByClient(User user);
}
