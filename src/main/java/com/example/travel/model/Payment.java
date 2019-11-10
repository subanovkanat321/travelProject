package com.example.travel.model;

import com.example.travel.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.ORDINAL)
    private PaymentStatus status;
    private Integer forPayment;
    @JsonIgnore
    private Integer confirmationCode;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;

    private LocalDateTime time;

    public Payment() {
    }
}
