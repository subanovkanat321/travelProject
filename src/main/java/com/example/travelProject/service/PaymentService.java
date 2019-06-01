package com.example.travelProject.service;

import com.example.travelProject.model.Payment;
import com.example.travelProject.repository.PaymentRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService implements CrudService<Payment> {
    @Autowired
    private PaymentRep paymentRep;

    @Override
    public List<Payment> getAll() {
        return paymentRep.findAll();
    }

    @Override
    public Payment findById(Long id) {
        return paymentRep.findById(id).get();
    }

    @Override
    public Payment save(Payment payment) {
        return paymentRep.save(payment);
    }

    @Override
    public Payment update(Payment payment) {
        return paymentRep.save(payment);
    }

    @Override
    public void deleteById(Long id) {
        paymentRep.deleteById(id);
    }

    @Override
    public void deleteAll() {
        paymentRep.deleteAll();
    }
}
