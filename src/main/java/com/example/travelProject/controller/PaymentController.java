package com.example.travelProject.controller;

import com.example.travelProject.model.Payment;
import com.example.travelProject.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private CrudService<Payment> paymentCrudService;

    @GetMapping("/getAll")
    public ResponseEntity<List<? extends Object>> getPayments() {
        try {
            return new ResponseEntity<>(paymentCrudService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<? extends Object> getPaymentById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(paymentCrudService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<? extends Object> savePayment(@RequestBody Payment payment) {
        try {
            return new ResponseEntity<>(paymentCrudService.save(payment), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<? extends Object> updatePayment(@RequestBody Payment payment) {
        try {
            return new ResponseEntity<>(paymentCrudService.save(payment), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<? extends Object> deletePaymentById(@PathVariable Long id) {
        try {
            this.paymentCrudService.deleteById(id);
            return new ResponseEntity<>("Deleted payment", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<? extends Object> deleteAllPayments() {
        try {
            this.paymentCrudService.deleteAll();
            return new ResponseEntity<>("Deleted all payments", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }
}