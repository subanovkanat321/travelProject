package com.example.travelProject.repository;

import com.example.travelProject.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRep
        extends JpaRepository<Payment,Long> {
}
