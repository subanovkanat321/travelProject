package com.example.travelProject.controller;

import com.example.travelProject.model.Payment;
import com.example.travelProject.service.CrudService;
import com.example.travelProject.utils.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private final CrudService<Payment> paymentCrudService;

    public PaymentController(CrudService<Payment> paymentCrudService) {
        this.paymentCrudService = paymentCrudService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<?>> getPayments() {
        try {
            return new ResponseEntity<>(paymentCrudService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getPaymentById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(paymentCrudService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response(false, e.toString()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> savePayment(@RequestBody Payment payment) {
        try {
            return new ResponseEntity<>(paymentCrudService.save(payment), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response(false, e.toString()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updatePayment(@RequestBody Payment payment) {
        try {
            return new ResponseEntity<>(paymentCrudService.save(payment), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response(false, e.toString()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<?> deletePaymentById(@PathVariable Long id) {
        try {
            this.paymentCrudService.deleteById(id);
            return new ResponseEntity<>("Deleted payment", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response(false, e.toString()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> deleteAllPayments() {
        try {
            this.paymentCrudService.deleteAll();
            return new ResponseEntity<>("Deleted all payments", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response(false, e.toString()), HttpStatus.BAD_REQUEST);
        }
    }
}