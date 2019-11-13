package com.example.travel.controller;

import com.example.travel.model.Payment;
import com.example.travel.service.CrudService;
import com.example.travel.service.PaymentService;
import com.example.travel.utils.BuyTour;
import com.example.travel.utils.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private final CrudService<Payment> paymentCrudService;

    private final PaymentService paymentService;

    public PaymentController(CrudService<Payment> paymentCrudService,
                             PaymentService paymentService) {
        this.paymentCrudService = paymentCrudService;
        this.paymentService = paymentService;
    }

    @GetMapping("/getConfirmPayments")
    public ResponseEntity<List<?>> getConfirmPayments() {
        try {
            return new ResponseEntity<>(paymentService.getConfirmPayments(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getUnConfirmPayments")
    public ResponseEntity<List<?>> getUnConfirmPayments() {
        try {
            return new ResponseEntity<>(paymentService.getUnConfirmPayments(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/buyTour")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> buyTour(@RequestBody BuyTour buyTour) {
        try {
            Payment payment = paymentService.buyTour(buyTour.getTourId(), buyTour.getCategoryId(),
                    buyTour.getHowManyDays(), buyTour.getHowManyPeople());
            return new ResponseEntity<>(payment, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response(false, e.toString()), HttpStatus.BAD_REQUEST);
        }
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